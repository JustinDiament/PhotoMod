package controller.commands;

import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to create a new layer with a specified name when the createlayer operation is
 * requested.
 */
public class CreateLayerCommand implements Command {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    if (scanner.hasNext()) {
      String layerName = scanner.next();

      if (model.getLayerNames().contains(layerName)) {
        throw new IllegalArgumentException("Layer with the given name already exists.");
      }

      model.addLayer(layerName);
    } else {
      throw new IllegalArgumentException("Name not provided for new layer.");
    }
  }
}
