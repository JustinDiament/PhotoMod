public class MonochromeOperation implements ImageOperations {

  @Override
  public void apply(Image i) {
    for (int x = 0; x < i.getWidth(); x++) {
      for (int y = 0; y < i.getHeight(); y++) {
        Pixel p = i.getPixelAt(x, y);
        int newColor = (int) (0.2126 * p.getRed() + 0.7152 * p.getGreen() + 0.0722 * p.getBlue());
        //TODO: Check if casting double to int is alright (I think it is)
        i.replacePixel(x, y, new PixelImpl(newColor, newColor, newColor));
      }
    }
  }
}