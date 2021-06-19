import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import model.image.Image;
import model.image.ImageImpl;
import model.operation.ImageOperation;
import model.image.PixelImpl;
import model.operation.SepiaOperation;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the SepiaOperation class.
 */
public class SepiaOperationTest {

  private Image steadilyIncreasingColors;
  private Image toBeClamped;
  private Image blackPixelsInImage;

  private ImageOperation sepiaOperation;

  @Before
  public void init() {
    this.steadilyIncreasingColors = new ImageImpl(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            new PixelImpl(5, 6, 7),
            new PixelImpl(10, 12, 14),
            new PixelImpl(15, 18, 21),
            new PixelImpl(20, 24, 28))),
        new ArrayList<>(Arrays.asList(
            new PixelImpl(105, 106, 107),
            new PixelImpl(110, 112, 114),
            new PixelImpl(115, 118, 121),
            new PixelImpl(120, 124, 128))),
        new ArrayList<>(Arrays.asList(
            new PixelImpl(205, 206, 207),
            new PixelImpl(210, 212, 214),
            new PixelImpl(215, 218, 221),
            new PixelImpl(220, 224, 228)))));

    this.blackPixelsInImage = new ImageImpl(Arrays.asList(
        new ArrayList<>(Arrays.asList(
            new PixelImpl(0, 0, 0),
            new PixelImpl(1, 2, 3))),
        new ArrayList<>(Arrays.asList(
            new PixelImpl(3, 2, 1),
            new PixelImpl(0, 0, 0))),
        new ArrayList<>(Arrays.asList(
            new PixelImpl(0, 0, 0),
            new PixelImpl(0, 0, 0)))));

    this.toBeClamped = new ImageImpl(Arrays.asList(
        new ArrayList<>(Collections.singletonList(
            new PixelImpl(240, 242, 246))),
        new ArrayList<>(Collections.singletonList(
            new PixelImpl(255, 255, 255)))));

    this.sepiaOperation = new SepiaOperation();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sepiaImageCannotBeNull() {
    this.sepiaOperation.apply(null);
  }

  @Test
  public void sepiaAdjustsPixelsArraySizeDoesNotChange() {
    assertEquals(3, this.steadilyIncreasingColors.getWidth());
    assertEquals(4, this.steadilyIncreasingColors.getHeight());

    Image modifiedSteadilyIncreasingColors = this.sepiaOperation
        .apply(this.steadilyIncreasingColors);

    assertEquals(3, modifiedSteadilyIncreasingColors.getWidth());
    assertEquals(4, modifiedSteadilyIncreasingColors.getHeight());
  }

  @Test
  public void sepiaAdjustsPixels() {

    assertEquals(5, this.steadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(6, this.steadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(7, this.steadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(10, this.steadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(12, this.steadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(14, this.steadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(15, this.steadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(18, this.steadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(21, this.steadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(20, this.steadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(24, this.steadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(28, this.steadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(105, this.steadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(106, this.steadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(107, this.steadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(110, this.steadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(112, this.steadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(114, this.steadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(115, this.steadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(118, this.steadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(121, this.steadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(120, this.steadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(124, this.steadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(128, this.steadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(205, this.steadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(206, this.steadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(207, this.steadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(210, this.steadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(212, this.steadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(214, this.steadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(215, this.steadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(218, this.steadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(221, this.steadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(220, this.steadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(224, this.steadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(228, this.steadilyIncreasingColors.getPixelAt(2, 3).getBlue());

    Image modifiedSteadilyIncreasingColors = this.sepiaOperation
        .apply(this.steadilyIncreasingColors);

    assertEquals(7, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(7, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(5, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(15, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(14, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(10, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(21, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(16, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(31, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(28, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(21, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(143, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(127, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(99, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(150, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(134, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(104, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(158, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(141, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(110, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(166, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(148, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(115, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(247, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(192, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(254, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(198, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(203, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(209, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void sepiaNoEffectOnBlack() {
    assertEquals(0, this.blackPixelsInImage.getPixelAt(0, 0).getRed());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(0, 0).getGreen());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(0, 0).getBlue());

    assertEquals(1, this.blackPixelsInImage.getPixelAt(0, 1).getRed());
    assertEquals(2, this.blackPixelsInImage.getPixelAt(0, 1).getGreen());
    assertEquals(3, this.blackPixelsInImage.getPixelAt(0, 1).getBlue());

    assertEquals(3, this.blackPixelsInImage.getPixelAt(1, 0).getRed());
    assertEquals(2, this.blackPixelsInImage.getPixelAt(1, 0).getGreen());
    assertEquals(1, this.blackPixelsInImage.getPixelAt(1, 0).getBlue());

    assertEquals(0, this.blackPixelsInImage.getPixelAt(1, 1).getRed());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(1, 1).getGreen());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(1, 1).getBlue());

    assertEquals(0, this.blackPixelsInImage.getPixelAt(2, 0).getRed());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(2, 0).getGreen());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(2, 0).getBlue());

    assertEquals(0, this.blackPixelsInImage.getPixelAt(2, 1).getRed());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(2, 1).getGreen());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(2, 1).getBlue());

    Image modifiedBlackPixelsInImage = this.sepiaOperation.apply(this.blackPixelsInImage);

    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getRed());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getGreen());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getBlue());

    assertEquals(2, modifiedBlackPixelsInImage.getPixelAt(0, 1).getRed());
    assertEquals(2, modifiedBlackPixelsInImage.getPixelAt(0, 1).getGreen());
    assertEquals(1, modifiedBlackPixelsInImage.getPixelAt(0, 1).getBlue());

    assertEquals(2, modifiedBlackPixelsInImage.getPixelAt(1, 0).getRed());
    assertEquals(2, modifiedBlackPixelsInImage.getPixelAt(1, 0).getGreen());
    assertEquals(2, modifiedBlackPixelsInImage.getPixelAt(1, 0).getBlue());

    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(1, 1).getRed());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(1, 1).getGreen());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(1, 1).getBlue());

    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(2, 0).getRed());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(2, 0).getGreen());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(2, 0).getBlue());

    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(2, 1).getRed());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(2, 1).getGreen());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(2, 1).getBlue());
  }

  @Test
  public void sepiaClampedButNoWayToClampBlue() {
    assertEquals(240, this.toBeClamped.getPixelAt(0, 0).getRed());
    assertEquals(242, this.toBeClamped.getPixelAt(0, 0).getGreen());
    assertEquals(246, this.toBeClamped.getPixelAt(0, 0).getBlue());

    assertEquals(255, this.toBeClamped.getPixelAt(1, 0).getRed());
    assertEquals(255, this.toBeClamped.getPixelAt(1, 0).getGreen());
    assertEquals(255, this.toBeClamped.getPixelAt(1, 0).getBlue());

    Image modifiedToBeClamped = this.sepiaOperation.apply(this.toBeClamped);

    assertEquals(255, modifiedToBeClamped.getPixelAt(0, 0).getRed());
    assertEquals(255, modifiedToBeClamped.getPixelAt(0, 0).getGreen());
    assertEquals(226, modifiedToBeClamped.getPixelAt(0, 0).getBlue());

    assertEquals(255, modifiedToBeClamped.getPixelAt(1, 0).getRed());
    assertEquals(255, modifiedToBeClamped.getPixelAt(1, 0).getGreen());
    assertEquals(238, modifiedToBeClamped.getPixelAt(1, 0).getBlue());
  }
}
