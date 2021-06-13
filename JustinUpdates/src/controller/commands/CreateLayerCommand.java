package controller.commands;

import model.image.ImageLayerModel;

public class CreateLayerCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    model.addLayer(specification);
  }
}
