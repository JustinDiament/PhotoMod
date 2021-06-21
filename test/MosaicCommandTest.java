import static org.junit.Assert.assertEquals;

import controller.commands.Command;
import controller.commands.MosaicCommand;
import java.awt.Color;
import java.util.Scanner;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.programmatic.CreateCheckerboard;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MosaicCommand class.
 */
public class MosaicCommandTest {

  private ImageLayerModel m;
  private Command c;
  private Scanner s;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.m = new ImageLayerModelImpl();
    this.c = new MosaicCommand();
    this.s = new Scanner("");

    this.m.addLayer("one");
    this.m.setCurrentLayer(0);
    this.m.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.CYAN));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExecuteNullScanner() {
    this.c.execute(null, this.m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExecuteNullModel() {
    this.c.execute(this.s, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExecuteSeedNotInt() {
    this.s = new Scanner("a");
    this.c.execute(this.s, this.m);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExecuteSeedNegativeInt() {
    this.s = new Scanner("-1");
    this.c.execute(this.s, this.m);
  }

  @Test
  public void testExecute() {
    this.s = new Scanner("1");
    this.c.execute(this.s, this.m);

    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        assertEquals(127, this.m.getCurrentLayerImage().getPixelAt(i, j).getRed());
        assertEquals(227, this.m.getCurrentLayerImage().getPixelAt(i, j).getGreen());
        assertEquals(127, this.m.getCurrentLayerImage().getPixelAt(i, j).getBlue());
      }
    }
  }
}
