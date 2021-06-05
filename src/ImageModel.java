import java.util.HashMap;
import java.util.Map;

//TODO: eventually add some invariants, mess with package stuff, add docstrings and tests
// TODO: check for passing in null values to all constructors

public class ImageModel implements ImageProcessingModel {

  private final Image image;
  private final Map<Operations, ImageOperations> operationsMap;

  //our image representation
  public ImageModel(Image image) {
    this.image = image;
    this.operationsMap = this.getOperations();

  }

  // will take in a PPM image and make it our representation
  public ImageModel(String fileName) {
    this(ImageUtil.ppmToImage(fileName));
  }

  public void applyOperation(Operations o) {
      // todo: finish operations first
  }


  private Map<Operations, ImageOperations> getOperations() {
    return new HashMap<>();
  }

  public Image getProgrammaticImage(ImageType i) {
    return null; //Justin
  }
}