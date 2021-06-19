package model.operation;

/**
 * Function object to perform the "Sepia" operation on an Image. The given Image's RGB color values
 * are modified to give the overall image an old-time photo's signature Sepia appearance.
 */
public class SepiaOperation extends ColorOperation {

  @Override
  protected double[][] getModifiers() {
    return new double[][]{
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}};
  }
}
