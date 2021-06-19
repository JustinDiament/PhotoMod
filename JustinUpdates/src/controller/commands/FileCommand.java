package controller.commands;

import java.util.HashMap;
import java.util.Map;
import model.ImageUtil;
import model.image.Image;
import model.image.file.ImageFile;
import model.image.file.JPEG;
import model.image.file.PNG;
import model.image.file.PPM;

/**
 * Abstract class that factors out shared functionality between commands involving image file
 * handling.
 * <p>MODIFICATION: Moved image file handling from the model interfaces to the command
 * classes used in the controller since user I/O is the responsibility of the controller, not the
 * model.</p>
 */
abstract class FileCommand implements Command {

  /**
   * Creates an Image object using the provided file name.
   *
   * @param filename  the path of the file to import
   * @param extension the extension of the file type to import
   * @return the Image corresponding to the imported image file
   * @throws IllegalArgumentException if the file was unable to be imported
   */
  protected Image importImage(String filename, String extension) throws IllegalArgumentException {
    ImageUtil.requireNonNull(filename);
    ImageUtil.requireNonNull(extension);
    ImageFile file = ImageUtil.requireNonNull(this.getFiles().getOrDefault(extension, null));
    return file.importFile(filename);
  }

  /**
   * Exports the given Image to the provided file name.
   *
   * @param filename  the path of the file to export
   * @param extension the extension of the file type to export
   * @param img       the Image to be exported to the corresponding file
   * @throws IllegalArgumentException if the file was unable to be exported
   */
  protected void exportImage(String filename, String extension, Image img)
      throws IllegalArgumentException {
    ImageUtil.requireNonNull(filename);
    ImageUtil.requireNonNull(extension);
    ImageUtil.requireNonNull(img);
    ImageFile file = ImageUtil.requireNonNull(this.getFiles().getOrDefault(extension, null));
    file.exportFile(filename, img);
  }

  /**
   * Produces a Map of the file formats that are able to be imported and exported in this model
   * implementation. The keys are the file extensions and the values are the corresponding function
   * objects used for file handling.
   *
   * @return a map of the file formats that this model implementation supports
   */
  private Map<String, ImageFile> getFiles() {
    Map<String, ImageFile> files = new HashMap<>();
    files.put("ppm", new PPM());
    files.put("jpeg", new JPEG());
    files.put("jpg", new JPEG());
    files.put("png", new PNG());
    return files;
  }
}
