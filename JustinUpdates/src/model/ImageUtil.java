package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import model.image.Image;
import model.image.Pixel;

/**
 * This class contains utility methods to be reused throughout the entire project.
 */
public class ImageUtil {

  /**
   * Checks that the given input is not null.
   *
   * @param obj the object to be checked for being null
   * @param <T> the generic type of the given input
   * @return the input if it is not null
   * @throws IllegalArgumentException if the given input is null
   */
  public static <T> T requireNonNull(T obj) throws IllegalArgumentException {
    if (obj == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    return obj;
  }

  /**
   * Converts an Image object to a BufferedImage object.
   *
   * @param img the Image object to be converted
   * @return the converted BufferedImage object
   * @throws IllegalArgumentException if the given Image is null
   */
  public static BufferedImage convertImage(Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);
    BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(),
        BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        Pixel p = img.getPixelAt(j, i);
        Color c = new Color(p.getRed(), p.getGreen(), p.getBlue());
        image.setRGB(j, i, c.getRGB());
      }
    }
    return image;
  }
}
