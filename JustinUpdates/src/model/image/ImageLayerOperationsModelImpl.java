package model.image;

import model.image.layer.LayerImpl;
import model.operation.ImageOperation;

public class ImageLayerOperationsModelImpl extends ImageLayerModelImpl implements
    ImageLayerOperationsModel {

  // made protected: isValidLayer, currentLayer field, list of layers field

  @Override
  public void applyOperation(ImageOperation operation) {
    Image newImage = operation.apply(this.getCurrentLayerImage());
    layers.set(this.currentLayer, new LayerImpl(newImage, this.getCurrentLayer().getName(),
        this.getCurrentLayer().getVisibility()));
  }
}
