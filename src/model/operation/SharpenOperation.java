package model.operation;

/**
 * Function object to perform the "Sharpen" operation on an Image. The given Image's RGB color
 * values are modified to sharpen the overall image by accentuating edges, or boundaries between
 * regions of high contrast.
 */
public class SharpenOperation extends FilterOperation {

  @Override
  protected double[][] getKernel() {
    return new double[][]{
        {-0.125, -0.125, -0.125, -0.125, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, 0.25, 1.0, 0.25, -0.125},
        {-0.125, 0.25, 0.25, 0.25, -0.125},
        {-0.125, -0.125, -0.125, -0.125, -0.125}};
  }
}
