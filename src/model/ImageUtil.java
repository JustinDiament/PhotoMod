package model;

import model.image.Image;
import model.image.file.ImageFile;
import model.image.file.PPM;
import model.operation.BlurOperation;
import model.operation.ImageOperation;
import model.operation.SharpenOperation;

/**
 * This class contains utility methods to be reused throughout the entire project.
 */
public class ImageUtil {

  //demo main
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "res\\manhattan-small.ppm";
    }

    ImageFile ppm = new PPM();
    Image i = ppm.importFile(filename);
    ImageOperation o = new BlurOperation();
    Image i2 = o.apply(i);
    ppm.exportFile("res\\skylinetest.ppm", i2);
  }

  /**
   * Checks that the given input is not null.
   *
   * @param obj the object to be checked for being null
   * @param <T> the generic type of the given input
   * @return the input if it is not null
   */
  public static <T> T requireNonNull(T obj) {
    if (obj == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    return obj;
  }
}
