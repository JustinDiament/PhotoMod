package model.image.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import model.ImageUtil;
import model.image.Image;
import model.image.Pixel;
import model.image.PixelImpl;

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
public class PPM extends ImageFileFormat {

  @Override
  public Image importFile(String filename) throws IllegalArgumentException {
    Scanner sc = new Scanner(ImageUtil.requireNonNull(this.readFile(filename)));

    String token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    System.out.printf("Width: %d\nHeight: %d\nMaximum RGB value: %d%n", width, height, maxValue);

    List<List<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int red = sc.nextInt();
        int green = sc.nextInt();
        int blue = sc.nextInt();
        row.add(new PixelImpl(red, green, blue));
      }
      pixels.add(row);
    }
    return this.transpose(pixels);
  }

  /**
   * Reads in an image file line by line and throws away any comment lines.
   *
   * @param filename the path of the file to read
   * @return the data within the given file stored as a String, or null if the file does not exist
   * @throws IllegalArgumentException if the given filename is null
   */
  private String readFile(String filename) throws IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(ImageUtil.requireNonNull(filename)));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return null;
    }

    StringBuilder sb = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        sb.append(s).append(System.lineSeparator());
      }
    }
    return sb.toString();
  }

  @Override
  public void exportFile(String filename, Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);
    StringBuilder sb = new StringBuilder();
    String lineSeparator = System.lineSeparator();

    FileOutputStream out;
    try {
      out = new FileOutputStream(ImageUtil.requireNonNull(filename), false);

      // write the standard PPM heading
      sb.append("P3").append(lineSeparator);
      sb.append(img.getWidth()).append(lineSeparator);
      sb.append(img.getHeight()).append(lineSeparator);
      sb.append(255).append(lineSeparator);

      // write the RGB values of all of the pixels in the image
      for (int i = 0; i < img.getHeight(); i++) {
        for (int j = 0; j < img.getWidth(); j++) {
          Pixel p = img.getPixelAt(j, i);
          sb.append(p.getRed()).append(lineSeparator);
          sb.append(p.getGreen()).append(lineSeparator);
          sb.append(p.getBlue()).append(lineSeparator);
        }
      }

      out.write(sb.toString().getBytes());
      out.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to export the image as a PPM file");
    }
  }

  @Override
  protected String getExtension() {
    return "ppm";
  }
}
