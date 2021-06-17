package controller.commands;

import java.util.List;
import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to remove a layer from an Image when the removelayer operation is requested.
 * Resets the current layer afterwards.
 */
public class RemoveLayerCommand implements Command {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    List<String> layerNames = model.getLayerNames();
    String layerName;

    if (scanner.hasNext()) {
      layerName = scanner.next();
    }
    else {
      throw new IllegalArgumentException("No name of layer to remove provided");
    }

    for (int i = 0; i < layerNames.size(); i++) {
      if (layerNames.get(i).equals(layerName)) {
        model.removeLayer(i);

        model.setCurrentLayer(-1);
        return;
      }
    }

    throw new IllegalArgumentException("No layer with the specified name to remove exists.");
  }
}
