package controller.commands;

import java.util.List;
import model.image.ImageLayerModel;

/**
 * Function object to change the current layer being operated on based on the layer name provided.
 */
public class ChangeCurrentLayerCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) throws IllegalArgumentException {
    List<String> layerNames = model.getLayerNames();

    for (int i = 0; i < layerNames.size(); i++) {
      if (layerNames.get(i).equals(specification)) {
        model.setCurrentLayer(i);
        return;
      }
    }

    // todo: inform user if no layer with matching name exists
    throw new IllegalArgumentException("No layer with the specified name to change to exists.");
  }
}
