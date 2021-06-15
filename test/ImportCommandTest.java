import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import controller.commands.Command;
import controller.commands.ImportCommand;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ImportCommand class.
 */
public class ImportCommandTest {

  private Command c;
  private ImageLayerModel m;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.c = new ImportCommand();
    this.m = new ImageLayerModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImportNullSpec() {
    this.c.execute(null, this.m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImportNullModel() {
    this.c.execute("res\\test\\layer\\test.jpg", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFileSpec() {
    this.c.execute("", this.m);
  }

  @Test
  public void testImportSingleImage() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.c.execute("res\\test\\layer\\test.jpg", this.m);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    assertEquals(1, this.m.getCurrentLayer().getImage().getHeight());
    assertEquals(1, this.m.getCurrentLayer().getImage().getWidth());
    assertEquals("first", this.m.getCurrentLayer().getName());
  }

  @Test
  public void testImportLayeredImage() {
    this.c.execute("res\\test\\layer\\test.txt", this.m);
    assertEquals(2, this.m.getLayerNames().size());
    this.m.setCurrentLayer(0);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    assertEquals(1, this.m.getCurrentLayer().getImage().getHeight());
    assertEquals(1, this.m.getCurrentLayer().getImage().getWidth());
    assertEquals("res\\test\\layer\\test", this.m.getCurrentLayer().getName());
    this.m.setCurrentLayer(1);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    assertEquals(1, this.m.getCurrentLayer().getImage().getHeight());
    assertEquals(1, this.m.getCurrentLayer().getImage().getWidth());
    assertEquals("res\\test\\layer\\test2", this.m.getCurrentLayer().getName());
  }
}
