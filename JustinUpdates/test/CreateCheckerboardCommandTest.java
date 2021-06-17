import static org.junit.Assert.assertEquals;

import controller.commands.CreateCheckerboardCommand;
import java.awt.Color;
import java.util.Scanner;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.programmatic.CreateCheckerboard;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the class CreateCheckerboardCommand.
 */
public class CreateCheckerboardCommandTest {

  private ImageLayerModel model;
  private ImageLayerModel altModel;

  private CreateCheckerboardCommand checkerboard;

  @Before
  public void init() {
    this.model = new ImageLayerModelImpl();
    this.altModel = new ImageLayerModelImpl();

    this.checkerboard = new CreateCheckerboardCommand();

    this.model.addLayer("one");
    this.altModel.addLayer("one");

    this.model.setCurrentLayer(0);
    this.altModel.setCurrentLayer(0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createCheckerboardScannerCannotBeNull() {
    checkerboard.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createCheckerboardModelCannotBeNull() {
    checkerboard.execute(new Scanner("16 4 black blue"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createCheckerboardSizeNotAnInt() {
    checkerboard.execute(new Scanner("sixteen 4 black blue"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createCheckerboardNumSquaresNotAnInt() {
    checkerboard.execute(new Scanner("16 four black blue"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createCheckerboardColorOneNotSupported() {
    checkerboard.execute(new Scanner("16 4 neon blue"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createCheckerboardColorTwoNotSupported() {
    checkerboard.execute(new Scanner("16 4 black neon"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createCheckerboardNotEnoughArgumentsProvided() {
    checkerboard.execute(new Scanner("16 4 black"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createCheckerboardNoArgumentsProvided() {
    checkerboard.execute(new Scanner(""), this.model);
  }

  @Test
  public void createCheckerboardCheckerboardCreatedSuccessfully() {
    checkerboard.execute(new Scanner("16 4 black blue"), this.model);

    this.altModel.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.BLACK, Color.BLUE));

    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getRed(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getRed());
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getGreen(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getGreen());
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getBlue(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getBlue());
      }
    }
  }

  @Test
  public void createCheckerboardCheckerboardCreatedSuccessfullyOppositeOrderColors() {
    checkerboard.execute(new Scanner("16 4 blue black"), this.model);

    this.altModel.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.BLUE, Color.BLACK));

    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getRed(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getRed());
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getGreen(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getGreen());
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getBlue(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getBlue());
      }
    }
  }

  @Test
  public void createCheckerboardExtraInputAfterArgumentsIgnored() {
    checkerboard.execute(new Scanner("16 4 blue black stuff"), this.model);

    this.altModel.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.BLUE, Color.BLACK));

    for (int i = 0; i < 16; i++) {
      for (int j = 0; j < 16; j++) {
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getRed(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getRed());
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getGreen(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getGreen());
        assertEquals(this.model.getCurrentLayer().getImage().getPixelAt(i, j).getBlue(),
            this.altModel.getCurrentLayer().getImage().getPixelAt(i, j).getBlue());
      }
    }
  }
}