import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.file.ImageFile;
import model.image.file.PNG;
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

  @Test(expected = IllegalArgumentException.class)
  public void testConvertNullImage() {
    ImageUtil.convertImage(null);
  }

  @Test
  public void testConvertImageToBufferedImage() {
    ImageFile png = new PNG();
    Image i1 = png.importFile("res//test//quetzal//quetzaljpg.jpg");
    assertNotNull(i1);
    assertEquals(ImageImpl.class, i1.getClass());
    BufferedImage i2 = ImageUtil.convertImage(i1);
    assertNotNull(i2);
    assertEquals(BufferedImage.class, i2.getClass());
    assertEquals(i2.getWidth(), i1.getWidth());
    assertEquals(i2.getHeight(), i2.getHeight());
  }
}
