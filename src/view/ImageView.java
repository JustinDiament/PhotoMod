package view;

import model.image.Image;

/**
 * Represents a GUI view for an Image modification program. This view allows the user to view
 * layered images and modify them through commands provided through components such as menus and
 * buttons. Errors such as invalid inputs are also conveyed to the user through popup windows with
 * customized messages.
 */
public interface ImageView {

  /**
   * Adds an event listener to the view whose methods are called upon actions performed by certain
   * view components.
   *
   * @param feature the listener to provide to the view
   * @throws IllegalArgumentException if the given feature is null
   */
  void addViewEventListener(Features feature) throws IllegalArgumentException;

  /**
   * Converts the given Image to an ImageIcon and renders it within the view to be visible to the
   * user. If the given Image is null, then a blank panel will be rendered.
   *
   * @param img the given image to be rendered
   */
  void renderImage(Image img);

  /**
   * Retrieves the user input value for the downscale x scale text field.
   *
   * @return the given downscale x scale value
   */
  String getXScale();

  /**
   * Retrieves the user input value for the downscale y scale text field.
   *
   * @return the given downscale y scale value
   */
  String getYScale();

  /**
   * Retrieves the user input value for the mosaic seeds text field.
   *
   * @return the given mosaic seeds value
   */
  String getSeeds();

  /**
   * Retrieves the user input value for the checkerboard size text field.
   *
   * @return the given checkerboard size value
   */
  String getCheckerboardSize();

  /**
   * Retrieves the user input value for the checkerboard number of squares text field.
   *
   * @return the given checkerboard number of squares value
   */
  String getNumSquares();

  /**
   * Retrieves the user input value for the checkerboard first color dropdown.
   *
   * @return the given checkerboard color one value
   */
  String getColorOne();

  /**
   * Retrieves the user input value for the checkerboard second color dropdown.
   *
   * @return the given checkerboard color two value
   */
  String getColorTwo();

  /**
   * Retrieves the user input value for the newly created layer text field.
   *
   * @return the given new layer name value
   */
  String getNewLayerName();

  /**
   * Adds the given layer name to all dropdowns with the image layer names.
   *
   * @param layerName the layer name to add to all of the dropdown menus
   * @throws IllegalArgumentException if the given layer name is null
   */
  void addNewLayerToDropdown(String layerName) throws IllegalArgumentException;

  /**
   * Retrieves the user input value for the file path of the file to import.
   *
   * @return the file path of the file to import
   */
  String getImportFilePath();

  /**
   * Retrieves the user input value for the file path of the file to export.
   *
   * @return the file path of the file to export
   */
  String getExportFilePath();

  /**
   * Retrieves the user input value for the file path of the script file to import and execute.
   *
   * @return the file path of the script to import and execute
   */
  String getScriptFilePath();

  /**
   * Retrieves the user input value of the extension of the file to import or export for the file
   * type dropdown.
   *
   * @return the extension of the file to handle
   */
  String getFileTypeExtension();

  /**
   * Retrieves the user input value of the name of the current layer for the layer names dropdown.
   *
   * @return the name of the current layer, or an empty string if no layer is selected
   */
  String getSelectedCurrentLayer();

  /**
   * Retrieves the user input value fo the name of the layer to remove for the layer names
   * dropdown.
   *
   * @return the name of the layer to remove, or an empty string if no layer is selected
   */
  String getSelectedRemoveLayer();

  /**
   * Changes the button panel border to display the given name as the current layer name.
   *
   * @param layerName the layer name to display as the current layer
   * @throws IllegalArgumentException if the given layer name is null
   */
  void changeCurrentLayerText(String layerName) throws IllegalArgumentException;

  /**
   * Changes the image panel border to display the given name as the visible layer name.
   *
   * @param layerName the layer name to display as the visible layer
   * @throws IllegalArgumentException if the given layer name is null
   */
  void changeCurrentVisibleLayerText(String layerName) throws IllegalArgumentException;

  /**
   * Removes the given layer name from the layer name dropdowns.
   *
   * @param layerName the layer name to remove from the image
   * @throws IllegalArgumentException if the given layer name is null or does not exist
   */
  void removeLayerName(String layerName) throws IllegalArgumentException;

  /**
   * Renders the given message as an informative error popup to display to the user.
   *
   * @param str the given error message to display
   * @throws IllegalArgumentException if the given message is null
   */
  void renderErrorMessage(String str) throws IllegalArgumentException;

  /**
   * Retrieves the user input value for the extension of the file to import for the import submenu.
   *
   * @return the extension of the file to import
   */
  String getImportExtension();

  /**
   * Retrieves the user input value for the extension of the file to export for the export submenu.
   *
   * @return the extension of the file to export
   */
  String getExportExtension();

  /**
   * Retrieves the user input value for the extension of the files to export for the export all
   * submenu.
   *
   * @return the extension of the files to export
   */
  String getExportAllExtension();
}
