/**
 * Interface for function objects that perform operations on the pixels of an Image, such as
 * (but not limited to) sharpen, blur, and grayscale filters.
 */
public interface ImageOperation {

  /**
   * Modifies the given Image's RGB values of each pixel to apply this operation to the image.
   *
   * @param img the Image to be modified
   * @throws IllegalArgumentException if the given Image is null
   */
  void apply(Image img) throws IllegalArgumentException;
}

