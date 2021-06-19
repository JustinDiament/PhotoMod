package controller.commands;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;
import model.image.programmatic.CreateCheckerboard;

/**
 * Function object to create a programmatic checkerboard Image and set it as the Image for the
 * current layer.
 */
public class CreateCheckerboardCommand implements Command {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    int size = 0;
    int numSquares = 0;
    Color color1 = Color.BLACK;
    Color color2;

    if (scanner.hasNext()) {
      try {
        size = Integer.parseInt(scanner.next());

      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(
            "Size of checkerboard provided is not an integer.");
      }
    }

    if (scanner.hasNext()) {
      try {
        numSquares = Integer.parseInt(scanner.next());

      } catch (NumberFormatException e) {
        throw new IllegalArgumentException(
            "Size of checkerboard provided is not an integer.");
      }
    }

    if (scanner.hasNext()) {
      color1 = this.getSupportedColors().getOrDefault(scanner.next(), null);

      if (color1 == null) {
        throw new IllegalArgumentException("First requested color not supported.");
      }
    }

    if (scanner.hasNext()) {
      color2 = this.getSupportedColors().getOrDefault(scanner.next(), null);

      if (color2 == null) {
        throw new IllegalArgumentException("Second requested color not supported.");
      }
    } else {
      throw new IllegalArgumentException(
          "Not all specifications for checkerboard creation provided");
    }

    model.createProgrammaticImage(new CreateCheckerboard(size, numSquares, color1, color2));
  }

  /**
   * Produces a Map of names of colors to the Color types supported by this
   * CreateCheckerboardCommand implementation.
   *
   * @return a Map of names of colors to the Color types that are supported
   */
  protected Map<String, Color> getSupportedColors() {
    Map<String, Color> supportedColors = new HashMap<>();
    supportedColors.put("blue", Color.BLUE);
    supportedColors.put("black", Color.BLACK);
    supportedColors.put("white", Color.WHITE);
    supportedColors.put("green", Color.GREEN);
    supportedColors.put("red", Color.RED);
    supportedColors.put("orange", Color.ORANGE);
    supportedColors.put("yellow", Color.YELLOW);
    supportedColors.put("cyan", Color.CYAN);
    supportedColors.put("magenta", Color.MAGENTA);
    supportedColors.put("gray", Color.GRAY);
    return supportedColors;
  }
}
