package controller.commands;

import model.ImageUtil;
import model.image.ImageLayerModel;
import model.operation.Operations;

/**
 * Abstract class the facilitates the execution of filter operations such as blur, sharpen, sepia,
 * and monochrome when one of these operations is requested.
 */
abstract class OperationCommand implements Command {

  protected abstract Operations getOperationName();

  @Override
  public void execute(String specification, ImageLayerModel model) throws IllegalArgumentException {
    ImageUtil.requireNonNull(specification);
    ImageUtil.requireNonNull(model);

    int numTimesToApply;

    try {
      numTimesToApply = Integer.parseInt(specification);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "Specified number of times to apply filter operation is not a number.");
    }

    if (numTimesToApply < 1) {
      throw new IllegalArgumentException(
          "Specified number of times to apply filter operation is less than one time.");
    }

    for (int i = 0; i < numTimesToApply; i++) {
      model.applyOperation(model.getCurrentLayer().getImage(), this.getOperationName());
    }
  }
}
