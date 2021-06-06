/**
 * Function object to perform the "Sepia" operation on an Image. The given Image's RGB color values
 * are modified to give the overall image an old-time photo's signature Sepia appearance.
 */
public class SepiaOperation implements ImageOperation {

  @Override
  public void apply(Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);

    for (int x = 0; x < img.getWidth(); x++) {
      for (int y = 0; y < img.getHeight(); y++) {
        Pixel p = img.getPixelAt(x, y);
        int red = p.getRed();
        int green = p.getBlue();
        int blue = p.getBlue();

        img.replacePixel(x, y, new PixelImpl(
            (int) (0.393 * red + 0.769 * green + 0.189 * blue),
            (int) (0.349 * red + 0.686 * green + 0.168 * blue),
            (int) (0.272 * red + 0.534 * green + 0.131 * blue)));
      }
    }
  }
}