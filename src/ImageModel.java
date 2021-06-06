import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO: eventually add some invariants, mess with package stuff, add docstrings and tests
// TODO: check for passing in null values to all constructors


// TODO: class diagram, readme
// TODO: support for importing and exporting images

/**
 * Represents the model of an Image modification program. The model takes in an Image provided by
 * the user and modifies it with operations such as filters chosen by the user. This implementation
 * also includes a static method to produce a programmatically generated checkerboard image that can
 * be used to create an ImageModel object.
 */
public class ImageModel implements ImageProcessingModel {

  private final Image image;
  private final Map<Operations, ImageOperation> operationsMap;

  /**
   * Produces an ImageModel object using the given Image, which is in the format specified by the
   * Image class.
   *
   * @param image the image to be stored by this model instance
   * @throws IllegalArgumentException if the given Image is null
   */
  public ImageModel(Image image) throws IllegalArgumentException {
    ImageUtil.requireNonNull(image);

    this.image = image;
    this.operationsMap = this.getOperations();
  }

  /**
   * Produces an ImageModel object using the PPM image stored at the given file name.
   *
   * @param fileName the name of the file where the PPM image to be stored is located
   * @throws IllegalArgumentException if the given file name is null or no PPM image at that file
   *                                  name can be successfully imported
   */
  public ImageModel(String fileName) throws IllegalArgumentException {
    this(ImageUtil.ppmToImage(fileName));
  }

  public void applyOperation(Operations o) throws IllegalArgumentException {
    ImageUtil.requireNonNull(o);

    ImageOperation operation = this.getOperations().getOrDefault(o, null);

    ImageUtil.requireNonNull(operation);

    operation.apply(this.image);
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

  // TODO: move this into its own class (potentially CreateCheckerboard, which implements
  //  CreateImage)
  public static Image getCheckerboard(int size, int numSquares, Color firstColor, Color secondColor)
      throws IllegalArgumentException {
    if (size < 1 || numSquares < 1) {
      throw new IllegalArgumentException("Numerical arguments must be positive");
    }

    ImageUtil.requireNonNull(firstColor);
    ImageUtil.requireNonNull(secondColor);

    int width = (int) Math.sqrt(numSquares);
    int squareSize = size / width;

    List<List<Pixel>> image = new ArrayList<>();

    for (int i = 0; i < squareSize * width; i++) {
      image.add(new ArrayList<>());
    }

    Color currentColor;

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < width; y++) {
        if ((x + y) % 2 == 0) {
          currentColor = firstColor;
        } else {
          currentColor = secondColor;
        }

        for (int xPixel = x * squareSize; xPixel < x * squareSize + squareSize; xPixel++) {
          for (int yPixel = y * squareSize; yPixel < y * squareSize + squareSize; yPixel++) {
            image.get(xPixel).add(new PixelImpl(currentColor.getRed(), currentColor.getGreen(),
                currentColor.getBlue()));
          }
        }
      }
    }

    return new ImageImpl(image);
  }
}
