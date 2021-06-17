package controller.commands;

import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to import an Image. If the Image is a single, one layer image, it will be loaded
 * into the current layer. If it is a multi-layer Image, its layers will be loaded into separate
 * layers.
 */
public class ImportCommand implements Command {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    String fileName = "";

    if (scanner.hasNext()) {
      fileName = scanner.next();
    }

    if (scanner.hasNext()) {
      try {
        model.importImage(fileName, scanner.next().toLowerCase());
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Failed to import file.");
      }
    } else {
      throw new IllegalArgumentException("Not all specifications to import file provided.");
    }
  }
}
