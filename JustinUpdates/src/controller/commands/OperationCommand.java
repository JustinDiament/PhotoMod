package controller.commands;

import model.image.ImageLayerModel;
import model.operation.Operations;

abstract class OperationCommand implements Command {

  @Override
  public void execute(String specification, ImageLayerModel model) {
    // todo: parse string -> number
    int numTimesToApply = Integer.parseInt(specification);

    for (int i = 0; i < numTimesToApply; i++) {
      model.applyOperation(model.getCurrentLayer().getImage(), this.getOperationName());
    }
  }

  protected abstract Operations getOperationName();
}
