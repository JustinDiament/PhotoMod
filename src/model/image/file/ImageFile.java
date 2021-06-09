package model.image.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.ImageUtil;
import model.image.Image;

/**
 * Represents an image file format that supports import and export operations.
 */
public interface ImageFile {

  /**
   * Creates an Image using the imported file corresponding to the given file name.
   *
   * @param filename the path of the file to import
   * @return the Image object created from the given file
   * @throws IllegalArgumentException if importing the given file name failed
   */
  Image importFile(String filename) throws IllegalArgumentException;

  /**
   * Exports the given Image to a file with the given file name.
   *
   * @param filename the path of the file to export
   * @param img      the Image object to export to the given file
   * @throws IllegalArgumentException if exporting the given Image to the file path failed
   */
  void exportFile(String filename, Image img) throws IllegalArgumentException;

  /**
   * Helper method to read in an image file line by line and throw away any comment lines.
   *
   * @param filename the path of the file to read
   * @return the data within the given file stored as a String, or null if the file does not exist
   * @throws IllegalArgumentException if the given filename is null
   */
  static String readFile(String filename) throws IllegalArgumentException {
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
}
