package controller.commands;

import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;

/**
 * Function object to change the visibility level of an Image when the visibility operation is
 * requested.
 */
public class VisibilityCommand implements Command {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    String visibility;

    if (scanner.hasNext()) {
      visibility = scanner.next();
    }
    else {
      throw new IllegalArgumentException("No visibility level provided.");
    }

    if (visibility.equals("visible")) {
      model.setCurrentLayerVisibility(true);
    } else if (visibility.equals("invisible")) {
      model.setCurrentLayerVisibility(false);
    } else {
      throw new IllegalArgumentException("Specified visibility level is invalid.");
    }
  }
}
