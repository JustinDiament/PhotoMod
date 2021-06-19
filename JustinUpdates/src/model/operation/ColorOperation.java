package model.operation;

import java.util.ArrayList;
import java.util.List;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * Represents an image operation that modifies the overall color of an Image by modifying each Pixel
 * by similar factors in its red, green, and blue channels. Examples of such operations are applying
 * a monochrome or sepia appearance to an Image.
 * <p>
 * New class created to abstract the operations of MonochromeOperation and SepiaOperation. These two
 * classes still function 100% identically to their original use, just utilize some formerly
 * duplicate code shared here.
 * </p>
 */
public abstract class ColorOperation implements ImageOperation {

  /**
   * Produces the modifier factors for each color channel of the Pixels of the Image for the
   * respective Image operation type.
   *
   * @return a 2D array of the modifier factors
   */
  protected abstract double[][] getModifiers();

  @Override
  public Image apply(Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);

    double[][] modifiers = this.getModifiers();

    List<List<Pixel>> copy = new ArrayList<>();
    for (int x = 0; x < img.getWidth(); x++) {
      List<Pixel> row = new ArrayList<>();
      for (int y = 0; y < img.getHeight(); y++) {
        Pixel p = img.getPixelAt(x, y);
        int red = p.getRed();
        int green = p.getGreen();
        int blue = p.getBlue();
        row.add(new PixelImpl(
            (int) (modifiers[0][0] * red + modifiers[0][1] * green + modifiers[0][2] * blue),
            (int) (modifiers[1][0] * red + modifiers[1][1] * green + modifiers[1][2] * blue),
            (int) (modifiers[2][0] * red + modifiers[2][1] * green + modifiers[2][2] * blue)));
      }
      copy.add(row);
    }
    return new ImageImpl(copy);
  }
}
