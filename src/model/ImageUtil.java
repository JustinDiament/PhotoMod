package model;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import model.image.Image;
import model.image.ImageFile;
import model.image.PPM;
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
      filename = "res\\Koala.ppm";
    }

    ImageFile ppm = new PPM();
    Image i = ppm.importFile(filename);
    ImageOperation o = new SharpenOperation();
    o.apply(i);
    ppm.exportFile("res\\test3.ppm", i);
    //ImageUtil.readPPM(filename);
  }

  /**
   * Reads in an image file line by line and throw away any comment lines.
   *
   * @param filename the path of the file to read
   * @return the data within the given file stored as a String
   */
  public static String readFile(String filename) {
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
