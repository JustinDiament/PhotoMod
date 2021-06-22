package controller;

import controller.commands.Command;

/**
 * Represents a controller for an interactive Image modification graphical user interface. Executes
 * various commands to modify, import, export, and create Images based on methods called by a
 * listener which is awaiting event broadcasts from a view. Requests necessary data to execute these
 * commands from the view and modifies them to reflect * changes to the model and other changes by
 * the controller.
 */
public interface ImageInteractiveController {

  /**
   * Modifies the current layer to apply the given type of filter or color operation and updates the
   * view's Image if necessary.
   *
   * @param operationCommand the command to apply to the current layer's Image
   */
  void operationCommandExecute(Command operationCommand);

  /**
   * Modifies the model's entire Image to apply the downscale operation (decreases the Image's
   * overall size) by getting the chosen scales from the view and updates the view's Image if
   * necessary.
   */
  void downscaleExecute();

  /**
   * Modifies the current layer to apply the mosaic operation (chaining its Image to a tiled, blurry
   * look) by getting the user's requested number of seeds from the view and updating the view's
   * Image if necessary.
   */
  void mosaicExecute();

  /**
   * Adds a new layer to the top of the overall Image, adds it to the list of layers the user can
   * view and select, and modifies the visible Image in the view to be the new, top, visible layer.
   */
  void addLayerExecute();

  /**
   * Imports the Image at the chosen filepath to be the Image contained in the current layer and
   * updates the view's Image if necessary.
   */
  void importExecute();

  /**
   * Exports the current layer's Image to the chosen filepath.
   */
  void exportLayerExecute();

  /**
   * Exports all layer Images as a multi-layer Image to the chosen filepath.
   */
  void exportAllExecute();

  /**
   * Adds a programmatically generated checkerboard Image to the current layer by getting the
   * requested size, number of squares, and colors from the view and updates the view's Image if
   * necessary.
   */
  void createCheckerboardExecute();

  /**
   * Changes the current layer's visibility status based on the user's visibility state selection
   * and updates the view's Image if necessary.
   */
  void visibilityExecute();

  /**
   * Modifies the current layer to be the one specified by the user's selection and updates the
   * view's text indicating this current layer.
   */
  void currentLayerExecute();

  /**
   * Removes the chosen layer from model, updates the view's Image if necessary, and resets the
   * current layer and the view's text indicator of this layer's name.
   */
  void removeLayerExecute();
}
