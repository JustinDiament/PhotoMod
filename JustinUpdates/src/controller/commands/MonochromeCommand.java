package controller.commands;

import model.operation.Operations;

public class MonochromeCommand extends OperationCommand {

  @Override
  protected Operations getOperationName() {
    return Operations.MONOCHROME;
  }
}