package model.image;

/**
 * Represents a single pixel of an image. A pixel has three base colors (red, green, blue) that are
 * used in various combinations to create any color.
 */
public interface Pixel {

  /**
   * Get the red value of the pixel.
   *
   * @return the integer value of the pixel's red value
   */
  int getRed();

  /**
   * Get the green value of the pixel.
   *
   * @return the integer value of the pixel's green value
   */
  int getGreen();

  /**
   * Get the blue value of the pixel.
   *
   * @return the integer value of the pixel's blue value
   */
  int getBlue();
}
