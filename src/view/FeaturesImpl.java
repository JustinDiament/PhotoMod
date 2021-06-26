package view;

import controller.ImageInteractiveController;
import controller.commands.BlurCommand;
import controller.commands.MonochromeCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import java.util.EventListener;
import model.ImageUtil;

/**
 * Represents a listener that subscribes to event occurrences that an ImageView broadcasts. When one
 * of these events is broadcaster to this listener, it handles the event by delegating any
 * modifications to the model and view to the controller.
 */
public class FeaturesImpl implements Features, EventListener {

  private final ImageInteractiveController controller;

  /**
   * Creates an instance of FeaturesImpl that delegates events requiring modifications to the model
   * or view to the given controller object.
   *
   * @param controller the controller that will handle modifications to the model or view that the
   *                   handling of an event by this class requires
   * @throws IllegalArgumentException if the given controller is null
   */
  public FeaturesImpl(ImageInteractiveController controller)
      throws IllegalArgumentException {
    this.controller = ImageUtil.requireNonNull(controller);
  }

  @Override
  public void handleSepiaEvent() {
    this.controller.operationCommandExecute(new SepiaCommand());
  }

  @Override
  public void handleMonochromeEvent() {
    this.controller.operationCommandExecute(new MonochromeCommand());
  }

  @Override
  public void handleSharpenEvent() {
    this.controller.operationCommandExecute(new SharpenCommand());
  }

  @Override
  public void handleBlurEvent() {
    this.controller.operationCommandExecute(new BlurCommand());
  }

  @Override
  public void handleDownscaleEvent() {
    this.controller.downscaleExecute();
  }

  @Override
  public void handleMosaicEvent() {
    this.controller.mosaicExecute();
  }

  @Override
  public void handleCurrentLayerEvent() {
    this.controller.currentLayerExecute();
  }

  @Override
  public void handleAddLayerEvent() {
    this.controller.addLayerExecute();
  }

  @Override
  public void handleRemoveLayerEvent() {
    this.controller.removeLayerExecute();
  }

  @Override
  public void handleImportEvent() {
    this.controller.importExecute();
  }

  @Override
  public void handleExportLayerEvent() {
    this.controller.exportLayerExecute();
  }

  @Override
  public void handleExportAllEvent() {
    this.controller.exportAllExecute();
  }

  @Override
  public void handleExecuteScriptEvent() {
    this.controller.executeScript();
  }

  @Override
  public void handleCreateCheckerboardEvent() {
    this.controller.createCheckerboardExecute();
  }

  @Override
  public void handleVisibilityEvent() {
    this.controller.visibilityExecute();
  }
}
