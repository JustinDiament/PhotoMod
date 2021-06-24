import static org.junit.Assert.assertEquals;

import controller.commands.Command;
import controller.commands.DownscaleCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.PixelImpl;
import model.image.file.JPEG;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DownscaleCommand class.
 */
public class DownscaleCommandTest {

  private Command downscale;
  private Image image1;
  private Image image2;
  private ImageLayerModel model;

  @Before
  public void init() {
    this.downscale = new DownscaleCommand();

    this.model = new ImageLayerModelImpl();

    JPEG jpg = new JPEG();
    this.image1 = jpg.importFile("res//test//quetzal//quetzaljpg.jpg");

    this.image2 = new ImageImpl(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            new PixelImpl(5, 6, 7),
            new PixelImpl(10, 12, 14),
            new PixelImpl(15, 18, 21))),
        new ArrayList<>(Arrays.asList(
            new PixelImpl(105, 106, 107),
            new PixelImpl(110, 112, 114),
            new PixelImpl(115, 118, 121))),
        new ArrayList<>(Arrays.asList(
            new PixelImpl(205, 206, 207),
            new PixelImpl(210, 212, 214),
            new PixelImpl(215, 218, 221)))));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testScannerCannotBeNull() {
    this.downscale.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModeCannotBeNull() {
    this.downscale.execute(new Scanner("5 5"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testXScaleNotInteger() {
    this.downscale.execute(new Scanner("yo 5"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testYScaleNotInteger() {
    this.downscale.execute(new Scanner("5 no"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScannerEmpty() {
    this.downscale.execute(new Scanner(""), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testScannerOnlyOneProvided() {
    this.downscale.execute(new Scanner("5"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalXScale() {
    this.downscale.execute(new Scanner("110 5"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIllegalYScale() {
    this.downscale.execute(new Scanner("5 110"), this.model);
  }

  @Test
  public void testDownscaleOneLayer() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.setCurrentLayerImage(image1);

    assertEquals(77, this.model.getCurrentLayerImage().getWidth());
    assertEquals(102, this.model.getCurrentLayerImage().getHeight());

    this.downscale.execute(new Scanner("30 45"), this.model);

    assertEquals(45, this.model.getCurrentLayerImage().getHeight());
    assertEquals(23, this.model.getCurrentLayerImage().getWidth());
  }

  @Test
  public void testDownscaleMultipleLayers() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(0);
    this.model.setCurrentLayerImage(image1);
    this.model.setCurrentLayer(1);
    this.model.setCurrentLayerImage(new ImageImpl(image1));

    assertEquals(77, this.model.getCurrentLayerImage().getWidth());
    assertEquals(102, this.model.getCurrentLayerImage().getHeight());

    this.model.setCurrentLayer(0);
    assertEquals(77, this.model.getCurrentLayerImage().getWidth());
    assertEquals(102, this.model.getCurrentLayerImage().getHeight());

    this.downscale.execute(new Scanner("30 45"), this.model);

    assertEquals(23, this.model.getCurrentLayerImage().getWidth());
    assertEquals(45, this.model.getCurrentLayerImage().getHeight());

    this.model.setCurrentLayer(1);
    assertEquals(23, this.model.getCurrentLayerImage().getWidth());
    assertEquals(45, this.model.getCurrentLayerImage().getHeight());
  }

  @Test
  public void testDownscaleNullImageInLayerDoesNotCauseIssue() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.addLayer("three");
    this.model.setCurrentLayer(0);
    this.model.setCurrentLayerImage(image1);
    this.model.setCurrentLayer(1);
    this.model.setCurrentLayerImage(new ImageImpl(image1));

    assertEquals(77, this.model.getCurrentLayerImage().getWidth());
    assertEquals(102, this.model.getCurrentLayerImage().getHeight());

    this.model.setCurrentLayer(0);
    assertEquals(77, this.model.getCurrentLayerImage().getWidth());
    assertEquals(102, this.model.getCurrentLayerImage().getHeight());

    this.downscale.execute(new Scanner("30 45"), this.model);

    assertEquals(23, this.model.getCurrentLayerImage().getWidth());
    assertEquals(45, this.model.getCurrentLayerImage().getHeight());

    this.model.setCurrentLayer(1);
    assertEquals(23, this.model.getCurrentLayerImage().getWidth());
    assertEquals(45, this.model.getCurrentLayerImage().getHeight());
  }

  @Test
  public void testDownscaleCurrentLayerIsMaintained() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.addLayer("three");
    this.model.setCurrentLayer(0);
    this.model.setCurrentLayerImage(image1);
    this.model.setCurrentLayer(1);
    this.model.setCurrentLayerImage(new ImageImpl(image1));

    assertEquals("two", this.model.getCurrentLayerName());

    this.downscale.execute(new Scanner("30 45"), this.model);

    assertEquals("two", this.model.getCurrentLayerName());
  }

  @Test
  public void testDownscalePixelsProperlyChanged() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.addLayer("three");
    this.model.setCurrentLayer(0);
    this.model.setCurrentLayerImage(image2);
    this.model.setCurrentLayer(1);
    this.model.setCurrentLayerImage(new ImageImpl(image2));

    assertEquals(5, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(6, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(7, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(10, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(12, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(14, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(15, this.model.getPixelInCurrentLayerAt(0, 2).getRed());
    assertEquals(18, this.model.getPixelInCurrentLayerAt(0, 2).getGreen());
    assertEquals(21, this.model.getPixelInCurrentLayerAt(0, 2).getBlue());

    assertEquals(105, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(106, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(107, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(110, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(112, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(114, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    assertEquals(115, this.model.getPixelInCurrentLayerAt(1, 2).getRed());
    assertEquals(118, this.model.getPixelInCurrentLayerAt(1, 2).getGreen());
    assertEquals(121, this.model.getPixelInCurrentLayerAt(1, 2).getBlue());

    assertEquals(205, this.model.getPixelInCurrentLayerAt(2, 0).getRed());
    assertEquals(206, this.model.getPixelInCurrentLayerAt(2, 0).getGreen());
    assertEquals(207, this.model.getPixelInCurrentLayerAt(2, 0).getBlue());

    assertEquals(210, this.model.getPixelInCurrentLayerAt(2, 1).getRed());
    assertEquals(212, this.model.getPixelInCurrentLayerAt(2, 1).getGreen());
    assertEquals(214, this.model.getPixelInCurrentLayerAt(2, 1).getBlue());

    assertEquals(215, this.model.getPixelInCurrentLayerAt(2, 2).getRed());
    assertEquals(218, this.model.getPixelInCurrentLayerAt(2, 2).getGreen());
    assertEquals(221, this.model.getPixelInCurrentLayerAt(2, 2).getBlue());

    assertEquals(3, this.model.getCurrentLayerImage().getWidth());
    assertEquals(3, this.model.getCurrentLayerImage().getHeight());

    this.model.setCurrentLayer(0);

    assertEquals(5, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(6, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(7, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(10, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(12, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(14, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(15, this.model.getPixelInCurrentLayerAt(0, 2).getRed());
    assertEquals(18, this.model.getPixelInCurrentLayerAt(0, 2).getGreen());
    assertEquals(21, this.model.getPixelInCurrentLayerAt(0, 2).getBlue());

    assertEquals(105, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(106, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(107, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(110, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(112, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(114, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    assertEquals(115, this.model.getPixelInCurrentLayerAt(1, 2).getRed());
    assertEquals(118, this.model.getPixelInCurrentLayerAt(1, 2).getGreen());
    assertEquals(121, this.model.getPixelInCurrentLayerAt(1, 2).getBlue());

    assertEquals(205, this.model.getPixelInCurrentLayerAt(2, 0).getRed());
    assertEquals(206, this.model.getPixelInCurrentLayerAt(2, 0).getGreen());
    assertEquals(207, this.model.getPixelInCurrentLayerAt(2, 0).getBlue());

    assertEquals(210, this.model.getPixelInCurrentLayerAt(2, 1).getRed());
    assertEquals(212, this.model.getPixelInCurrentLayerAt(2, 1).getGreen());
    assertEquals(214, this.model.getPixelInCurrentLayerAt(2, 1).getBlue());

    assertEquals(215, this.model.getPixelInCurrentLayerAt(2, 2).getRed());
    assertEquals(218, this.model.getPixelInCurrentLayerAt(2, 2).getGreen());
    assertEquals(221, this.model.getPixelInCurrentLayerAt(2, 2).getBlue());

    assertEquals(3, this.model.getCurrentLayerImage().getWidth());
    assertEquals(3, this.model.getCurrentLayerImage().getHeight());

    this.downscale.execute(new Scanner("85 75"), this.model);

    assertEquals(5, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(6, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(7, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(10, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(12, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(14, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(105, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(106, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(107, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(162, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(165, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(167, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    assertEquals(2, this.model.getCurrentLayerImage().getWidth());
    assertEquals(2, this.model.getCurrentLayerImage().getHeight());

    this.model.setCurrentLayer(1);

    assertEquals(5, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(6, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(7, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(10, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(12, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(14, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(105, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(106, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(107, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(162, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(165, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(167, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    assertEquals(2, this.model.getCurrentLayerImage().getWidth());
    assertEquals(2, this.model.getCurrentLayerImage().getHeight());
  }

}