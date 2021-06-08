package model.operation;

import java.util.ArrayList;
import java.util.List;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * Function object to perform the "Sepia" operation on an Image. The given Image's RGB color values
 * are modified to give the overall image an old-time photo's signature Sepia appearance.
 */
public class SepiaOperation implements ImageOperation {

  @Override
  public Image apply(Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);

    List<List<Pixel>> copy = new ArrayList<>();
    for (int x = 0; x < img.getWidth(); x++) {
      List<Pixel> row = new ArrayList<>();
      for (int y = 0; y < img.getHeight(); y++) {
        Pixel p = img.getPixelAt(x, y);
        int red = p.getRed();
        int green = p.getBlue();
        int blue = p.getBlue();
        row.add(new PixelImpl(
            (int) (0.393 * red + 0.769 * green + 0.189 * blue),
            (int) (0.349 * red + 0.686 * green + 0.168 * blue),
            (int) (0.272 * red + 0.534 * green + 0.131 * blue)));
      }
      copy.add(row);
    }
    return new ImageImpl(copy);
  }
}
