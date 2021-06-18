package controller.commands;

import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to export all layers of an Image as a multi-layer Image.
 */
public class ExportAllCommand implements Command {

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
        model.exportImage(fileName, scanner.next().toLowerCase(), null);
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Failed to export file.");
      }
    } else {
      throw new IllegalArgumentException("Not all specifications to export file provided.");
    }
  }
}
