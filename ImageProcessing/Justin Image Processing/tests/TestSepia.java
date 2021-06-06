import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;

public class TestSepia {

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
        new ArrayList<>(Arrays.asList(
            new PixelImpl(240, 242, 246))),
        new ArrayList<>(Arrays.asList(
            new PixelImpl(255, 255, 255)))));

    this.sepiaOperation = new SepiaOperation();
  }

  @Test(expected = IllegalArgumentException.class)
  public void sepiaImageCannotBeNull() {
    this.sepiaOperation.apply(null);
  }

  @Test
  public void sepiaAdjustsPixelsArraySizeDoesNotChange() {
    this.sepiaOperation.apply(this.steadilyIncreasingColors);

    assertEquals(3, this.steadilyIncreasingColors.getWidth());
    assertEquals(4, this.steadilyIncreasingColors.getHeight());

  }

  @Test
  public void sepiaAdjustsPixels() {
    this.sepiaOperation.apply(this.steadilyIncreasingColors);

    assertEquals(8, this.steadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(7, this.steadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(6, this.steadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(17, this.steadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(15, this.steadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(12, this.steadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(26, this.steadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(23, this.steadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(18, this.steadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(34, this.steadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(30, this.steadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(24, this.steadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(143, this.steadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(128, this.steadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(99, this.steadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(152, this.steadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(135, this.steadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(105, this.steadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(161, this.steadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(143, this.steadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(111, this.steadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(169, this.steadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(151, this.steadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(117, this.steadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(255, this.steadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(248, this.steadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(193, this.steadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(255, this.steadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(255, this.steadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(199, this.steadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(255, this.steadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(255, this.steadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(205, this.steadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(255, this.steadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(255, this.steadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(211, this.steadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void sepiaNoEffectOnBlack() {
    this.sepiaOperation.apply(this.blackPixelsInImage);

    assertEquals(0, this.blackPixelsInImage.getPixelAt(0, 0).getRed());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(0, 0).getGreen());
    assertEquals(0, this.blackPixelsInImage.getPixelAt(0, 0).getBlue());

    assertEquals(3, this.blackPixelsInImage.getPixelAt(0, 1).getRed());
    assertEquals(2, this.blackPixelsInImage.getPixelAt(0, 1).getGreen());
    assertEquals(2, this.blackPixelsInImage.getPixelAt(0, 1).getBlue());

    assertEquals(2, this.blackPixelsInImage.getPixelAt(1, 0).getRed());
    assertEquals(1, this.blackPixelsInImage.getPixelAt(1, 0).getGreen());
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
  }


  @Test
  public void sepiaClampedButNoWayToClampBlue() {
    this.sepiaOperation.apply(this.toBeClamped);

    assertEquals(255, this.toBeClamped.getPixelAt(0, 0).getRed());
    assertEquals(255, this.toBeClamped.getPixelAt(0, 0).getGreen());
    assertEquals(228, this.toBeClamped.getPixelAt(0, 0).getBlue());

    assertEquals(255, this.toBeClamped.getPixelAt(1, 0).getRed());
    assertEquals(255, this.toBeClamped.getPixelAt(1, 0).getGreen());
    assertEquals(238, this.toBeClamped.getPixelAt(1, 0).getBlue());

  }
}
