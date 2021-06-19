package model.operation;

/**
 * Function object to perform the "Monochrome" operation on an Image. The given Image's RGB color
 * values are modified to make the overall image appear monochrome/grayscale, with each individual
 * pixel having equal red, green, and blue values.
 */
public class MonochromeOperation extends ColorOperation {

  @Override
  protected double[][] getModifiers() {
    return new double[][]{
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}};
  }
}
