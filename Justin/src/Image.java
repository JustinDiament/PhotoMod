/**
 * Represents an image, such as a photograph or programmatically generated image (though it is not
 * limited to these two).
 */
public interface Image {

  /**
   * Returns the pixel at the given location in this Image.
   *
   * @param x the x coordinate of the location of the desired pixel
   * @param y the y coordinate of the location of the desired pixel
   * @return the pixel at the given location
   * @throws IllegalArgumentException if the given location is invalid because x or y are less than
   *                                  0 or greater than the bounds of the image
   */
  Pixel getPixelAt(int x, int y) throws IllegalArgumentException;

  /**
   * Returns the width of this Image, in pixels.
   *
   * @return width of this Image in pixels.
   */
  int getWidth();

  /**
   * Returns the height of this Image, in pixels.
   *
   * @return height of this Image in pixels.
   */
  int getHeight();

  /**
   * Replaces the pixel at the given location in the image with the given pixel
   *
   * @param x the x coordinate of the location of the pixel to be replaced
   * @param y the y coordinate of the location of the pixel to be replaced
   * @throws IllegalArgumentException if the given location is invalid because x or y are less than
   *                                  0 or greater than the bounds of the image
   */
  void replacePixel(int x, int y, Pixel p) throws IllegalArgumentException;
}
