package model.image;

import java.util.HashMap;
import java.util.Map;
import model.image.file.ImageFile;
import model.image.file.PPM;
import model.image.programmatic.ProgrammaticCreator;
import model.operation.BlurOperation;
import model.operation.ImageOperation;
import model.ImageUtil;
import model.operation.MonochromeOperation;
import model.operation.Operations;
import model.operation.SepiaOperation;
import model.operation.SharpenOperation;

/**
 * Represents the model of an Image modification program. The model processes Images provided by the
 * user and modifies it with operations such as filters chosen by the user.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {

  // MODIFICATION: Changed these fields from private to protected. Protected methods below, namely
  // getFiles and getOperations, were already protected with the intent to override them in future
  // model extensions. However, in order to do so, access to these fields was needed. As a result,
  // they were changed to protected. They are still not public facing, so this change will not
  // cause issues with clients usage of the model.
  protected final Map<Operations, ImageOperation> operationsMap;
  protected final Map<String, ImageFile> filesMap;

  /**
   * Creates an image processing model that is able to handle image files and perform operations on
   * Image objects.
   */
  public ImageProcessingModelImpl() {
    this.operationsMap = this.getOperations();
    this.filesMap = this.getFiles();
  }

  @Override
  public Image applyOperation(Image img, Operations o) throws IllegalArgumentException {
    ImageUtil.requireNonNull(o);
    ImageUtil.requireNonNull(img);
    ImageOperation operation = ImageUtil.requireNonNull(this.operationsMap.getOrDefault(o, null));
    return operation.apply(img);
  }

  @Override
  public Image createProgrammaticImage(ProgrammaticCreator creator)
      throws IllegalArgumentException {
    ImageUtil.requireNonNull(creator);
    return creator.create();
  }

  @Override
  public Image importImage(String filename, String extension) throws IllegalArgumentException {
    ImageUtil.requireNonNull(filename);
    ImageUtil.requireNonNull(extension);
    ImageFile file = ImageUtil.requireNonNull(this.filesMap.getOrDefault(extension, null));
    return file.importFile(filename);
  }

  @Override
  public void exportImage(String filename, String extension, Image img)
      throws IllegalArgumentException {
    ImageUtil.requireNonNull(filename);
    ImageUtil.requireNonNull(extension);
    ImageUtil.requireNonNull(img);
    ImageFile file = ImageUtil.requireNonNull(this.filesMap.getOrDefault(extension, null));
    file.exportFile(filename, img);
  }

  /**
   * Produces a Map of the operations on Images that are usable in this model implementation. The
   * keys are the names of the operations and the values are the corresponding function objects to
   * complete the operations.
   *
   * @return a map of the operations on Images that this model implementation can complete
   */
  protected Map<Operations, ImageOperation> getOperations() {
    Map<Operations, ImageOperation> operations = new HashMap<>();
    operations.put(Operations.SEPIA, new SepiaOperation());
    operations.put(Operations.MONOCHROME, new MonochromeOperation());
    operations.put(Operations.SHARPEN, new SharpenOperation());
    operations.put(Operations.BLUR, new BlurOperation());
    return operations;
  }

  /**
   * Produces a Map of the file formats that are able to be imported and exported in this model
   * implementation. The keys are the file extensions and the values are the corresponding function
   * objects used for file handling.
   *
   * @return a map of the file formats that this model implementation supports
   */
  protected Map<String, ImageFile> getFiles() {
    Map<String, ImageFile> files = new HashMap<>();
    files.put("ppm", new PPM());
    return files;
  }
}
