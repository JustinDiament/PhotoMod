package controller.commands;

import model.operation.Operations;

/**
 * Function object to execute blur on an Image when the blur operation is requested.
 */
public class BlurCommand extends OperationCommand {

  @Override
  protected Operations getOperationName() throws IllegalArgumentException {
    return Operations.BLUR;
  }
}
