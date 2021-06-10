import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageProcessingModel;
import model.image.ImageProcessingModelImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import model.image.programmatic.CreateCheckerboard;
import model.image.programmatic.ProgrammaticCreator;
import model.operation.Operations;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ImageProcessingModelImpl class.
 */
public class ImageProcessingModelImplTest {

  private ImageProcessingModel m1;
  private Pixel p1;
  private Image i1;
  private Image i2;
  private ImageProcessingModel model;
  private Image steadilyIncreasingColors;
  private Image blackPixelsInImage;
  private Image toBeClamped;
  private Image maxColor;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.m1 = new ImageProcessingModelImpl();
    this.p1 = new PixelImpl(10, 10, 10);

    List<List<Pixel>> l1 = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      List<Pixel> row1 = new ArrayList<>();
      for (int j = 0; j < 5; j++) {
        row1.add(this.p1);
      }
      l1.add(row1);
    }
    this.i1 = new ImageImpl(l1);

    List<List<Pixel>> l2 = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      List<Pixel> row2 = new ArrayList<>();
      for (int j = 0; j < 2; j++) {
        row2.add(new PixelImpl(255, 100, 0));
      }
      l2.add(row2);
    }
    this.i2 = new ImageImpl(l2);

    this.model = new ImageProcessingModelImpl();

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

    this.maxColor = new ImageImpl(Arrays.asList(
        new ArrayList<>(Collections.singletonList(
            new PixelImpl(240, 242, 246))),
        new ArrayList<>(Collections.singletonList(
            new PixelImpl(255, 255, 255)))));
  }

  @Test
  public void applyOperationSepiaClampedButNoWayToClampBlue() {
    assertEquals(240, this.toBeClamped.getPixelAt(0, 0).getRed());
    assertEquals(242, this.toBeClamped.getPixelAt(0, 0).getGreen());
    assertEquals(246, this.toBeClamped.getPixelAt(0, 0).getBlue());

    assertEquals(255, this.toBeClamped.getPixelAt(1, 0).getRed());
    assertEquals(255, this.toBeClamped.getPixelAt(1, 0).getGreen());
    assertEquals(255, this.toBeClamped.getPixelAt(1, 0).getBlue());

    Image modifiedToBeClamped = this.model
        .applyOperation(this.toBeClamped, Operations.SEPIA);

    assertEquals(255, modifiedToBeClamped.getPixelAt(0, 0).getRed());
    assertEquals(255, modifiedToBeClamped.getPixelAt(0, 0).getGreen());
    assertEquals(228, modifiedToBeClamped.getPixelAt(0, 0).getBlue());

    assertEquals(255, modifiedToBeClamped.getPixelAt(1, 0).getRed());
    assertEquals(255, modifiedToBeClamped.getPixelAt(1, 0).getGreen());
    assertEquals(238, modifiedToBeClamped.getPixelAt(1, 0).getBlue());
  }

  @Test
  public void applyOperationSepiaTwice() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.model.applyOperation(this.steadilyIncreasingColors, Operations.SEPIA),
            Operations.SEPIA);

    assertEquals(8, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(7, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(6, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(18, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(16, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(12, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(27, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(24, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(19, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(36, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(32, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(25, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(151, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(134, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(104, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(160, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(142, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(169, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(150, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(178, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(158, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(253, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(197, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(201, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(205, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(209, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void applyOperationMonochromeAdjustsPixelsArraySizeDoesNotChange() {
    assertEquals(3, this.steadilyIncreasingColors.getWidth());
    assertEquals(4, this.steadilyIncreasingColors.getHeight());

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.steadilyIncreasingColors, Operations.MONOCHROME);

    assertEquals(3, modifiedSteadilyIncreasingColors.getWidth());
    assertEquals(4, modifiedSteadilyIncreasingColors.getHeight());
  }

  @Test
  public void applyOperationMonochromeAdjustsPixels() {

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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.steadilyIncreasingColors, Operations.MONOCHROME);

    assertEquals(5, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(5, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(5, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(11, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(11, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(11, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(17, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(17, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(17, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(105, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(105, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(105, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(205, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(205, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(205, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(211, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(211, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(211, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(217, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(217, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(217, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(223, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(223, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(223, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void applyOperationMonochromeNoEffectOnBlack() {
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

    Image modifiedBlackPixelsInImage = this.model
        .applyOperation(this.blackPixelsInImage, Operations.MONOCHROME);

    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getRed());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getGreen());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getBlue());

    assertEquals(1, modifiedBlackPixelsInImage.getPixelAt(0, 1).getRed());
    assertEquals(1, modifiedBlackPixelsInImage.getPixelAt(0, 1).getGreen());
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
  public void monochromeCannotClampEvenAtMax() {
    assertEquals(240, this.maxColor.getPixelAt(0, 0).getRed());
    assertEquals(242, this.maxColor.getPixelAt(0, 0).getGreen());
    assertEquals(246, this.maxColor.getPixelAt(0, 0).getBlue());

    assertEquals(255, this.maxColor.getPixelAt(1, 0).getRed());
    assertEquals(255, this.maxColor.getPixelAt(1, 0).getGreen());
    assertEquals(255, this.maxColor.getPixelAt(1, 0).getBlue());

    Image modifiedMaxColor = this.model
        .applyOperation(this.maxColor, Operations.MONOCHROME);

    assertEquals(241, modifiedMaxColor.getPixelAt(0, 0).getRed());
    assertEquals(241, modifiedMaxColor.getPixelAt(0, 0).getGreen());
    assertEquals(241, modifiedMaxColor.getPixelAt(0, 0).getBlue());

    assertEquals(254, modifiedMaxColor.getPixelAt(1, 0).getRed());
    assertEquals(254, modifiedMaxColor.getPixelAt(1, 0).getGreen());
    assertEquals(254, modifiedMaxColor.getPixelAt(1, 0).getBlue());
  }

  @Test
  public void applyOperationMonochromeTwice() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(
            this.model.applyOperation(this.steadilyIncreasingColors, Operations.MONOCHROME),
            Operations.MONOCHROME);

    assertEquals(4, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(4, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(4, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(11, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(11, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(11, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(17, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(17, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(17, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(104, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(104, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(104, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(204, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(204, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(204, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(210, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(210, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(210, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(217, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(217, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(217, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(223, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(223, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(223, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  // test that a given image is blurred correctly and does not mutate the original image
  @Test
  public void testApplyBlur() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = this.i1.getPixelAt(i, j);
        assertEquals(10, p1.getRed());
        assertEquals(10, p1.getGreen());
        assertEquals(10, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i1, Operations.BLUR);

    int[][] expectedBlurValues = {
        {6, 8, 8, 8, 6},
        {8, 10, 10, 10, 8},
        {8, 10, 10, 10, 8},
        {8, 10, 10, 10, 8},
        {6, 8, 8, 8, 6}};
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        Pixel pix = this.i1.getPixelAt(i, j);
        assertEquals(10, pix.getRed());
        assertEquals(10, pix.getGreen());
        assertEquals(10, pix.getBlue());

        this.p1 = img.getPixelAt(i, j);
        int expectedBlurValue = expectedBlurValues[i][j];
        assertEquals(expectedBlurValue, p1.getRed());
        assertEquals(expectedBlurValue, p1.getGreen());
        assertEquals(expectedBlurValue, p1.getBlue());
      }
    }
  }

  // test that a given image can be blurred multiple times
  @Test
  public void testApplyMultipleBlur() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = this.i1.getPixelAt(i, j);
        assertEquals(10, p1.getRed());
        assertEquals(10, p1.getGreen());
        assertEquals(10, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i1, Operations.BLUR);

    int[][] expectedBlurValues = {
        {6, 8, 8, 8, 6},
        {8, 10, 10, 10, 8},
        {8, 10, 10, 10, 8},
        {8, 10, 10, 10, 8},
        {6, 8, 8, 8, 6}};
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = img.getPixelAt(i, j);
        int expectedBlurValue = expectedBlurValues[i][j];
        assertEquals(expectedBlurValue, p1.getRed());
        assertEquals(expectedBlurValue, p1.getGreen());
        assertEquals(expectedBlurValue, p1.getBlue());
      }
    }
    Image img2 = this.m1.applyOperation(img, Operations.BLUR);

    int[][] expectedBlurValues2 = {
        {4, 6, 7, 6, 4},
        {6, 9, 10, 9, 6},
        {7, 10, 10, 10, 7},
        {6, 9, 10, 9, 6},
        {4, 6, 7, 6, 4}};
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = img2.getPixelAt(i, j);
        int expectedBlurValue = expectedBlurValues2[i][j];
        assertEquals(expectedBlurValue, p1.getRed());
        assertEquals(expectedBlurValue, p1.getGreen());
        assertEquals(expectedBlurValue, p1.getBlue());
      }
    }
  }

  // test that a given image is sharpened correctly and does not mutate the original image
  @Test
  public void testApplySharpen() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = this.i1.getPixelAt(i, j);
        assertEquals(10, p1.getRed());
        assertEquals(10, p1.getGreen());
        assertEquals(10, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i1, Operations.SHARPEN);

    int[][] expectedSharpenValues = {
        {11, 15, 11, 15, 11},
        {15, 21, 16, 21, 15},
        {11, 16, 10, 16, 11},
        {15, 21, 16, 21, 15},
        {11, 15, 11, 15, 11}};
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        Pixel pix = this.i1.getPixelAt(i, j);
        assertEquals(10, pix.getRed());
        assertEquals(10, pix.getGreen());
        assertEquals(10, pix.getBlue());

        this.p1 = img.getPixelAt(i, j);
        int expectedSharpenValue = expectedSharpenValues[i][j];
        assertEquals(expectedSharpenValue, p1.getRed());
        assertEquals(expectedSharpenValue, p1.getGreen());
        assertEquals(expectedSharpenValue, p1.getBlue());
      }
    }
  }

  // test that a given image can be sharpened multiple times
  @Test
  public void testApplyMultipleSharpen() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = this.i1.getPixelAt(i, j);
        assertEquals(10, p1.getRed());
        assertEquals(10, p1.getGreen());
        assertEquals(10, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i1, Operations.SHARPEN);

    int[][] expectedSharpenValues = {
        {11, 15, 11, 15, 11},
        {15, 21, 16, 21, 15},
        {11, 16, 10, 16, 11},
        {15, 21, 16, 21, 15},
        {11, 15, 11, 15, 11}};
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = img.getPixelAt(i, j);
        int expectedSharpenValue = expectedSharpenValues[i][j];
        assertEquals(expectedSharpenValue, p1.getRed());
        assertEquals(expectedSharpenValue, p1.getGreen());
        assertEquals(expectedSharpenValue, p1.getBlue());
      }
    }
    Image img2 = this.m1.applyOperation(img, Operations.SHARPEN);

    int[][] expectedSharpenValues2 = {
        {16, 22, 19, 22, 16},
        {22, 32, 27, 32, 22},
        {19, 27, 21, 27, 19},
        {22, 32, 27, 32, 22},
        {16, 22, 19, 22, 16}};
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = img2.getPixelAt(i, j);
        int expectedSharpenValue = expectedSharpenValues2[i][j];
        assertEquals(expectedSharpenValue, p1.getRed());
        assertEquals(expectedSharpenValue, p1.getGreen());
        assertEquals(expectedSharpenValue, p1.getBlue());
      }
    }
  }

  @Test
  public void createProgrammaticImageThreeByThreeCheckerboard() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(3, 9, Color.WHITE,
        Color.BLACK);

    Image checkerboard = this.model.createProgrammaticImage(checkerboardCreator);

    assertEquals(3, checkerboard.getWidth());
    assertEquals(3, checkerboard.getHeight());

    assertEquals(255, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(0, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(255, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getBlue());

    assertEquals(255, checkerboard.getPixelAt(2, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(2, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getBlue());

    this.maxColor = new ImageImpl(Arrays.asList(
        new ArrayList<>(Collections.singletonList(
            new PixelImpl(240, 242, 246))),
        new ArrayList<>(Collections.singletonList(
            new PixelImpl(255, 255, 255)))));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createProgrammaticImageCheckerboardColorOneCannotBeNull() {
    this.model.createProgrammaticImage(new CreateCheckerboard(3, 9, null, Color.BLACK));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createProgrammaticImageCheckerboardColorTwoCannotBeNull() {
    this.model.createProgrammaticImage(new CreateCheckerboard(3, 9, Color.WHITE, null));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createProgrammaticImageCheckerboardSizeMustBePositive() {
    this.model.createProgrammaticImage(new CreateCheckerboard(0, 9, Color.WHITE, Color.BLACK));
  }

  @Test(expected = IllegalArgumentException.class)
  public void createProgrammaticImageCheckerboardNumSquaresMustBePositive() {
    this.model.createProgrammaticImage(new CreateCheckerboard(3, -1, Color.WHITE, Color.BLACK));
  }

  @Test
  public void createProgrammaticImageCheckerboardThreeByThreeBoard() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(3, 9, Color.WHITE,
        Color.BLACK);

    Image checkerboard = this.model.createProgrammaticImage(checkerboardCreator);

    assertEquals(3, checkerboard.getWidth());
    assertEquals(3, checkerboard.getHeight());

    assertEquals(255, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(0, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(255, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getBlue());

    assertEquals(255, checkerboard.getPixelAt(2, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(2, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void createProgrammaticImageCheckerboardSizeIsRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(5, 9, Color.BLUE, Color.GREEN);

    Image checkerboard = this.model.createProgrammaticImage(checkerboardCreator);

    assertEquals(3, checkerboard.getWidth());
    assertEquals(3, checkerboard.getHeight());

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void createProgrammaticImageCheckerboardNumSquaresIsRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(3, 15, Color.BLUE,
        Color.GREEN);

    Image checkerboard = this.model.createProgrammaticImage(checkerboardCreator);

    assertEquals(3, checkerboard.getWidth());
    assertEquals(3, checkerboard.getHeight());

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void createProgrammaticImageCheckerboardBothSizeAndNumSquaresRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(5, 15, Color.BLUE,
        Color.GREEN);

    Image checkerboard = this.model.createProgrammaticImage(checkerboardCreator);

    assertEquals(3, checkerboard.getWidth());
    assertEquals(3, checkerboard.getHeight());

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void createProgrammaticImageCheckerboardMultiplePixelsPerSquare() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(4, 4, Color.BLUE, Color.GREEN);

    Image checkerboard = this.model.createProgrammaticImage(checkerboardCreator);

    assertEquals(4, checkerboard.getWidth());
    assertEquals(4, checkerboard.getHeight());

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 3).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 3).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 3).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(0, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 3).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 3).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 3).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 3).getRed());
    assertEquals(0, checkerboard.getPixelAt(2, 3).getGreen());
    assertEquals(255, checkerboard.getPixelAt(2, 3).getBlue());

    assertEquals(0, checkerboard.getPixelAt(3, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(3, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(3, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(3, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(3, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(3, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(3, 2).getRed());
    assertEquals(0, checkerboard.getPixelAt(3, 2).getGreen());
    assertEquals(255, checkerboard.getPixelAt(3, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(3, 3).getRed());
    assertEquals(0, checkerboard.getPixelAt(3, 3).getGreen());
    assertEquals(255, checkerboard.getPixelAt(3, 3).getBlue());
  }

  @Test
  public void createProgrammaticImageCheckerboardBothColorsTheSame() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(4, 4, Color.GREEN,
        Color.GREEN);

    Image checkerboard = this.model.createProgrammaticImage(checkerboardCreator);

    assertEquals(4, checkerboard.getWidth());
    assertEquals(4, checkerboard.getHeight());

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(0, 3).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 3).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 3).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 3).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 3).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 3).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(2, 3).getRed());
    assertEquals(255, checkerboard.getPixelAt(2, 3).getGreen());
    assertEquals(0, checkerboard.getPixelAt(2, 3).getBlue());

    assertEquals(0, checkerboard.getPixelAt(3, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(3, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(3, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(3, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(3, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(3, 1).getBlue());

    assertEquals(0, checkerboard.getPixelAt(3, 2).getRed());
    assertEquals(255, checkerboard.getPixelAt(3, 2).getGreen());
    assertEquals(0, checkerboard.getPixelAt(3, 2).getBlue());

    assertEquals(0, checkerboard.getPixelAt(3, 3).getRed());
    assertEquals(255, checkerboard.getPixelAt(3, 3).getGreen());
    assertEquals(0, checkerboard.getPixelAt(3, 3).getBlue());
  }

  @Test
  public void createProgrammaticImageCheckerboardOneByOne() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(1, 1, Color.GREEN, Color.RED);

    Image checkerboard = this.model.createProgrammaticImage(checkerboardCreator);

    assertEquals(1, checkerboard.getWidth());
    assertEquals(1, checkerboard.getHeight());

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 0).getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateProgrammaticImageNull() {
    this.model.createProgrammaticImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void applyOperationImageIsNull() {
    this.model.applyOperation(null, Operations.BLUR);
  }

  @Test(expected = IllegalArgumentException.class)
  public void applyOperationOperationIsNull() {
    this.model.applyOperation(this.steadilyIncreasingColors, null);
  }

  @Test
  public void applyOperationSepiaAdjustsPixelsArraySizeDoesNotChange() {
    assertEquals(3, this.steadilyIncreasingColors.getWidth());
    assertEquals(4, this.steadilyIncreasingColors.getHeight());

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.steadilyIncreasingColors, Operations.SEPIA);

    assertEquals(3, modifiedSteadilyIncreasingColors.getWidth());
    assertEquals(4, modifiedSteadilyIncreasingColors.getHeight());
  }

  @Test
  public void applyOperationSepiaAdjustsPixels() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.steadilyIncreasingColors, Operations.SEPIA);

    assertEquals(8, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(7, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(6, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(17, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(15, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(12, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(26, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(18, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(34, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(30, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(24, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(143, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(128, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(99, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(152, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(135, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(105, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(161, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(143, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(169, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(151, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(248, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(193, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(199, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(205, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(211, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void applyOperationSepiaNoEffectOnBlack() {
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

    Image modifiedBlackPixelsInImage = this.model
        .applyOperation(this.blackPixelsInImage, Operations.SEPIA);

    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getRed());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getGreen());
    assertEquals(0, modifiedBlackPixelsInImage.getPixelAt(0, 0).getBlue());

    assertEquals(3, modifiedBlackPixelsInImage.getPixelAt(0, 1).getRed());
    assertEquals(2, modifiedBlackPixelsInImage.getPixelAt(0, 1).getGreen());
    assertEquals(2, modifiedBlackPixelsInImage.getPixelAt(0, 1).getBlue());

    assertEquals(2, modifiedBlackPixelsInImage.getPixelAt(1, 0).getRed());
    assertEquals(1, modifiedBlackPixelsInImage.getPixelAt(1, 0).getGreen());
    assertEquals(1, modifiedBlackPixelsInImage.getPixelAt(1, 0).getBlue());

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
  public void applyOperationSepiaThenMonochrome() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.model
                .applyOperation(this.steadilyIncreasingColors, Operations.SEPIA),
            Operations.MONOCHROME);

    assertEquals(7, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(7, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(7, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(15, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(15, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(15, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(30, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(30, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(30, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(129, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(129, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(129, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(136, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(136, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(136, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(144, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(144, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(144, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(152, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(152, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(152, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(245, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(245, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(245, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(250, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(250, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(250, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(251, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(251, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(251, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(251, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(251, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(251, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void applyOperationSepiaThenBlur() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.model
                .applyOperation(this.steadilyIncreasingColors, Operations.SEPIA),
            Operations.BLUR);

    assertEquals(32, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(28, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(22, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(47, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(41, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(32, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(53, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(47, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(37, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(43, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(38, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(30, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(105, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(98, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(76, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(144, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(135, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(105, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(151, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(141, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(116, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(109, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(86, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(118, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(92, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(166, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(160, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(126, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(168, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(163, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(130, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(127, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(123, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(100, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void applyOperationSepiaThenSharpen() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.model
                .applyOperation(this.steadilyIncreasingColors, Operations.SEPIA),
            Operations.SHARPEN);

    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(13, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(6, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(240, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(186, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(220, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(250, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void applyOperationMonochromeThenSepia() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.model
                .applyOperation(this.steadilyIncreasingColors, Operations.MONOCHROME),
            Operations.SEPIA);

    assertEquals(6, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(6, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(4, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(14, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(13, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(10, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(22, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(20, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(15, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(31, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(27, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(21, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(141, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(126, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(98, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(149, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(133, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(104, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(158, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(140, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(109, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(166, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(147, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(115, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(246, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(192, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(253, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(197, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(203, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(208, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void applyOperationMonochromeThenBlur() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.model
                .applyOperation(this.steadilyIncreasingColors, Operations.MONOCHROME),
            Operations.BLUR);

    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(23, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(33, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(33, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(33, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(38, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(38, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(38, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(31, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(31, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(31, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(80, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(80, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(80, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(111, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(117, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(91, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(91, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(91, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(98, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(98, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(98, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(133, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(133, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(133, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(138, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(138, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(138, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(106, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(106, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(106, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  @Test
  public void applyOperationMonochromeThenSharpen() {
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

    Image modifiedSteadilyIncreasingColors = this.model
        .applyOperation(this.model
                .applyOperation(this.steadilyIncreasingColors, Operations.MONOCHROME),
            Operations.SHARPEN);

    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getRed());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getGreen());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 0).getBlue());

    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getRed());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getGreen());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 1).getBlue());

    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getRed());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getGreen());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 2).getBlue());

    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getRed());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getGreen());
    assertEquals(0, modifiedSteadilyIncreasingColors.getPixelAt(0, 3).getBlue());

    assertEquals(197, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getRed());
    assertEquals(197, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getGreen());
    assertEquals(197, modifiedSteadilyIncreasingColors.getPixelAt(1, 0).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 1).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(1, 2).getBlue());

    assertEquals(231, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getRed());
    assertEquals(231, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getGreen());
    assertEquals(231, modifiedSteadilyIncreasingColors.getPixelAt(1, 3).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 0).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 1).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 2).getBlue());

    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getRed());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getGreen());
    assertEquals(255, modifiedSteadilyIncreasingColors.getPixelAt(2, 3).getBlue());
  }

  // test that a given image can be correctly processed using blur then sepia
  @Test
  public void testApplyBlurSepia() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        this.p1 = this.i2.getPixelAt(i, j);
        assertEquals(255, p1.getRed());
        assertEquals(100, p1.getGreen());
        assertEquals(0, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i2, Operations.BLUR);

    assertEquals(143, img.getPixelAt(0, 0).getRed());
    assertEquals(56, img.getPixelAt(0, 0).getGreen());
    assertEquals(0, img.getPixelAt(0, 0).getBlue());

    assertEquals(143, img.getPixelAt(0, 1).getRed());
    assertEquals(56, img.getPixelAt(0, 1).getGreen());
    assertEquals(0, img.getPixelAt(0, 1).getBlue());

    assertEquals(143, img.getPixelAt(1, 0).getRed());
    assertEquals(56, img.getPixelAt(1, 0).getGreen());
    assertEquals(0, img.getPixelAt(1, 0).getBlue());

    assertEquals(143, img.getPixelAt(1, 1).getRed());
    assertEquals(56, img.getPixelAt(1, 1).getGreen());
    assertEquals(0, img.getPixelAt(1, 1).getBlue());

    Image img2 = this.m1.applyOperation(img, Operations.SEPIA);

    assertEquals(56, img2.getPixelAt(0, 0).getRed());
    assertEquals(49, img2.getPixelAt(0, 0).getGreen());
    assertEquals(38, img2.getPixelAt(0, 0).getBlue());

    assertEquals(56, img2.getPixelAt(0, 1).getRed());
    assertEquals(49, img2.getPixelAt(0, 1).getGreen());
    assertEquals(38, img2.getPixelAt(0, 1).getBlue());

    assertEquals(56, img2.getPixelAt(1, 0).getRed());
    assertEquals(49, img2.getPixelAt(1, 0).getGreen());
    assertEquals(38, img2.getPixelAt(1, 0).getBlue());

    assertEquals(56, img2.getPixelAt(1, 1).getRed());
    assertEquals(49, img2.getPixelAt(1, 1).getGreen());
    assertEquals(38, img2.getPixelAt(1, 1).getBlue());
  }

  // test that a given image can be correctly processed using blur then monochrome
  @Test
  public void testApplyBlurMonochrome() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        this.p1 = this.i2.getPixelAt(i, j);
        assertEquals(255, p1.getRed());
        assertEquals(100, p1.getGreen());
        assertEquals(0, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i2, Operations.BLUR);

    assertEquals(143, img.getPixelAt(0, 0).getRed());
    assertEquals(56, img.getPixelAt(0, 0).getGreen());
    assertEquals(0, img.getPixelAt(0, 0).getBlue());

    assertEquals(143, img.getPixelAt(0, 1).getRed());
    assertEquals(56, img.getPixelAt(0, 1).getGreen());
    assertEquals(0, img.getPixelAt(0, 1).getBlue());

    assertEquals(143, img.getPixelAt(1, 0).getRed());
    assertEquals(56, img.getPixelAt(1, 0).getGreen());
    assertEquals(0, img.getPixelAt(1, 0).getBlue());

    assertEquals(143, img.getPixelAt(1, 1).getRed());
    assertEquals(56, img.getPixelAt(1, 1).getGreen());
    assertEquals(0, img.getPixelAt(1, 1).getBlue());

    Image img2 = this.m1.applyOperation(img, Operations.MONOCHROME);

    assertEquals(70, img2.getPixelAt(0, 0).getRed());
    assertEquals(70, img2.getPixelAt(0, 0).getGreen());
    assertEquals(70, img2.getPixelAt(0, 0).getBlue());

    assertEquals(70, img2.getPixelAt(0, 1).getRed());
    assertEquals(70, img2.getPixelAt(0, 1).getGreen());
    assertEquals(70, img2.getPixelAt(0, 1).getBlue());

    assertEquals(70, img2.getPixelAt(1, 0).getRed());
    assertEquals(70, img2.getPixelAt(1, 0).getGreen());
    assertEquals(70, img2.getPixelAt(1, 0).getBlue());

    assertEquals(70, img2.getPixelAt(1, 1).getRed());
    assertEquals(70, img2.getPixelAt(1, 1).getGreen());
    assertEquals(70, img2.getPixelAt(1, 1).getBlue());
  }

  // test that a given image can be correctly processed using blur then sharpen
  @Test
  public void testApplyBlurSharpen() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        this.p1 = this.i2.getPixelAt(i, j);
        assertEquals(255, p1.getRed());
        assertEquals(100, p1.getGreen());
        assertEquals(0, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i2, Operations.BLUR);

    assertEquals(143, img.getPixelAt(0, 0).getRed());
    assertEquals(56, img.getPixelAt(0, 0).getGreen());
    assertEquals(0, img.getPixelAt(0, 0).getBlue());

    assertEquals(143, img.getPixelAt(0, 1).getRed());
    assertEquals(56, img.getPixelAt(0, 1).getGreen());
    assertEquals(0, img.getPixelAt(0, 1).getBlue());

    assertEquals(143, img.getPixelAt(1, 0).getRed());
    assertEquals(56, img.getPixelAt(1, 0).getGreen());
    assertEquals(0, img.getPixelAt(1, 0).getBlue());

    assertEquals(143, img.getPixelAt(1, 1).getRed());
    assertEquals(56, img.getPixelAt(1, 1).getGreen());
    assertEquals(0, img.getPixelAt(1, 1).getBlue());

    Image img2 = this.m1.applyOperation(img, Operations.SHARPEN);

    assertEquals(250, img2.getPixelAt(0, 0).getRed());
    assertEquals(98, img2.getPixelAt(0, 0).getGreen());
    assertEquals(0, img2.getPixelAt(0, 0).getBlue());

    assertEquals(250, img2.getPixelAt(0, 1).getRed());
    assertEquals(98, img2.getPixelAt(0, 1).getGreen());
    assertEquals(0, img2.getPixelAt(0, 1).getBlue());

    assertEquals(250, img2.getPixelAt(1, 0).getRed());
    assertEquals(98, img2.getPixelAt(1, 0).getGreen());
    assertEquals(0, img2.getPixelAt(1, 0).getBlue());

    assertEquals(250, img2.getPixelAt(1, 1).getRed());
    assertEquals(98, img2.getPixelAt(1, 1).getGreen());
    assertEquals(0, img2.getPixelAt(1, 1).getBlue());
  }

  // test that a given image can be correctly processed using sharpen then sepia
  @Test
  public void testApplySharpenSepia() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        this.p1 = this.i2.getPixelAt(i, j);
        assertEquals(255, p1.getRed());
        assertEquals(100, p1.getGreen());
        assertEquals(0, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i2, Operations.SHARPEN);

    assertEquals(255, img.getPixelAt(0, 0).getRed());
    assertEquals(175, img.getPixelAt(0, 0).getGreen());
    assertEquals(0, img.getPixelAt(0, 0).getBlue());

    assertEquals(255, img.getPixelAt(0, 1).getRed());
    assertEquals(175, img.getPixelAt(0, 1).getGreen());
    assertEquals(0, img.getPixelAt(0, 1).getBlue());

    assertEquals(255, img.getPixelAt(1, 0).getRed());
    assertEquals(175, img.getPixelAt(1, 0).getGreen());
    assertEquals(0, img.getPixelAt(1, 0).getBlue());

    assertEquals(255, img.getPixelAt(1, 1).getRed());
    assertEquals(175, img.getPixelAt(1, 1).getGreen());
    assertEquals(0, img.getPixelAt(1, 1).getBlue());

    Image img2 = this.m1.applyOperation(img, Operations.SEPIA);

    assertEquals(100, img2.getPixelAt(0, 0).getRed());
    assertEquals(88, img2.getPixelAt(0, 0).getGreen());
    assertEquals(69, img2.getPixelAt(0, 0).getBlue());

    assertEquals(100, img2.getPixelAt(0, 1).getRed());
    assertEquals(88, img2.getPixelAt(0, 1).getGreen());
    assertEquals(69, img2.getPixelAt(0, 1).getBlue());

    assertEquals(100, img2.getPixelAt(1, 0).getRed());
    assertEquals(88, img2.getPixelAt(1, 0).getGreen());
    assertEquals(69, img2.getPixelAt(1, 0).getBlue());

    assertEquals(100, img2.getPixelAt(1, 1).getRed());
    assertEquals(88, img2.getPixelAt(1, 1).getGreen());
    assertEquals(69, img2.getPixelAt(1, 1).getBlue());
  }

  // test that a given image can be correctly processed using sharpen then monochrome
  @Test
  public void testApplySharpenMonochrome() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        this.p1 = this.i2.getPixelAt(i, j);
        assertEquals(255, p1.getRed());
        assertEquals(100, p1.getGreen());
        assertEquals(0, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i2, Operations.SHARPEN);

    assertEquals(255, img.getPixelAt(0, 0).getRed());
    assertEquals(175, img.getPixelAt(0, 0).getGreen());
    assertEquals(0, img.getPixelAt(0, 0).getBlue());

    assertEquals(255, img.getPixelAt(0, 1).getRed());
    assertEquals(175, img.getPixelAt(0, 1).getGreen());
    assertEquals(0, img.getPixelAt(0, 1).getBlue());

    assertEquals(255, img.getPixelAt(1, 0).getRed());
    assertEquals(175, img.getPixelAt(1, 0).getGreen());
    assertEquals(0, img.getPixelAt(1, 0).getBlue());

    assertEquals(255, img.getPixelAt(1, 1).getRed());
    assertEquals(175, img.getPixelAt(1, 1).getGreen());
    assertEquals(0, img.getPixelAt(1, 1).getBlue());

    Image img2 = this.m1.applyOperation(img, Operations.MONOCHROME);

    assertEquals(179, img2.getPixelAt(0, 0).getRed());
    assertEquals(179, img2.getPixelAt(0, 0).getGreen());
    assertEquals(179, img2.getPixelAt(0, 0).getBlue());

    assertEquals(179, img2.getPixelAt(0, 1).getRed());
    assertEquals(179, img2.getPixelAt(0, 1).getGreen());
    assertEquals(179, img2.getPixelAt(0, 1).getBlue());

    assertEquals(179, img2.getPixelAt(1, 0).getRed());
    assertEquals(179, img2.getPixelAt(1, 0).getGreen());
    assertEquals(179, img2.getPixelAt(1, 0).getBlue());

    assertEquals(179, img2.getPixelAt(1, 1).getRed());
    assertEquals(179, img2.getPixelAt(1, 1).getGreen());
    assertEquals(179, img2.getPixelAt(1, 1).getBlue());
  }

  // test that a given image can be correctly processed using sharpen then blur
  @Test
  public void testApplySharpenBlur() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        this.p1 = this.i2.getPixelAt(i, j);
        assertEquals(255, p1.getRed());
        assertEquals(100, p1.getGreen());
        assertEquals(0, p1.getBlue());
      }
    }
    Image img = this.m1.applyOperation(this.i2, Operations.SHARPEN);

    assertEquals(255, img.getPixelAt(0, 0).getRed());
    assertEquals(175, img.getPixelAt(0, 0).getGreen());
    assertEquals(0, img.getPixelAt(0, 0).getBlue());

    assertEquals(255, img.getPixelAt(0, 1).getRed());
    assertEquals(175, img.getPixelAt(0, 1).getGreen());
    assertEquals(0, img.getPixelAt(0, 1).getBlue());

    assertEquals(255, img.getPixelAt(1, 0).getRed());
    assertEquals(175, img.getPixelAt(1, 0).getGreen());
    assertEquals(0, img.getPixelAt(1, 0).getBlue());

    assertEquals(255, img.getPixelAt(1, 1).getRed());
    assertEquals(175, img.getPixelAt(1, 1).getGreen());
    assertEquals(0, img.getPixelAt(1, 1).getBlue());

    Image img2 = this.m1.applyOperation(img, Operations.BLUR);
    assertEquals(143, img2.getPixelAt(0, 0).getRed());
    assertEquals(98, img2.getPixelAt(0, 0).getGreen());
    assertEquals(0, img2.getPixelAt(0, 0).getBlue());

    assertEquals(143, img2.getPixelAt(0, 1).getRed());
    assertEquals(98, img2.getPixelAt(0, 1).getGreen());
    assertEquals(0, img2.getPixelAt(0, 1).getBlue());

    assertEquals(143, img2.getPixelAt(1, 0).getRed());
    assertEquals(98, img2.getPixelAt(1, 0).getGreen());
    assertEquals(0, img2.getPixelAt(1, 0).getBlue());

    assertEquals(143, img2.getPixelAt(1, 1).getRed());
    assertEquals(98, img2.getPixelAt(1, 1).getGreen());
    assertEquals(0, img2.getPixelAt(1, 1).getBlue());
  }

  // test that a null file cannot be read
  @Test(expected = IllegalArgumentException.class)
  public void testImportNull() {
    this.m1.importImage(null);
  }

  // test that a file that cannot be found cannot be read
  @Test(expected = IllegalArgumentException.class)
  public void testImportFake() {
    this.m1.importImage("");
  }

  // test that a file type that isn't included in the map cannot be read
  @Test(expected = IllegalArgumentException.class)
  public void testImportInvalidFileType() {
    this.m1.importImage("res\\test\\test.txt");
  }

  // test that an invalid PPM file cannot be read
  @Test(expected = IllegalArgumentException.class)
  public void testImportBadPPM() {
    this.m1.importImage("res\\test\\test_bad.ppm");
  }

  // test that a valid PPM is imported properly and a corresponding Image object is created
  @Test
  public void testImportPPM() {
    Image img = this.m1.importImage("res\\test\\test_good.ppm");
    assertNotNull(img);
    assertTrue(img instanceof ImageImpl);
    assertEquals(ImageImpl.class, img.getClass());
    assertEquals(1, img.getHeight());
    assertEquals(1, img.getWidth());
  }

  // test that a null file cannot be written
  @Test(expected = IllegalArgumentException.class)
  public void testExportNullFile() {
    this.m1.exportImage(null, this.i1);
  }

  // test that a null image cannot be written
  @Test(expected = IllegalArgumentException.class)
  public void testExportNullImage() {
    this.m1.exportImage("res\\test\\test_export.ppm", null);
  }

  // test that a valid image is able to be exported properly to a valid file path
  @Test
  public void testExportPPM() {
    this.m1.exportImage("res\\test\\test_export.ppm", this.i1);
    Image img = this.m1.importImage("res\\test\\test_export.ppm");
    assertNotNull(img);
    assertTrue(img instanceof ImageImpl);
    assertEquals(ImageImpl.class, img.getClass());
  }

  // test that a valid image overwrites an existing image at the valid file path
  @Test
  public void testExportPPMOverwrite() {
    this.m1.exportImage("res\\test\\test_export.ppm", this.i1);
    Image img = this.m1.importImage("res\\test\\test_export.ppm");
    assertNotNull(img);
    assertTrue(img instanceof ImageImpl);
    assertEquals(ImageImpl.class, img.getClass());
    this.m1.exportImage("res\\test\\test_export.ppm", this.i1);
    Image img2 = this.m1.importImage("res\\test\\test_export.ppm");
    assertNotNull(img2);
    assertTrue(img2 instanceof ImageImpl);
    assertEquals(ImageImpl.class, img2.getClass());
  }
}
