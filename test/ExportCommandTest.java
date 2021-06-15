import static org.junit.Assert.assertTrue;

import controller.commands.Command;
import controller.commands.ExportCommand;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ExportCommand class.
 */
public class ExportCommandTest {

  private Command c;
  private ImageLayerModel m;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.c = new ExportCommand();
    this.m = new ImageLayerModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportNullSpec() {
    this.c.execute(null, this.m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportNullModel() {
    this.c.execute("res\\test\\layer\\test.jpg", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFileSpec() {
    this.c.execute("", this.m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportNoVisibleLayers() {
    this.c.execute("res\\test\\layer\\test.jpg", this.m);
  }

  @Test
  public void testExportTopmostVisibleLayer() {
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    this.m.importImage("res\\test\\layer\\test.jpg");
    assertTrue(this.m.getCurrentLayer().getVisibility());
    this.c.execute("res\\test\\layer\\test.jpg", this.m);
  }
}
