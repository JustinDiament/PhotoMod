package model.operation;

import java.util.ArrayList;
import java.util.List;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * Function object to perform the "Blur" operation on an Image. The given Image's RGB color values
 * are modified to make the overall image appear blurrier.
 */
public class BlurOperation extends FilterOperation {

  @Override
  protected double[][] getKernel() {
    return new double[][]{
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
  }

  public static void main(String[] args) {
    List<List<Pixel>> l = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < 10; j++) {
        row.add(new PixelImpl(100, 10, 0));
      }
      l.add(row);
    }
    Image i = new ImageImpl(l);
    ImageOperation o = new BlurOperation();
    Image i2 = o.apply(i);
    for (int a = 0; a < 10; a++) {
      for (int b = 0; b < 10; b++) {
        Pixel p = i2.getPixelAt(a, b);
        System.out.printf("(%d,%d,%d)          ", p.getRed(), p.getGreen(), p.getBlue());
      }
      System.out.println();
    }
    System.out.println();
  }
}
