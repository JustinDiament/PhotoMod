package controller.commands;

import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to export the current layer of an Image when the export operation is requested.
 */
public class ExportCommand extends FileCommand {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    String filename = "";

    if (scanner.hasNext()) {
      filename = scanner.next();
    }

    if (scanner.hasNext()) {
      try {
        String extension = scanner.next().toLowerCase();
        this.exportImage(filename, extension, model.getTopImage());
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Failed to export file.");
      }
    } else {
      throw new IllegalArgumentException("Not all specifications to export file provided.");
    }
  }
}
