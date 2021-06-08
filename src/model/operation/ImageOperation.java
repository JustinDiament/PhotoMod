package model.operation;

import model.image.Image;

/**
 * Interface for function objects that perform operations on the pixels of an Image, such as
 * (but not limited to) filtering and color transformations.
 */
public interface ImageOperation {

  /**
   * Modifies the given Image's RGB values of each pixel to apply this operation to the image.
   *
   * @param img the Image to be modified
   * @return a new Image with the modifications applied
   * @throws IllegalArgumentException if the given Image is null
   */
  Image apply(Image img) throws IllegalArgumentException;
}
