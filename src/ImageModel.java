import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

  public Image getCheckerboard(int size, int numSquares, Color firstColor, Color secondColor) {

    List<List<Pixel>> image = new ArrayList<>();

    int squareSize = size / numSquares; // int stuff

    for (int i = 0; i < squareSize * numSquares; i++) {
      image.add(new ArrayList<>());
    }

    // NEEDS to be checked over, easily could be broken
    for (int i = 0; i < numSquares; i++) {

      for (int x = i * squareSize; x < i * squareSize + squareSize; x++) {
        for (int y = i * squareSize; y < i * squareSize + squareSize; y++) {
          if (i % 2 == 0) {
            image.get(x).add(new PixelImpl(firstColor.getRed(), firstColor.getGreen(),
                firstColor.getBlue()));
          } else {
            image.get(x).add(new PixelImpl(secondColor.getRed(), secondColor.getGreen(),
                secondColor.getBlue()));
          }
        }
      }
    }
    return new ImageImpl(image);
  }



  public Image getProgrammaticImage(ImageType i) {
    return null; //Justin
  }
  // TODO: make this static method? keep this in this class?
  //  extensibility for future programmatic images
}