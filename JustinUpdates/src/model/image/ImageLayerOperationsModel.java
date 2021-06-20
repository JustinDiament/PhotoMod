package model.image;

import model.operation.ImageOperation;

/**
 *
 *
 */
public interface ImageLayerOperationsModel extends ImageLayerModel {

  /**
   *
   * @param operation
   */
  void applyOperation(ImageOperation operation);
}
