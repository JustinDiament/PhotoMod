package controller.commands;

import model.operation.Operations;

public class BlurCommand extends OperationCommand {

  @Override
  protected Operations getOperationName() {
    return Operations.BLUR;
  }
}
