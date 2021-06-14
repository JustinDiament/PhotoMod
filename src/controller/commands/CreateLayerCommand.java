package controller.commands;

import model.image.ImageLayerModel;

/**
 * Function object to create a new layer in an Image when the createlayer operation is requested.
 */
public class CreateLayerCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) throws IllegalArgumentException {
    model.addLayer(specification);
  }
}
