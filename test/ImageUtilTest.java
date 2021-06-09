import static org.junit.Assert.assertEquals;

import model.ImageUtil;
import org.junit.Test;

/**
 * Test class for the ImageUtil class.
 */
public class ImageUtilTest {

  // test that requireNonNull throws an exception if the input is null
  @Test(expected = IllegalArgumentException.class)
  public void testRequireNonNull1() {
    ImageUtil.requireNonNull(null);
  }

  // test that requireNonNull does not throw an exception if the input is non-null
  @Test
  public void testRequireNonNullGivenNonNull() {
    String s = "Not Null";
    assertEquals(s, ImageUtil.requireNonNull(s));
  }
}
