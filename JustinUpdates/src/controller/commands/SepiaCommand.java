package controller.commands;

import model.operation.Operations;

public class SepiaCommand extends OperationCommand {

  @Override
  protected Operations getOperationName() {
    return Operations.SEPIA;
  }
}