package model.image.file;

/**
 * Represents an image file in PNG format. PNG files are handled using ImageIO and BufferedImages.
 */
public class PNG extends ImageFileFormat {

  @Override
  protected String getExtension() {
    return "png";
  }
}
