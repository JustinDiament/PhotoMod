package controller.commands;

import java.util.List;
import model.image.ImageLayerModel;

/**
 * Function object to remove a layer from an Image when the removelayer operation is requested
 */
public class RemoveLayerCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) throws IllegalArgumentException {
    List<String> layerNames = model.getLayerNames();

    for (int i = 0; i < layerNames.size(); i++) {
      if (layerNames.get(i) == specification) {
        model.removeLayer(i);

        model.setCurrentLayer(-1);
        return;
      }
    }

    throw new IllegalArgumentException("No layer with the specified name to remove exists.");
    //todo: if none match?
  }
}
