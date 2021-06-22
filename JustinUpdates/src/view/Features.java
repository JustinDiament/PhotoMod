package view;

/**
 * Represents a listener that subscribes to event occurrences that an ImageView broadcasts. When
 * this listener hears a broadcasted event, it determines how to respond and if it needs to delegate
 * the response to a controller capable of modifying the model and/or view.
 */
public interface Features {

  /**
   * Responds to the "sepia" broadcasted event by modifying the current layer to apply the sepia
   * operation and updating the view's Image if necessary.
   */
  void handleSepiaEvent();

  /**
   * Responds to the "monochrome" broadcasted event by modifying the current layer to apply the
   * monochrome operation and updating the view's Image if necessary.
   */
  void handleMonochromeEvent();

  /**
   * Responds to the "sharpen" broadcasted event by modifying the current layer to apply the sharpen
   * operation and updating the view's Image if necessary.
   */
  void handleSharpenEvent();

  /**
   * Responds to the "blur" broadcasted event by modifying the current layer to apply the blur
   * operation and updating the view's Image if necessary.
   */
  void handleBlurEvent();

  /**
   * Responds to the "downscale" broadcasted event by modifying the model's entire Image to apply
   * the downscale and updating the view's Image if necessary.
   */
  void handleDownscaleEvent();

  /**
   * Responds to the "mosaic" broadcasted event by modifying the current layer to apply the mosaic
   * operation and updating the view's Image if necessary.
   */
  void handleMosaicEvent();

  /**
   * Responds to the "change current layer" broadcasted event by modifying the current layer to be
   * the one specified by the user's selection and updating the view's text indicating this current
   * layer.
   */
  void handleCurrentLayerEvent();

  /**
   * Responds to the "add new layer" broadcasted event by adding a new layer to the top of the
   * overall Image, adding it to the list of layers the user can view and select, and modifies the
   * visible Image in the view to be the new, top, visible layer.
   */
  void handleAddLayerEvent();

  /**
   * Responds to the "remove layer" broadcasted event by removing the chosen layer from model,
   * updating the view's Image if necessary, and resetting the current layer and the view's text
   * indicator of this layer's name.
   */
  void handleRemoveLayerEvent();

  /**
   * Responds to the "import" broadcasted event by importing the Image at the chosen filepath to be
   * the Image contained in the current layer and updating the view's Image if necessary.
   */
  void handleImport();

  /**
   * Responds to the "export layer" broadcasted event by exporting the current layer's Image to the
   * chosen filepath.
   */
  void handleExportLayer();

  /**
   * Responds to the "export layer" broadcasted event by exporting all layer Images as a multi-layer
   * Image to the chosen filepath.
   */
  void handleExportAll();

  /**
   * Responds to the "create checkerboard" broadcasted event by adding a programmatically generated
   * checkerboard Image to the current layer and updating the view's Image if necessary.
   */
  void handleCreateCheckerboard();

  /**
   * Responds to the "visibility" broadcasted event by changing the current layer's visibility
   * status and updating the view's Image if necessary.
   */
  void handleVisibility();
}
