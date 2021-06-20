package model.operation;

import model.image.Image;

public class MosaicOperation implements ImageOperation {

  private final int seeds;

  public MosaicOperation(int seeds) {
    this.seeds = seeds;
  }

  @Override
  public Image apply(Image img) throws IllegalArgumentException {
    return null;
  }
}
