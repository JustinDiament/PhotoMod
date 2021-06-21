package controller.commands;

import java.util.Scanner;
import model.image.ImageLayerModel;
import model.operation.DownscaleOperation;
import model.operation.ImageOperation;

/**
 * Function object to perform the "Downscale" operation and downscale the Images of all layers in
 * the overall image to given percents of their original horizontal and vertical size.
 */
public class DownscaleCommand implements Command {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException {

    int xScale = 0;
    int yScale;

    if (scanner.hasNext()) {
      try {
        xScale = Integer.parseInt(scanner.next());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("X Scale for downscale must be an integer.");
      }
    }

    if (scanner.hasNext()) {
      try {
        yScale = Integer.parseInt(scanner.next());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Y Scale for downscale must be an integer.");
      }
    } else {
      throw new IllegalArgumentException(
          "Two scale arguments must be provided in order to downscale the Image.");
    }

    int currentLayer = model.getCurrentLayerIndex();
    ImageOperation downscale = new DownscaleOperation(xScale, yScale);

    for (int i = 0; i < model.getLayerNames().size(); i++) {
      model.setCurrentLayer(i);
      model.setCurrentLayerImage(downscale.apply(model.getCurrentLayerImage()));
    }

    model.setCurrentLayer(currentLayer);
  }
}
