import org.junit.Test;

/**
 * Test class for the FilterOperation abstract class.
 */
public class FilterOperationTest {

  // test that the FilterOperation constructor throws an exception if the implementing class
  // defines a kernel with even dimensions
  @Test(expected = IllegalStateException.class)
  public void testInvalidKernel() {
    new BadFilterOperation();
  }
}
