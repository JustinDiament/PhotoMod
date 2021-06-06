/**
 * Function object to perform the "Monochrome" operation on an Image. The given Image's RGB color
 * values are modified to make the overall image appear monochrome/grayscale, with each individual
 * pixel having equal red, green, and blue values.
 */
public class MonochromeOperation implements ImageOperation {

  @Override
  public void apply(Image img) {
    ImageUtil.requireNonNull(img);

    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        Pixel p = img.getPixelAt(x, y);
        int newColor = (int) (0.2126 * p.getRed() + 0.7152 * p.getGreen() + 0.0722 * p.getBlue());

        img.replacePixel(x, y, new PixelImpl(newColor, newColor, newColor));
      }
    }
  }
}