package controller.commands;

import java.util.List;
import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to change the current layer being operated on based on the layer name provided.
 */
public class ChangeCurrentLayerCommand implements Command {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    List<String> layerNames = model.getLayerNames();
    String newLayer;

    if (scanner.hasNext()) {
      newLayer = scanner.next();
    } else {
      throw new IllegalArgumentException("No layer name provided to change to.");
    }

    for (int i = 0; i < layerNames.size(); i++) {
      if (layerNames.get(i).equals(newLayer)) {
        model.setCurrentLayer(i);
        return;
      }
    }

    throw new IllegalArgumentException("No layer with the specified name to change to exists.");
  }
}
