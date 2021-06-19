import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import model.image.file.ImageFile;
import model.image.file.PNG;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PNG class.
 */
public class PNGTest {

  private ImageFile i;
  private Image i1;
  private Image i2;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.i = new PNG();
    this.i1 = null;

    List<List<Pixel>> l1 = new ArrayList<>();
    l1.add(new ArrayList<>(Collections.singletonList(new PixelImpl(10, 10, 10))));
    this.i2 = new ImageImpl(l1);
  }

  // test that a null file cannot be read
  @Test(expected = IllegalArgumentException.class)
  public void testImportNull() {
    this.i.importFile(null);
  }

  // test that a file that cannot be found cannot be read
  @Test(expected = IllegalArgumentException.class)
  public void testImportFake() {
    this.i.importFile("");
  }

  // test that a file that isn't a PNG file cannot be read
  @Test(expected = IllegalArgumentException.class)
  public void testImportNonPNG() {
    this.i.importFile("res\\test\\test.txt");
  }

  // test that a valid PNG is imported properly and a corresponding Image object is created
  @Test
  public void testImportPNG() {
    assertNull(this.i1);
    this.i1 = this.i.importFile("res\\test\\test_good.png");
    assertNotNull(this.i1);
    assertTrue(this.i1 instanceof ImageImpl);
    assertEquals(ImageImpl.class, this.i1.getClass());
    assertEquals(1, this.i1.getHeight());
    assertEquals(1, this.i1.getWidth());
  }

  // test that a null file cannot be written
  @Test(expected = IllegalArgumentException.class)
  public void testExportNullFile() {
    this.i.exportFile(null, this.i2);
  }

  // test that a null image cannot be written
  @Test(expected = IllegalArgumentException.class)
  public void testExportNullImage() {
    this.i.exportFile("res\\test\\test_good.png", null);
  }

  // test that a valid image is able to be exported properly to a valid file path
  @Test
  public void testExportPNG() {
    assertNotNull(this.i2);
    this.i.exportFile("res\\test\\test_good.png", this.i2);
    assertNull(this.i1);
    this.i1 = this.i.importFile("res\\test\\test_good.png");
    assertNotNull(this.i1);
    assertTrue(this.i1 instanceof ImageImpl);
    assertEquals(ImageImpl.class, this.i1.getClass());
  }

  // test that a valid image overwrites an existing image at the valid file path
  @Test
  public void testExportPNGOverwrite() {
    assertNotNull(this.i2);
    this.i.exportFile("res\\test\\test_good.png", this.i2);
    assertNull(this.i1);
    this.i1 = this.i.importFile("res\\test\\test_good.png");
    assertNotNull(this.i1);
    assertTrue(this.i1 instanceof ImageImpl);
    assertEquals(ImageImpl.class, this.i1.getClass());
    this.i.exportFile("res\\test\\test_good.png", this.i2);
    this.i1 = this.i.importFile("res\\test\\test_good.png");
    assertNotNull(this.i1);
    assertTrue(this.i1 instanceof ImageImpl);
    assertEquals(ImageImpl.class, this.i1.getClass());
  }
}
