import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Function object to product a programmatically-generated Image of a checkerboard, in which two
 * chosen colors alternate to form a repeating pattern of identically-sized squares.
 */
public class CreateCheckerboard implements ProgrammaticCreator {

  private final int size;
  private final int numSquares;
  private final Color firstColor;
  private final Color secondColor;

  /**
   * @param size        the width and height of the square checkerboard to be generated, in pixels.
   *                    Will be rounded down to the highest possible integer that will allow for
   *                    each square to be equal in size
   * @param numSquares  the number of squares to be included in the checkerboard. Will be rounded
   *                    down to the highest possible perfect square so that the checkerboard can be
   *                    equal in size vertically and horizontally (and thus square)
   * @param firstColor  the first color of the two alternating colors in the checkerboard
   * @param secondColor the second color of the two alternating colors in the checkerboard
   * @throws IllegalArgumentException if either the size or numSquares are not positive numbers or
   *                                  if either of the Colors are null
   */
  CreateCheckerboard(int size, int numSquares, Color firstColor, Color secondColor)
      throws IllegalArgumentException {
    ImageUtil.requireNonNull(firstColor);
    ImageUtil.requireNonNull(secondColor);

    if (size < 1 || numSquares < 1) {
      throw new IllegalArgumentException("Numerical arguments must be positive");
    }

    this.size = size;
    this.numSquares = numSquares;
    this.firstColor = firstColor;
    this.secondColor = secondColor;
  }

  @Override
  public Image create() {
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