package model.operation;

import java.util.ArrayList;
import java.util.List;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * Function object to perform the "Monochrome" operation on an Image. The given Image's RGB color
 * values are modified to make the overall image appear monochrome/grayscale, with each individual
 * pixel having equal red, green, and blue values.
 */
public class MonochromeOperation implements ImageOperation {

  @Override
  public Image apply(Image img) {
    ImageUtil.requireNonNull(img);

    List<List<Pixel>> copy = new ArrayList<>();
    for (int x = 0; x < img.getWidth(); x++) {
      List<Pixel> row = new ArrayList<>();
      for (int y = 0; y < img.getHeight(); y++) {
        Pixel p = img.getPixelAt(x, y);
        int newColor = (int) (0.2126 * p.getRed() + 0.7152 * p.getGreen() + 0.0722 * p.getBlue());
        row.add(new PixelImpl(newColor, newColor, newColor));
      }
      copy.add(row);
    }
    return new ImageImpl(copy);
  }
}
