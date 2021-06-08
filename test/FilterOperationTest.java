import model.operation.ImageOperation;
import org.junit.Test;

/**
 * Test class for the FilterOperation abstract class.
 */
public class FilterOperationTest {

  ImageOperation b1;

  // test that the FilterOperation constructor throws an exception if the implementing class
  // defines a kernel with even dimensions
  @Test(expected = IllegalStateException.class)
  public void testInvalidKernel() {
    this.b1 = new BadFilterOperation();
  }
}
