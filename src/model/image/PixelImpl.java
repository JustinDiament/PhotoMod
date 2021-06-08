package model.image;

/**
 * A pixel of an image with red, green, and blue values.
 * <p>Invariant: the red, green, and blue values of a pixel are 0-255, inclusive.</p>
 */
public class PixelImpl implements Pixel {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructs a pixel using the given color values clamped between 0 and 255.
   *
   * @param red the integer value of the pixel's red value
   * @param green the integer value of the pixel's green value
   * @param blue the integer value of the pixel's blue value
   */
  public PixelImpl(int red, int green, int blue) {
    this.red = PixelImpl.clamp(red);
    this.green = PixelImpl.clamp(green);
    this.blue = PixelImpl.clamp(blue);
  }

  /**
   * Returns the integer value of the integer value. The value is clamped between 0 and 255 to
   * prevent overflow and underflow.
   *
   * @param color the integer value of the given pixel color
   * @return the integer value of the given pixel color clamped between 0 and 255
   */
  private static int clamp(int color) {
    return Math.max(Math.min(color, 255), 0);
  }

  @Override
  public int getRed() {
    return this.red;
  }

  @Override
  public int getGreen() {
    return this.green;
  }

  @Override
  public int getBlue() {
    return this.blue;
  }
}
