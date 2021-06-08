package model.image;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.image.programmatic.ProgrammaticCreator;
import model.operation.BlurOperation;
import model.operation.ImageOperation;
import model.ImageUtil;
import model.operation.MonochromeOperation;
import model.operation.Operations;
import model.operation.SepiaOperation;
import model.operation.SharpenOperation;

//TODO: eventually add some invariants, add docstrings and tests
// TODO: check for passing in null values to all constructors


// TODO: class diagram, readme
// TODO: support for importing and exporting images

/**
 * Represents the model of an Image modification program. The model takes in an Image provided by
 * the user and modifies it with operations such as filters chosen by the user. This implementation
 * also includes a static method to produce a programmatically generated checkerboard image that can
 * be used to create an ImageModel object.
 */
public class ImageProcessingModelImpl { //implements ImageProcessingModel {

  private final Map<Operations, ImageOperation> operationsMap;
  private final Map<String, ImageFile> filesMap;

  // TODO: docstring
  public ImageProcessingModelImpl() {
    this.operationsMap = this.getOperations();
    this.filesMap = this.getFiles();
  }
  
  // @Override
  public Image applyOperation(Image img, Operations o) throws IllegalArgumentException {
    ImageUtil.requireNonNull(o);
    ImageOperation operation = ImageUtil.requireNonNull(this.operationsMap.getOrDefault(o, null));
    return operation.apply(ImageUtil.requireNonNull(img));
  }

  // @Override
  public Image importImage(String filename) throws IllegalArgumentException {
    String extension = filename.substring(filename.indexOf(".") + 1);
    ImageFile file = ImageUtil.requireNonNull(this.filesMap.getOrDefault(extension, null));
    return file.importFile(filename);
  }

  // @Override
  public void exportImage(String filename, Image img) {
    String extension = filename.substring(filename.indexOf(".") + 1);
    ImageFile file = ImageUtil.requireNonNull(this.filesMap.getOrDefault(extension, null));
    file.exportFile(filename, img);
  }

  /**
   * Produces a Map of the operations on Images that are usable in this model implementation. The
   * keys are the names of the operations and the values are thee corresponding function objects to
   * complete the operations.
   *
   * @return a map of the operations on Images that this model implementation can complete
   */
  private Map<Operations, ImageOperation> getOperations() {
    Map<Operations, ImageOperation> operations = new HashMap<>();
    operations.put(Operations.SEPIA, new SepiaOperation());
    operations.put(Operations.MONOCHROME, new MonochromeOperation());
    operations.put(Operations.SHARPEN, new SharpenOperation());
    operations.put(Operations.BLUR, new BlurOperation());
    return operations;
  }

  /**
   *
   * @return
   */
  private Map<String, ImageFile> getFiles() {
    Map<String, ImageFile> files = new HashMap<>();
    files.put("ppm", new PPM());
    return files;
  }

  // @Override
  public Image createProgrammaticImage(ProgrammaticCreator creator) {
    return creator.create();
  }
}
