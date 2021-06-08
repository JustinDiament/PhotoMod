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
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {
//
//  /**
//   * Read an image file in the PPM format and print the colors.
//   *
//   * @param filename the path of the file.
//   */

  // TODO: make a separate interface for reading different file types
  //  and make classes that implement it for each specific file type
  //  interface has import() and export() methods


  // eventually, change return type to create Image (see ppmToImage below)
//  public static void readPPM(String filename) {
//
//    //now set up the scanner to read from the string we just built
////    sc = new Scanner(builder.toString());
//    Scanner sc = new Scanner(ImageUtil.requireNonNull(ImageUtil.readFile(filename)));
//
//    String token;
//
//    token = sc.next();
//    if (!token.equals("P3")) {
//      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
//    }
//    int width = sc.nextInt();
//    System.out.println("Width of image: " + width);
//    int height = sc.nextInt();
//    System.out.println("Height of image: " + height);
//    int maxValue = sc.nextInt();
//    System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);
//
//    for (int i = 0; i < height; i++) {
//      for (int j = 0; j < width; j++) {
//        int r = sc.nextInt();
//        int g = sc.nextInt();
//        int b = sc.nextInt();
//        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
//      }
//    }
//  }


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

  public static Image ppmToImage(String fileName) {
    return null;
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
