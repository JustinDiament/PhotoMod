package controller.commands;

import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to export the current layer of an Image when the export operation is requested.
 */
public class ExportCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    ImageUtil.requireNonNull(specification);
    ImageUtil.requireNonNull(model);

    String[] splitOnPeriod = specification.split("\\.");

    if (splitOnPeriod.length != 2) {
      throw new IllegalArgumentException("Image name to be exported lacks extension type.");
    }

    try {
      model.exportTopImage(specification, "jpg");
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Failed to export file.");
    }
  }
}
