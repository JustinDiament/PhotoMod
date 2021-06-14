package controller.commands;

import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to create a new layer in an Image when the createlayer operation is requested.
 */
public class CreateLayerCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(specification);
    ImageUtil.requireNonNull(model);

    model.addLayer(specification);
  }
}
