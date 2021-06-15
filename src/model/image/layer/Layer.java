package model.image.layer;

import model.image.Image;

/**
 * Represents a single layer within a layered image. A layer has a name, an Image, and a visibility.
 */
public interface Layer {

  /**
   * Gets a deep copy of the image stored by the layer.
   *
   * @return a copy of the image stored by the layer
   */
  Image getImage();

  /**
   * Gets the name of the layer.
   *
   * @return the name of the layer
   */
  String getName();

  /**
   * Sets the visibility of the layer to the given value.
   *
   * @param visibility the value to set the layer's visibility to: true for visible, false for
   *                   invisible
   */
  void setVisibility(boolean visibility);

  /**
   * Gets the visibility of the layer.
   *
   * @return the visibility of the layer: true for visible, false for invisible
   */
  boolean getVisibility();
}
