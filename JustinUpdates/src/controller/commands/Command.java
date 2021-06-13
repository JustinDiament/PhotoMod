package controller.commands;

import model.image.ImageLayerModel;
import model.image.ImageProcessingModel;

public interface Command {

  void execute(String specification, ImageLayerModel model);
}


/**
 * possible commands:
 * createlayer [name] -done
 * current [name] -done
 * load [name]
 * blur [times] -done
 * other 3 -done
 * save [name]
 * removelayer [name] -done
 * visibility [visible/invisible]
 */