import static org.junit.Assert.assertTrue;

import controller.commands.Command;
import controller.commands.ExportAllCommand;
import controller.commands.ImportCommand;
import java.util.Scanner;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ExportAllCommand class.
 */
public class ExportAllCommandTest {

  private Command c;
  private ImageLayerModel m;
  private Scanner s;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.c = new ExportAllCommand();
    this.m = new ImageLayerModelImpl();
    this.s = new Scanner("");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportNullScanner() {
    this.c.execute(null, this.m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportNullModel() {
    this.c.execute(this.s, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFileSpec() {
    this.c.execute(this.s, this.m);
  }

  @Test
  public void testExportAllLayers() {
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    Command i = new ImportCommand();
    i.execute(new Scanner("res//test//layer//test.jpg jpg"), this.m);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    this.m.addLayer("2");
    this.m.setCurrentLayer(1);
    i.execute(new Scanner("res//test//layer//test2.jpg jpg"), this.m);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    this.s = new Scanner("res//test//layer//test jpg");
    this.c.execute(this.s, this.m);
  }
}
