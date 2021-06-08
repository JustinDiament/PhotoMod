import model.FilterOperation;

/**
 * A bad filter operation to be used for testing purposes. This filter operation defines an invalid
 * kernel since it does not have odd dimensions.
 */
final class BadFilterOperation extends FilterOperation {

  @Override
  protected double[][] getKernel() {
    return new double[][]{{1, 1}, {1, 1}};
  }
}
