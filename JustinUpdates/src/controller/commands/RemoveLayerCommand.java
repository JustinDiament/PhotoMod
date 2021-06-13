package controller.commands;

import java.util.List;
import model.image.ImageLayerModel;

public class RemoveLayerCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    List<String> layerNames = model.getLayerNames();

    for (int i = 0; i < layerNames.size(); i++) {
      if (layerNames.get(i) == specification) {
        model.removeLayer(i);
        return;
      }
    }

    //todo: if none match?
  }
}
