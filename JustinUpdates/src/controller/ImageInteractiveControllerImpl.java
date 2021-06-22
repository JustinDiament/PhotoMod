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
import javax.swing.text.html.ImageView;
import model.ImageUtil;
import model.image.ImageLayerModel;
import view.FeaturesImpl;

/**
 * Represents an implementation of a controller for an interactive Image modification graphical user
 * interface. Executes various commands to modify, import, export, and create Images based on
 * methods called by a listener which is awaiting event broadcasts from its connected view object.
 * Requests necessary data to execute these commands from the view and modifies them to reflect
 * changes to the model and other changes by the controller.
 */
public class ImageInteractiveControllerImpl implements ImageInteractiveController {

  private final ImageLayerModel model;
  private final ImageView view;

  /**
   * Constructs a ImageInteractiveControllerImpl object that can modify the given model and modify
   * and request information from the given view.
   *
   * @param model the model that the controller will modify based on executed commands
   * @param view  the view that the model will modify to reflect changes it creates and request
   *              information from to execute its commands
   * @throws IllegalArgumentException
   */
  public ImageInteractiveControllerImpl(ImageLayerModel model, ImageView view)
      throws IllegalArgumentException {
    this.model = ImageUtil.requireNonNull(model);
    this.view = ImageUtil.requireNonNull(view);

    this.view.addViewEventListener(new FeaturesImpl(this));
  }

  /**
   * Attempts to execute the given Command function object using the given parameters. If the
   * command was successfully executed, returns true to indicate this. If it was not, returns
   * false.
   *
   * @param scannerData the data that will be fed to the command to determine its execution
   *                    parameters
   * @param command     the command that will attempt to be executes
   * @return true if the command was successfully executed (does not throw an error) or false if the
   * command fails
   */
  private boolean executeCommand(String scannerData, Command command) {
    try {
      command.execute(new Scanner(scannerData), this.model);
      return true;
    } catch (IllegalArgumentException | IllegalStateException e) {
      // if the user's attempt to complete an action in the GUI cannot be completed due to an
      // invalid state or invalid user input, nothing should occur
      // todo: possibly add some kind of error popup or error ticker on the bottom of the gui?
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

      // this.view.changeVisibleLayerText("None");
      // no way to do this?
    } catch (IllegalArgumentException e) {
      // todo: if no layers are visible, make the view render an image with no pixels since there's
      // todo: nothing to show? or handle this another way
      //this.view.renderImage(new ImageImpl())
      // this.view.changeVisibleLayerText("None");
      // no way to do this?

    }
  }

  @Override
  public void operationCommandExecute(Command operationCommand) {
    if (this.executeCommand("", operationCommand)) {
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void downscaleExecute() {

    int xScale;
    int yScale;

    try {
      // handles empty xScale or yScale boxes
      xScale = Integer.parseInt(this.view.getXScale()); //getXScale returns a String
      yScale = Integer.parseInt(this.view.getYScale()); //getYScale returns a String
    } catch (NumberFormatException e) {
      return;
    }

    double xScaleOutOfOne = (double) xScale / 100;
    double yScaleOutOfOne = (double) yScale / 100;

    if (this.executeCommand(xScaleOutOfOne + " " + yScaleOutOfOne, new DownscaleCommand())) {
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void mosaicExecute() {
    int seeds;

    try {
      seeds = Integer.parseInt(this.view.getSeeds()); //getSeeds returns a String
    } catch (NumberFormatException e) {
      return;
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
   * extension.
   */
  private String getExtension(String path) {

    int i = path.lastIndexOf('.');
    if (i > 0) {
      return path.substring(i + 1);
    } else {
      return null;
    }
  }

  public void importExecute() {
    String path = this.view.getFilePath();

    String extension = this.getExtension(path);

    if (extension == null) {
      return;
    }

    this.executeCommand(path + " " + extension, new ImportCommand());
  }

  public void exportLayerExecute() {
    String path = this.view.getFilePath();

    String extension = this.getExtension(path);

    if (extension == null) {
      return;
    }

    this.executeCommand(path + " " + extension, new ExportCommand());
  }

  public void exportAllExecute() {
    String path = this.view.getFilePath();

    String extension = this.view.getExportAllFileType();

    this.executeCommand(path + " " + extension, new ExportAllCommand());
  }

  public void createCheckerboardExecute() {
    String colorOne = this.view.getColorOne();
    String colorTwo = this.view.getColorTwo();

    int size;
    int numSquares;

    try {
      // handles empty size or numSquares boxes
      size = Integer.parseInt(this.view.getSize()); // getSize returns a String
      numSquares = Integer.parseInt(this.view.getNumSqures()); // getNumSquares returns a String
    } catch (NumberFormatException e) {
      return;
    }

    if (this.executeCommand(size + " " + numSquares + " " + colorOne + " " + colorTwo,
        new CreateCheckerboardCommand())) {
      this.renderTopmostVisibleLayer();
    }
  }

  public void visibilityExecute() {
    if (this.view.getVisibilityState()) {
      this.executeCommand("visible", new VisibilityCommand());
    } else {
      this.executeCommand("invisible", new VisibilityCommand());
    }

    this.renderTopmostVisibleLayer();
  }

  public void currentLayerExecute() {
    String newCurrentLayer = this.view.getSelectedLayer();

    if (this.executeCommand(newCurrentLayer, new ChangeCurrentLayerCommand())) {
      this.view.changeCurrentLayerText(newCurrentLayer);
    }
  }

  public void removeLayerExecute() {
    String layerToRemove = this.view.getSelectedLayer();

    if (this.executeCommand(layerToRemove, new RemoveLayerCommand())) {
      this.view.changeCurrentLayerText("None");
      this.renderTopmostVisibleLayer();
    }
  }
}
