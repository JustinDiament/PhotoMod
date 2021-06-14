package controller.commands;

import model.operation.Operations;

/**
 * Function object to execute sharpen on an Image when the sharpen operation is requested.
 */
public class SharpenCommand extends OperationCommand {

  @Override
  protected Operations getOperationName() {
    return Operations.SHARPEN;
  }
}