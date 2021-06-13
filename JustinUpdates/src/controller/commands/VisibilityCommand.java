package controller.commands;

import model.image.ImageLayerModel;

public class VisibilityCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    if (specification.equals("visible")) {
      model.setCurrentLayerVisibility(true);
    } else if (specification.equals("invisible")) {
      model.setCurrentLayerVisibility(false);
    } else { //todo: do something here
    }
  }
}
