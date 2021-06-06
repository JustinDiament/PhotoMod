/**
 * Represents filtering image operations, which are a subset of all image operations that involve
 * the use of a kernel to filter each of the pixels in an image.
 */
abstract class FilterOperation implements ImageOperation {

  protected final double[][] kernel;

  /**
   * Create a filter operation containing a kernel to be used for its specific filter operation.
   *
   * @throws IllegalStateException if the kernel does not have odd dimensions
   */
  FilterOperation() throws IllegalStateException {
    this.kernel = this.getKernel();

    if (this.kernel.length % 2 == 0) {
      throw new IllegalStateException("Invalid kernel implementation");
    }
  }

  /**
   * Generate the specific kernel for its corresponding filter operation.
   *
   * @return the kernel to be used to filter an image
   */
  protected abstract double[][] getKernel();
}
