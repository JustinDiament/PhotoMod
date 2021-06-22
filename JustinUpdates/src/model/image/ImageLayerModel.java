package model.image;

import java.util.List;
import model.image.layer.Layer;

/**
 * Represents the model of an Image modification program. The model processes Images, modifies them
 * with operations such as filters chosen by the user, and handles file importing and exporting.
 * This model also stores all processed Images as layers and offers the ability to manipulate these
 * layers, as well as import multi-layered images and export multi-layered or single images.
 */
public interface ImageLayerModel extends ImageProcessingModel {

  /**
   * Adds a new layer to the top of the list of layers with a default null Image and set to
   * visible.
   *
   * @param name the name to give to the new layer
   * @throws IllegalArgumentException if the given name is null
   */
  void addLayer(String name) throws IllegalArgumentException;

  /**
   * Sets the Image stored by the current layer to the given image.
   *
   * @param img the new Image to store in the current layer
   * @throws IllegalArgumentException if the current layer index is invalid
   */
  void setCurrentLayerImage(Image img) throws IllegalArgumentException;

  /**
   * Sets the current layer index to the given value.
   *
   * @param index the value to set as the current layer index
   * @throws IllegalArgumentException if the given index is invalid
   */
  void setCurrentLayer(int index) throws IllegalArgumentException;

  /**
   * Retrieves the current layer specified by the model from the list of layers.
   *
   * @return the current layer from the list of layers
   * @throws IllegalStateException if the current layer index is invalid
   */
  Layer getCurrentLayer() throws IllegalStateException;

  /**
   * Sets the visibility of the current layer to the specified value.
   *
   * @param visibility the value to set the current layer's visibility to: true for visible, false
   *                   for invisible
   * @throws IllegalStateException if the current layer index is invalid
   */
  void setCurrentLayerVisibility(boolean visibility) throws IllegalStateException;

  /**
   * Generates a list of names of all of the layers.
   *
   * @return a list of layer names from the model
   */
  List<String> getLayerNames();

  /**
   * Generates a list of images stored by all of the layers.
   *
   * @return a list of image deep copies from the model
   */
  List<Image> getLayerImages();

  /**
   * Removes the specified layer from the list of layers.
   *
   * @param index the index of the layer to be removed
   * @throws IllegalArgumentException if the given index is invalid
   */
  void removeLayer(int index) throws IllegalArgumentException;

  /**
   * Returns the Pixel in the current layer at the given location.
   *
   * @param x the x coordinate of the desired Pixel
   * @param y the y coordinate of the desired Pixel
   * @return the Pixel at the given location
   * @throws IllegalArgumentException if the given location is outside the bounds of the current
   *                                  layer
   * @throws IllegalStateException    if the current layer has no Image or if the current layer is
   *                                  invalid
   */
  Pixel getPixelInCurrentLayerAt(int x, int y)
      throws IllegalArgumentException, IllegalStateException;

  /**
   * Returns a List of all of the Pixels in the Image in the current layer.
   *
   * @return a list of the current layer's image's pixels
   * @throws IllegalStateException if the current layer has no Image or if the current layer is
   *                               invalid
   */
  List<List<Pixel>> getCurrentLayerImagePixels() throws IllegalStateException;

  /**
   * Returns the Image contained by the current layer.
   *
   * @return the current layer's Image
   * @throws IllegalStateException if the current layer has no Image or if the current layer is
   *                               invalid
   */
  Image getCurrentLayerImage() throws IllegalStateException;

  /**
   * Returns the name of the current Layer.
   *
   * @return the current layer's name
   * @throws IllegalStateException if the current layer is invalid
   */
  String getCurrentLayerName() throws IllegalStateException;

  /**
   * Checks if the current layer is visible.
   *
   * @return the current layer's visibility status (true if it is visible, false if it is not)
   * @throws IllegalStateException if the current layer is invalid
   */
  boolean getCurrentLayerVisibility() throws IllegalStateException;

  /**
   * Checks that the given image shares the same dimensions as the rest of the layers.
   *
   * @param img the image to check the dimensions of
   * @throws IllegalArgumentException if the given image has different dimensions
   */
  void verifyLayerDimensions(Image img) throws IllegalArgumentException;

  /**
   * Returns the index of the current layer.
   *
   * @return the current layer's index
   */
  int getCurrentLayerIndex();

  /**
   * Returns the image stored in the topmost visible layer.
   *
   * @return a deep copy of the topmost visible image
   * @throws IllegalArgumentException if no layers are visible
   */
  Image getTopImage() throws IllegalArgumentException;
}
