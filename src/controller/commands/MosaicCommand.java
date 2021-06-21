package controller.commands;

import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;
import model.operation.ImageOperation;
import model.operation.MosaicOperation;

/**
 * Function object to apply the mosaic operation on an Image with the given number of seeds.
 */
public class MosaicCommand implements Command {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    int seeds = -1;
    if (scanner.hasNext()) {
      try {
        seeds = Integer.parseInt(scanner.next());
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Number of seeds provided is not an integer.");
      }
    }

    ImageOperation mosaic = new MosaicOperation(seeds);
    model.setCurrentLayerImage(mosaic.apply(model.getCurrentLayerImage()));
  }
}
