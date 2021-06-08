package model.image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.ImageUtil;

/**
 * Represents an image file in PPM format. PPM files follow the following format:
 * <ul>
 *   <li>Token (P3)</li>
 *   <li>Image width</li>
 *   <li>Image height</li>
 *   <li>Maximum RGB color value</li>
 *   <li>RGB values of each pixel</li>
 * </ul>
 */
public class PPM implements ImageFile {

  @Override
  public Image importFile(String filename) throws IllegalArgumentException {
    Scanner sc = new Scanner(ImageUtil.requireNonNull(ImageUtil.readFile(filename)));

    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    System.out.printf("Width: %d\nHeight: %d\nMaximum RGB value: %d%n", width, height, maxValue);

    List<List<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < width; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < height; j++) {
        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();
        row.add(new PixelImpl(red, green, blue));
      }
      pixels.add(row);
    }
    return new ImageImpl(pixels);
  }

  @Override
  public void exportFile(String filename, Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);
    byte[] lineSeparator = System.lineSeparator().getBytes();

    FileOutputStream out;
    try {
      out = new FileOutputStream(ImageUtil.requireNonNull(filename), false);

      // write the standard PPM heading
      out.write("P3".getBytes());
      out.write(lineSeparator);
      out.write(String.valueOf(img.getWidth()).getBytes());
      out.write(lineSeparator);
      out.write(String.valueOf(img.getHeight()).getBytes());
      out.write(lineSeparator);
      out.write(String.valueOf(255).getBytes());
      out.write(lineSeparator);

      // write the RGB values of all of the pixels in the image
      for (int i = 0; i < img.getWidth(); i++) {
        for (int j = 0; j < img.getHeight(); j++) {
          Pixel p = img.getPixelAt(i, j);
          out.write(String.valueOf(p.getRed()).getBytes());
          out.write(lineSeparator);
          out.write(String.valueOf(p.getGreen()).getBytes());
          out.write(lineSeparator);
          out.write(String.valueOf(p.getBlue()).getBytes());
          out.write(lineSeparator);
        }
      }

      out.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to export the image as a PPM file");
    }
  }
}
