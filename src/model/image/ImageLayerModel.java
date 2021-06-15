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
   * @throws IllegalArgumentException if the current layer index is invalid
   */
  Layer getCurrentLayer() throws IllegalArgumentException;

  /**
   * Sets the visibility of the current layer to the specified value
   *
   * @param visibility the value to set the current layer's visibility to: true for visible, false
   *                   for invisible
   * @throws IllegalArgumentException if the current layer index is invalid
   */
  void setCurrentLayerVisibility(boolean visibility) throws IllegalArgumentException;

  /**
   * Generates a list of names of all of the layers.
   *
   * @return a list of layer names from the model
   */
  List<String> getLayerNames();

  /**
   * Removes the specified layer from the list of layers.
   *
   * @param index the index of the layer to be removed
   * @throws IllegalArgumentException if the given index is invalid
   */
  void removeLayer(int index) throws IllegalArgumentException;

  /**
   * Exports the topmost visible layer in the model as an image file.
   *
   * @param filename the file path to export the topmost image to
   * @throws IllegalArgumentException if the file is unable to be exported or there are no visible
   *                                  layers in the model
   */
  void exportTopImage(String filename) throws IllegalArgumentException;
}
