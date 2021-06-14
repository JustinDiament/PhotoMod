package controller.commands;

import model.operation.Operations;

/**
 * Function object to execute monochrome on an Image when the monochrome operation is requested.
 */
public class MonochromeCommand extends OperationCommand {

  @Override
  protected Operations getOperationName() throws IllegalArgumentException {
    return Operations.MONOCHROME;
  }
}
