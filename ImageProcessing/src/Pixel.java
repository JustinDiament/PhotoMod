// TODO: make interface
//TODO: constructors should throw exception if given illegal rgb values, but other classes
// just make sure this never happens
// todo: nathan write tests
public class Pixel {

  private final int red;
  private final int green;
  private final int blue;

  public Pixel(int red, int green, int blue) {
    this.red = Pixel.clamp(red);
    this.green = Pixel.clamp(green);
    this.blue = Pixel.clamp(blue);
  }

  private static int clamp(int color) {
    if (color > 255) {
      return 255;
    }
    return Math.max(color, 0);
  }

  /**
   * @return
   */
  public int getRed() {
    return this.red;
  }

  /**
   * @return
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * @return
   */
  public int getBlue() {
    return this.blue;
  }
}
