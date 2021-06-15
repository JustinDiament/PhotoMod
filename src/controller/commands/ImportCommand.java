package controller.commands;

import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to import an Image. If the Image is a single, one layer image, it will be loaded
 * into the current layer. If it is a multi-layer Image, its layers will be loaded into separate
 * layers.
 */
public class ImportCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    ImageUtil.requireNonNull(specification);
    ImageUtil.requireNonNull(model);

    String[] splitOnPeriod = specification.split("\\.");

    if (splitOnPeriod.length != 2) {
      throw new IllegalArgumentException("Image name to be imported lacks extension type.");
    }

    try {
      model.importImage(specification);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Failed to import file.");
    }
  }
}
