package model.image;

/**
 * Represents an image file format that supports import and export operations.
 */
public interface ImageFile {

  // TODO: should this return an Image or an ImageProcessingModel
  //  add docstring
  Image importFile(String filename);

  // TODO: should this take in an Image or an ImageProcessingModel
  //  add docstring
  void exportFile(String filename, Image img);
}
