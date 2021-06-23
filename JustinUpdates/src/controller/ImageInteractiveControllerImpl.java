package controller;

import controller.commands.ChangeCurrentLayerCommand;
import controller.commands.Command;
import controller.commands.CreateCheckerboardCommand;
import controller.commands.CreateLayerCommand;
import controller.commands.DownscaleCommand;
import controller.commands.ExportAllCommand;
import controller.commands.ExportCommand;
import controller.commands.ImportCommand;
import controller.commands.MosaicCommand;
import controller.commands.RemoveLayerCommand;
import controller.commands.VisibilityCommand;
import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import view.FeaturesImpl;
import view.ImageViewImpl;

/**
 * Represents an implementation of a controller for an interactive Image modification graphical user
 * interface. Executes various commands to modify, import, export, and create Images based on
 * methods called by a listener which is awaiting event broadcasts from its connected view object.
 * Requests necessary data to execute these commands from the view and modifies them to reflect
 * changes to the model and other changes by the controller.
 */
public class ImageInteractiveControllerImpl implements ImageInteractiveController {

  private final ImageLayerModel model;
  // todo: rename all occurrences with view interface type once that is defined
  private final ImageViewImpl view;

  /**
   * Constructs a ImageInteractiveControllerImpl object that can modify the given model and modify
   * and request information from the given view.
   *
   * @param model the model that the controller will modify based on executed commands
   * @param view  the view that the model will modify to reflect changes it creates and request
   *              information from to execute its commands
   * @throws IllegalArgumentException if the given model or view are null
   */
  public ImageInteractiveControllerImpl(ImageLayerModel model, ImageViewImpl view)
      throws IllegalArgumentException {
    this.model = ImageUtil.requireNonNull(model);
    this.view = ImageUtil.requireNonNull(view);

    this.view.addViewEventListener(new FeaturesImpl(this));
  }

  public static void main(String[] args) {
    System.out.println(null + " ");
    ImageViewImpl v = new ImageViewImpl();
    ImageInteractiveController cont = new ImageInteractiveControllerImpl(new ImageLayerModelImpl(),
        v);
    cont.run();
  }

  @Override
  public void run() {
  }

  /**
   * Attempts to execute the given Command function object using the given parameters. If the
   * command was successfully executed, returns true to indicate this. If it was not, renders the
   * error message to the view and returns false.
   *
   * @param scannerData the data that will be fed to the command to determine its execution
   *                    parameters
   * @param command     the command that will attempt to be executes
   * @return true if the command was successfully executed (does not throw an error) or false if the
   * command fails
   * @throws IllegalArgumentException if the given scanner data or command are null
   */
  private boolean executeCommand(String scannerData, Command command)
      throws IllegalArgumentException {
    ImageUtil.requireNonNull(scannerData);
    ImageUtil.requireNonNull(command);

    try {
      command.execute(new Scanner(scannerData), this.model);
      return true;
    } catch (IllegalArgumentException | IllegalStateException e) {
      this.view.renderErrorMessage(e.getMessage());
      return false;
    }
  }

  /**
   * Renders the topmost visible layer in the model's Image in the view. If no Image is visible,
   * renders an empty Image in the view.
   */
  private void renderTopmostVisibleLayer() {
    try {
      this.view.renderImage(this.model.getTopImage());
    } catch (IllegalArgumentException e) {
      this.view.renderImage(null);
    }
  }

  @Override
  public void operationCommandExecute(Command operationCommand) {
    ImageUtil.requireNonNull(operationCommand);
    if (this.executeCommand("", operationCommand)) {
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void downscaleExecute() {

    int xScale = -1;
    int yScale = -1;

    try {
      // handles empty xScale or yScale boxes
      xScale = Integer.parseInt(this.view.getXScale()); //getXScale returns a String
      yScale = Integer.parseInt(this.view.getYScale()); //getYScale returns a String
    } catch (NumberFormatException ignored) {
    }

    if (this.executeCommand(xScale + " " + yScale, new DownscaleCommand())) {
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void mosaicExecute() {
    int seeds = -1;

    try {
      seeds = Integer.parseInt(this.view.getSeeds()); //getSeeds returns a String
    } catch (NumberFormatException ignored) {
    }

    if (this.executeCommand(Integer.toString(seeds), new MosaicCommand())) {
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void addLayerExecute() {
    String layerName = this.view.getNewLayerName();

    // handles empty layerName boxes
    if (this.executeCommand(layerName, new CreateLayerCommand())) {
      this.view.addNewLayerToDropdown(layerName);
      this.renderTopmostVisibleLayer();
    }
  }

  /**
   * Produces the Image extension type (such as ppm, jpg, or png) from a given Image filepath.
   *
   * @param path the filepath of the Image whose extension type will be returned
   * @return the extension type of the Image at the given filepath, or null if the filepath lacks an
   * extension
   */
  private String getExtension(String path) {

    int i = path.lastIndexOf('.');
    if (i > 0) {
      return path.substring(i + 1);
    } else {
      return null;
    }
  }

  @Override
  public void importExecute() {
    String path = this.view.getFilePathImport();

//    String extension = this.getExtension(path);
    //String extension = this.view.getExportAllFileType();
    String extension = "png";

    if (extension == null || path == null) {
      return;
    }

    if (this.executeCommand(path + " " + extension, new ImportCommand())) {
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void exportLayerExecute() {
    String path = this.view.getFilePathExport();

//    String extension = this.getExtension(path);
    String extension = this.view.getExportAllFileType();

    if (extension == null ) {
      return;
    }

    this.executeCommand(path + " " + extension, new ExportCommand());
  }

  @Override
  public void exportAllExecute() {
    String path = this.view.getFilePathExport();

    String extension = this.view.getExportAllFileType();

    this.executeCommand(path + " " + extension, new ExportAllCommand());
  }

  @Override
  public void createCheckerboardExecute() {
    String colorOne = this.view.getColorOne();
    String colorTwo = this.view.getColorTwo();

    int size = -1;
    int numSquares = -1;

    try {
      // handles empty size or numSquares boxes
      size = Integer.parseInt(this.view.getCheckerboardSize()); // getSize returns a String
      numSquares = Integer.parseInt(this.view.getNumSquares()); // getNumSquares returns a String
    } catch (NumberFormatException ignored) {
    }

    if (this.executeCommand(size + " " + numSquares + " " + colorOne + " " + colorTwo,
        new CreateCheckerboardCommand())) {
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void visibilityExecute() {
    boolean currentLayerVisibility;

    try {
      currentLayerVisibility = this.model.getCurrentLayerVisibility();
    } catch (IllegalStateException e) {
      this.view.renderErrorMessage(e.getMessage());
      return;
    }

    if (currentLayerVisibility) {
      // todo: optional text label to display visibility of current layer
      this.executeCommand("invisible", new VisibilityCommand());
    } else {
      this.executeCommand("visible", new VisibilityCommand());
    }

    this.renderTopmostVisibleLayer();
  }

  @Override
  public void currentLayerExecute() {
    String newCurrentLayer = this.view.getSelectedLayer();

    if (this.executeCommand(newCurrentLayer, new ChangeCurrentLayerCommand())) {
      this.view.changeCurrentLayerText(newCurrentLayer);
    }
  }

  @Override
  public void removeLayerExecute() {
    String layerToRemove = this.view.getSelectedLayer();

    if (this.executeCommand(layerToRemove, new RemoveLayerCommand())) {
      this.view.changeCurrentLayerText("None");
      this.view.removeLayerName(layerToRemove);
      this.renderTopmostVisibleLayer();
    }
  }
}
