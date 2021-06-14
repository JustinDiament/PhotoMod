package model.image.file;

/**
 * Represents an image file in JPG/JPEG format. JPEG files are handled using ImageIO and
 * BufferedImages.
 */
public class JPEG extends ImageFileFormat {

  @Override
  protected String getExtension() {
    return "jpg";
  }
}
