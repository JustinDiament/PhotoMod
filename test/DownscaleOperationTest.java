import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import model.image.Image;
import model.image.ImageImpl;
import model.image.PixelImpl;
import model.image.file.JPEG;
import model.operation.DownscaleOperation;
import model.operation.ImageOperation;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the DownscaleOperation class.
 */
public class DownscaleOperationTest {

  private ImageOperation downscale;
  private Image image;
  private Image imported;

  @Before
  public void init() {
    this.image = new ImageImpl(Arrays.asList(
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

    JPEG jpg = new JPEG();

    this.imported = jpg.importFile("res//test//quetzal//quetzaljpg.jpg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testXScaleCannotBeLessOrEqualToZero() {
    new DownscaleOperation(0, 0.5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testYScaleCannotBeLessOrEqualToZero() {
    new DownscaleOperation(0.5, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testYScaleCannotBeGreaterThanOne() {
    new DownscaleOperation(0.5, 1.01);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testXScaleCannotBeGreaterThanOne() {
    new DownscaleOperation(1.01, 0.5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageCannotBeNull() {
    this.downscale = new DownscaleOperation(1.01, 0.5);

    this.downscale.apply(null);
  }

  @Test
  public void testDownscaleXAndYScalesAreEqual() {
    this.downscale = new DownscaleOperation(0.7, 0.7);

    assertEquals(5, this.image.getPixelAt(0, 0).getRed());
    assertEquals(6, this.image.getPixelAt(0, 0).getGreen());
    assertEquals(7, this.image.getPixelAt(0, 0).getBlue());

    assertEquals(10, this.image.getPixelAt(0, 1).getRed());
    assertEquals(12, this.image.getPixelAt(0, 1).getGreen());
    assertEquals(14, this.image.getPixelAt(0, 1).getBlue());

    assertEquals(15, this.image.getPixelAt(0, 2).getRed());
    assertEquals(18, this.image.getPixelAt(0, 2).getGreen());
    assertEquals(21, this.image.getPixelAt(0, 2).getBlue());

    assertEquals(105, this.image.getPixelAt(1, 0).getRed());
    assertEquals(106, this.image.getPixelAt(1, 0).getGreen());
    assertEquals(107, this.image.getPixelAt(1, 0).getBlue());

    assertEquals(110, this.image.getPixelAt(1, 1).getRed());
    assertEquals(112, this.image.getPixelAt(1, 1).getGreen());
    assertEquals(114, this.image.getPixelAt(1, 1).getBlue());

    assertEquals(115, this.image.getPixelAt(1, 2).getRed());
    assertEquals(118, this.image.getPixelAt(1, 2).getGreen());
    assertEquals(121, this.image.getPixelAt(1, 2).getBlue());

    assertEquals(205, this.image.getPixelAt(2, 0).getRed());
    assertEquals(206, this.image.getPixelAt(2, 0).getGreen());
    assertEquals(207, this.image.getPixelAt(2, 0).getBlue());

    assertEquals(210, this.image.getPixelAt(2, 1).getRed());
    assertEquals(212, this.image.getPixelAt(2, 1).getGreen());
    assertEquals(214, this.image.getPixelAt(2, 1).getBlue());

    assertEquals(215, this.image.getPixelAt(2, 2).getRed());
    assertEquals(218, this.image.getPixelAt(2, 2).getGreen());
    assertEquals(221, this.image.getPixelAt(2, 2).getBlue());

    assertEquals(3, this.image.getWidth());
    assertEquals(3, this.image.getHeight());

    Image downscaled = this.downscale.apply(this.image);

    assertEquals(5, downscaled.getPixelAt(0, 0).getRed());
    assertEquals(6, downscaled.getPixelAt(0, 0).getGreen());
    assertEquals(7, downscaled.getPixelAt(0, 0).getBlue());

    assertEquals(10, downscaled.getPixelAt(0, 1).getRed());
    assertEquals(12, downscaled.getPixelAt(0, 1).getGreen());
    assertEquals(14, downscaled.getPixelAt(0, 1).getBlue());

    assertEquals(105, downscaled.getPixelAt(1, 0).getRed());
    assertEquals(106, downscaled.getPixelAt(1, 0).getGreen());
    assertEquals(107, downscaled.getPixelAt(1, 0).getBlue());

    assertEquals(162, downscaled.getPixelAt(1, 1).getRed());
    assertEquals(165, downscaled.getPixelAt(1, 1).getGreen());
    assertEquals(167, downscaled.getPixelAt(1, 1).getBlue());

    assertEquals(2, downscaled.getWidth());
    assertEquals(2, downscaled.getHeight());
  }

  @Test
  public void testDownscaleXAndYScalesAreNotEqual() {
    this.downscale = new DownscaleOperation(0.7, 0.4);

    assertEquals(5, this.image.getPixelAt(0, 0).getRed());
    assertEquals(6, this.image.getPixelAt(0, 0).getGreen());
    assertEquals(7, this.image.getPixelAt(0, 0).getBlue());

    assertEquals(10, this.image.getPixelAt(0, 1).getRed());
    assertEquals(12, this.image.getPixelAt(0, 1).getGreen());
    assertEquals(14, this.image.getPixelAt(0, 1).getBlue());

    assertEquals(15, this.image.getPixelAt(0, 2).getRed());
    assertEquals(18, this.image.getPixelAt(0, 2).getGreen());
    assertEquals(21, this.image.getPixelAt(0, 2).getBlue());

    assertEquals(105, this.image.getPixelAt(1, 0).getRed());
    assertEquals(106, this.image.getPixelAt(1, 0).getGreen());
    assertEquals(107, this.image.getPixelAt(1, 0).getBlue());

    assertEquals(110, this.image.getPixelAt(1, 1).getRed());
    assertEquals(112, this.image.getPixelAt(1, 1).getGreen());
    assertEquals(114, this.image.getPixelAt(1, 1).getBlue());

    assertEquals(115, this.image.getPixelAt(1, 2).getRed());
    assertEquals(118, this.image.getPixelAt(1, 2).getGreen());
    assertEquals(121, this.image.getPixelAt(1, 2).getBlue());

    assertEquals(205, this.image.getPixelAt(2, 0).getRed());
    assertEquals(206, this.image.getPixelAt(2, 0).getGreen());
    assertEquals(207, this.image.getPixelAt(2, 0).getBlue());

    assertEquals(210, this.image.getPixelAt(2, 1).getRed());
    assertEquals(212, this.image.getPixelAt(2, 1).getGreen());
    assertEquals(214, this.image.getPixelAt(2, 1).getBlue());

    assertEquals(215, this.image.getPixelAt(2, 2).getRed());
    assertEquals(218, this.image.getPixelAt(2, 2).getGreen());
    assertEquals(221, this.image.getPixelAt(2, 2).getBlue());

    assertEquals(3, this.image.getWidth());
    assertEquals(3, this.image.getHeight());

    Image downscaled = this.downscale.apply(this.image);

    assertEquals(5, downscaled.getPixelAt(0, 0).getRed());
    assertEquals(6, downscaled.getPixelAt(0, 0).getGreen());
    assertEquals(7, downscaled.getPixelAt(0, 0).getBlue());

    assertEquals(105, downscaled.getPixelAt(1, 0).getRed());
    assertEquals(106, downscaled.getPixelAt(1, 0).getGreen());
    assertEquals(107, downscaled.getPixelAt(1, 0).getBlue());

    assertEquals(2, downscaled.getWidth());
    assertEquals(1, downscaled.getHeight());
  }

  @Test
  public void testDownscaleXAndYHalfScaleWorksEvenThoughFloorEqualsCeil() {
    this.downscale = new DownscaleOperation(0.5, 0.5);

    assertEquals(5, this.image.getPixelAt(0, 0).getRed());
    assertEquals(6, this.image.getPixelAt(0, 0).getGreen());
    assertEquals(7, this.image.getPixelAt(0, 0).getBlue());

    assertEquals(10, this.image.getPixelAt(0, 1).getRed());
    assertEquals(12, this.image.getPixelAt(0, 1).getGreen());
    assertEquals(14, this.image.getPixelAt(0, 1).getBlue());

    assertEquals(15, this.image.getPixelAt(0, 2).getRed());
    assertEquals(18, this.image.getPixelAt(0, 2).getGreen());
    assertEquals(21, this.image.getPixelAt(0, 2).getBlue());

    assertEquals(105, this.image.getPixelAt(1, 0).getRed());
    assertEquals(106, this.image.getPixelAt(1, 0).getGreen());
    assertEquals(107, this.image.getPixelAt(1, 0).getBlue());

    assertEquals(110, this.image.getPixelAt(1, 1).getRed());
    assertEquals(112, this.image.getPixelAt(1, 1).getGreen());
    assertEquals(114, this.image.getPixelAt(1, 1).getBlue());

    assertEquals(115, this.image.getPixelAt(1, 2).getRed());
    assertEquals(118, this.image.getPixelAt(1, 2).getGreen());
    assertEquals(121, this.image.getPixelAt(1, 2).getBlue());

    assertEquals(205, this.image.getPixelAt(2, 0).getRed());
    assertEquals(206, this.image.getPixelAt(2, 0).getGreen());
    assertEquals(207, this.image.getPixelAt(2, 0).getBlue());

    assertEquals(210, this.image.getPixelAt(2, 1).getRed());
    assertEquals(212, this.image.getPixelAt(2, 1).getGreen());
    assertEquals(214, this.image.getPixelAt(2, 1).getBlue());

    assertEquals(215, this.image.getPixelAt(2, 2).getRed());
    assertEquals(218, this.image.getPixelAt(2, 2).getGreen());
    assertEquals(221, this.image.getPixelAt(2, 2).getBlue());

    assertEquals(3, this.image.getWidth());
    assertEquals(3, this.image.getHeight());

    Image downscaled = this.downscale.apply(this.image);

    assertEquals(5, downscaled.getPixelAt(0, 0).getRed());
    assertEquals(6, downscaled.getPixelAt(0, 0).getGreen());
    assertEquals(7, downscaled.getPixelAt(0, 0).getBlue());

    assertEquals(1, downscaled.getWidth());
    assertEquals(1, downscaled.getHeight());
  }

  @Test
  public void testDownscaleToCorrectSizeOnImportScalesDifferent() {
    this.downscale = new DownscaleOperation(0.32, 0.85);

    assertEquals(77, this.imported.getWidth());
    assertEquals(102, this.imported.getHeight());

    Image downscaled = this.downscale.apply(this.imported);

    assertEquals(24, downscaled.getWidth());
    assertEquals(86, downscaled.getHeight());
  }

  @Test
  public void testDownscaleToCorrectSizeOnImportScalesSame() {
    this.downscale = new DownscaleOperation(0.45, 0.45);

    assertEquals(77, this.imported.getWidth());
    assertEquals(102, this.imported.getHeight());

    Image downscaled = this.downscale.apply(this.imported);

    assertEquals(34, downscaled.getWidth());
    assertEquals(45, downscaled.getHeight());
  }
}