package controller.commands;

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
    int numTimesToApply = 0;

    try {
      numTimesToApply = Integer.parseInt(specification);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException(
          "Specified number of times to apply filter operation is not a number.");
      //todo: inform (or throw error) that this command can't work bc not a number
      // or could choose to default to 1 time?
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