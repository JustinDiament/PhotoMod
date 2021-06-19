package controller.commands;

import model.operation.Operations;

/**
 * Function object to execute sepia on an Image when the sepia operation is requested.
 */
public class SepiaCommand extends OperationCommand {

  @Override
  protected Operations getOperationName() {
    return Operations.SEPIA;
  }
}
