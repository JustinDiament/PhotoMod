package controller.commands;

import java.util.List;
import model.image.ImageLayerModel;

public class ChangeCurrentLayerCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    List<String> layerNames = model.getLayerNames();

    for (int i = 0; i < layerNames.size(); i++) {
      if (layerNames.get(i).equals(specification)) {
        model.setCurrentLayer(i);
        return;
      }
    }

    // todo: what to do if nothing matches
  }
}
