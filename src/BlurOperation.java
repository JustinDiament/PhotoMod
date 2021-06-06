/**
 * Function object to perform the "Blur" operation on an Image. The given Image's RGB color values
 * are modified to make the overall image appear blurrier.
 */
public class BlurOperation extends FilterOperation {

  @Override
  protected double[][] getKernel() {
    return new double[][]{
        {0.0625, 0.125, 0.0625},
        {0.125, 0.25, 0.125},
        {0.0625, 0.125, 0.0625}};
  }
}
