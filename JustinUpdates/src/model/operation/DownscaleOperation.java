package model.operation;

import model.image.Image;

public class DownscaleOperation implements ImageOperation {

  private final double xScale;
  private final double yScale;

  public DownscaleOperation(double xScale, double yScale) {
    this.xScale = xScale;
    this.yScale = yScale;
  }

  @Override
  public Image apply(Image img) throws IllegalArgumentException {
    return null;
  }
}
