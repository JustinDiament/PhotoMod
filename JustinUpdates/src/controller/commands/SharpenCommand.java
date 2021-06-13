package controller.commands;

import model.operation.Operations;

public class SharpenCommand extends OperationCommand {

  @Override
  protected Operations getOperationName() {
    return Operations.SHARPEN;
  }
}