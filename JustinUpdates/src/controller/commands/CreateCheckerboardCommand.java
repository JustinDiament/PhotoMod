package controller.commands;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import model.image.ImageLayerModel;
import model.image.programmatic.CreateCheckerboard;

/**
 * Function object to create a programmatic checkerboard Image and set it as the Image for the
 * current layer.
 */
public class CreateCheckerboardCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {

    String[] specifications = specification.split("-");

    if (specifications.length != 4) {
      throw new IllegalArgumentException(
          "Specifications for creation of checkerboard incorrectly provided.");
    }

    int size;
    int numSquares;
    Color color1;
    Color color2;

    try {
      size = Integer.parseInt(specifications[0]);
      numSquares = Integer.parseInt(specifications[1]);

    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "Integer arguments for checkerboard image incorrectly provided.");
    }

    color1 = this.getSupportedColors().getOrDefault(specifications[2], null);
    color2 = this.getSupportedColors().getOrDefault(specifications[3], null);

    if (color1 == null || color2 == null) {
      throw new IllegalArgumentException(
          "Color arguments for checkerboard image incorrectly provided.");
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
    Map<String, Color> supportedColors = new HashMap();

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
