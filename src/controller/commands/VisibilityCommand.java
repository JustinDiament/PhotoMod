package controller.commands;

import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to change the visibility level of an Image when the visibility operation is
 * requested.
 */
public class VisibilityCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    ImageUtil.requireNonNull(specification);
    ImageUtil.requireNonNull(model);

    if (specification.equals("visible")) {
      model.setCurrentLayerVisibility(true);
    } else if (specification.equals("invisible")) {
      model.setCurrentLayerVisibility(false);
    } else {
      throw new IllegalArgumentException("Specified visibility level is invalid.");
    }
  }
}
