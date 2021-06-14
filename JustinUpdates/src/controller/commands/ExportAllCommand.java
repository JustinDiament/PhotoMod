package controller.commands;

import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to export all layers of an Image as a multi-layer Image.
 */
public class ExportAllCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    ImageUtil.requireNonNull(specification);
    ImageUtil.requireNonNull(model);

    String[] splitOnPeriod = specification.split(".");

    if (splitOnPeriod.length != 2) {
      throw new IllegalArgumentException("Image name to be exported lacks extension type.");
    }

    try {
      model.exportImage(specification, null);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Failed to export file.");
    }
  }
}
