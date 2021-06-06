public class SepiaOperation implements ImageOperations {

  @Override
  public void apply(Image i) {
    for (int x = 0; x < i.getWidth(); x++) {
      for (int y = 0; y < i.getHeight(); y++) {
        Pixel p = i.getPixelAt(x, y);
        double red = p.getRed();
        double green = p.getBlue();
        double blue = p.getBlue();
        //TODO: Check if casting double to int is alright (I think it is)
        i.replacePixel(x, y, new Pixel(
            (int) (0.393 * red + 0.769 * green + 0.189 * blue),
            (int) (0.349 * red + 0.686 * green + 0.168 * blue),
            (int) (0.272 * red + 0.534 * green + 0.131 * blue)));
      }
    }
  }
}