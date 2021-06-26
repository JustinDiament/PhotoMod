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
import view.FeaturesImpl;
import view.ImageView;

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
   * @throws IllegalArgumentException if the given model or view are null
   */
  public ImageInteractiveControllerImpl(ImageLayerModel model, ImageView view)
      throws IllegalArgumentException {
    this.model = ImageUtil.requireNonNull(model);
    this.view = ImageUtil.requireNonNull(view);

    this.view.addViewEventListener(new FeaturesImpl(this));
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
    } catch (IllegalArgumentException | IllegalStateException e) {
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
      xScale = Integer.parseInt(this.view.getXScale());
      yScale = Integer.parseInt(this.view.getYScale());
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
      seeds = Integer.parseInt(this.view.getSeeds());
    } catch (NumberFormatException ignored) {
    }

    if (this.executeCommand(Integer.toString(seeds), new MosaicCommand())) {
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void addLayerExecute() {
    String layerName = this.view.getNewLayerName();

    if (this.executeCommand(layerName, new CreateLayerCommand())) {
      this.view.addNewLayerToDropdown(layerName);
      this.view.changeCurrentVisibleLayerText(this.model.getTopName());
      this.renderTopmostVisibleLayer();
    }
  }

  @Override
  public void importExecute() {
    String path = this.view.getImportFilePath();
    String extension = this.view.getImportExtension();

    if (extension == null || path == null) {
      return;
    }

    if (this.executeCommand(path + " " + extension, new ImportCommand())) {
      this.renderTopmostVisibleLayer();
      this.view.changeCurrentVisibleLayerText(this.model.getTopName());
    }
  }

  @Override
  public void exportLayerExecute() {
    String path = this.view.getExportFilePath();
    String extension = this.view.getExportExtension();

    if (extension == null || path == null) {
      return;
    }

    this.executeCommand(path + " " + extension, new ExportCommand());
  }

  @Override
  public void exportAllExecute() {
    String path = this.view.getExportFilePath();
    String extension = this.view.getExportAllExtension();

    if (extension == null || path == null) {
      return;
    }

    this.executeCommand(path + " " + extension, new ExportAllCommand());
  }

  @Override
  public void executeScript() {
    String scriptFilepath = this.view.getScriptFilePath();
    StringBuilder ap = new StringBuilder();

    ImageController controller;
    try {
      controller = new ImageControllerImpl(this.model, scriptFilepath, ap);
      controller.run();
    }
    catch (IllegalArgumentException | IllegalStateException e) {
      this.view.renderErrorMessage(e.getMessage());
    }

    this.view.renderErrorMessage(ap.toString());
  }

  @Override
  public void createCheckerboardExecute() {
    String colorOne = this.view.getColorOne();
    String colorTwo = this.view.getColorTwo();

    int size = -1;
    int numSquares = -1;

    try {
      size = Integer.parseInt(this.view.getCheckerboardSize());
      numSquares = Integer.parseInt(this.view.getNumSquares());
    } catch (NumberFormatException ignored) {
    }

    if (this.executeCommand(size + " " + numSquares + " " + colorOne + " " + colorTwo,
        new CreateCheckerboardCommand())) {
      this.renderTopmostVisibleLayer();
      this.view.changeCurrentVisibleLayerText(this.model.getTopName());
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
      this.executeCommand("invisible", new VisibilityCommand());
    } else {
      this.executeCommand("visible", new VisibilityCommand());
    }

    this.renderTopmostVisibleLayer();
    this.view.changeCurrentVisibleLayerText(this.model.getTopName());
  }

  @Override
  public void currentLayerExecute() {
    String newCurrentLayer = this.view.getSelectedCurrentLayer();

    if (this.executeCommand(newCurrentLayer, new ChangeCurrentLayerCommand())) {
      this.view.changeCurrentLayerText(newCurrentLayer);
      this.view.changeCurrentVisibleLayerText(this.model.getTopName());
    }
  }

  @Override
  public void removeLayerExecute() {
    String layerToRemove = this.view.getSelectedRemoveLayer();

    if (this.executeCommand(layerToRemove, new RemoveLayerCommand())) {
      this.view.changeCurrentLayerText("None");
      this.view.changeCurrentVisibleLayerText(this.model.getTopName());
      this.view.removeLayerName(layerToRemove);
      this.renderTopmostVisibleLayer();
    }
  }
}
