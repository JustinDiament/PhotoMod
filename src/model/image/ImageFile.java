package model.image;

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
   * @param img the Image object to export to the given file
   * @throws IllegalArgumentException if exporting the given Image to the file path failed
   */
  void exportFile(String filename, Image img) throws IllegalArgumentException;
}
