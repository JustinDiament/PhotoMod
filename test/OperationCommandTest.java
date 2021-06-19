import static org.junit.Assert.assertEquals;

import controller.commands.BlurCommand;
import controller.commands.MonochromeCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import java.awt.Color;
import java.util.Scanner;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.programmatic.CreateCheckerboard;
import model.operation.Operations;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the abstract class OperationCommand's child classes, including BlurCommand,
 * SharpenCommand, SepiaCommand, MonochromeCommand.
 */
public class OperationCommandTest {

  private ImageLayerModel model;
  private ImageLayerModel altModel;

  @Before
  public void init() {
    this.model = new ImageLayerModelImpl();
    this.altModel = new ImageLayerModelImpl();

    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.CYAN));

    this.altModel.addLayer("one");
    this.altModel.setCurrentLayer(0);
    this.altModel.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.CYAN));
  }

  @Test
  public void testBlurCommandCompletesBlur() {
    BlurCommand blur = new BlurCommand();
    blur.execute(new Scanner(""), this.model);

    this.altModel.applyOperation(this.altModel.getCurrentLayer().getImage(), Operations.BLUR);

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
  public void testBlurCommandDoesNotCareAboutScannerContentSoTestResultIsSameAsAbove() {
    BlurCommand blur = new BlurCommand();
    blur.execute(new Scanner("stuff"), this.model);

    this.altModel.applyOperation(this.altModel.getCurrentLayer().getImage(), Operations.BLUR);

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


  @Test(expected = IllegalArgumentException.class)
  public void testBlurCommandScannerCannotBeNull() {
    BlurCommand blur = new BlurCommand();
    blur.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlurCommandModelCannotBeNull() {
    BlurCommand blur = new BlurCommand();
    blur.execute(new Scanner(""), null);
  }

  @Test
  public void testSharpenCommandCompletesSharpen() {
    SharpenCommand sharpen = new SharpenCommand();
    sharpen.execute(new Scanner(""), this.model);

    this.altModel.applyOperation(this.altModel.getCurrentLayer().getImage(), Operations.SHARPEN);

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
  public void testSharpenCommandDoesNotCareAboutScannerContentSoTestResultIsSameAsAbove() {
    SharpenCommand sharpen = new SharpenCommand();
    sharpen.execute(new Scanner("stuff"), this.model);

    this.altModel.applyOperation(this.altModel.getCurrentLayer().getImage(), Operations.SHARPEN);

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

  @Test(expected = IllegalArgumentException.class)
  public void testSharpenCommandScannerCannotBeNull() {
    SharpenCommand sharpen = new SharpenCommand();
    sharpen.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSharpenCommandModelCannotBeNull() {
    SharpenCommand sharpen = new SharpenCommand();
    sharpen.execute(new Scanner("stuff"), null);
  }

  @Test(expected = IllegalStateException.class)
  public void testOperationWhenLayerIsBad() {
    ImageLayerModel badModel = new ImageLayerModelImpl();
    badModel.addLayer("one");

    MonochromeCommand monochrome = new MonochromeCommand();
    monochrome.execute(new Scanner(""), badModel);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOperationWhenLayerHasNoImage() {
    ImageLayerModel badModel = new ImageLayerModelImpl();
    badModel.addLayer("one");
    badModel.setCurrentLayer(0);

    MonochromeCommand monochrome = new MonochromeCommand();
    monochrome.execute(new Scanner(""), badModel);
  }

  @Test
  public void testMonochromeCommandCompletesMonochrome() {
    MonochromeCommand monochrome = new MonochromeCommand();
    monochrome.execute(new Scanner(""), this.model);

    this.altModel.applyOperation(this.altModel.getCurrentLayer().getImage(), Operations.MONOCHROME);

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
  public void testMonochromeCommandDoesNotCareAboutScannerContentSoTestResultIsSameAsAbove() {
    MonochromeCommand monochrome = new MonochromeCommand();
    monochrome.execute(new Scanner("stuff"), this.model);

    this.altModel.applyOperation(this.altModel.getCurrentLayer().getImage(), Operations.MONOCHROME);

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

  @Test(expected = IllegalArgumentException.class)
  public void testMonochromeCommandScannerCannotBeNull() {
    MonochromeCommand monochrome = new MonochromeCommand();
    monochrome.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testMonochromeCommandModelCannotBeNull() {
    MonochromeCommand monochrome = new MonochromeCommand();
    monochrome.execute(new Scanner(""), null);
  }

  @Test
  public void testSepiaCommandCompletesSepia() {
    SepiaCommand sepia = new SepiaCommand();
    sepia.execute(new Scanner(""), this.model);

    this.altModel.applyOperation(this.altModel.getCurrentLayer().getImage(), Operations.SEPIA);

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
  public void testSepiaCommandDoesNotCareAboutScannerContentSoTestResultIsSameAsAbove() {
    SepiaCommand sepia = new SepiaCommand();
    sepia.execute(new Scanner("stuff"), this.model);

    this.altModel.applyOperation(this.altModel.getCurrentLayer().getImage(), Operations.SEPIA);

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

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaCommandScannerCannotBeNull() {
    MonochromeCommand monochrome = new MonochromeCommand();
    monochrome.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaCommandModelCannotBeNull() {
    MonochromeCommand monochrome = new MonochromeCommand();
    monochrome.execute(new Scanner(""), null);
  }
}
