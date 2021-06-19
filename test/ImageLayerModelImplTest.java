import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import model.image.programmatic.CreateCheckerboard;
import model.operation.Operations;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ImageLayerModelImpl class.
 */
public class ImageLayerModelImplTest {

  private ImageLayerModel m;
  private Image i1;
  private Image i2;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.m = new ImageLayerModelImpl();
    this.i1 = new ImageImpl(Collections.singletonList(
        Collections.singletonList(new PixelImpl(0, 0, 0))));
    this.i2 = new ImageImpl(Collections.singletonList(
        Collections.singletonList(new PixelImpl(10, 10, 10))));
  }

  @Test
  public void testConstructorEmptyLayerList() {
    assertEquals(0, this.m.getLayerNames().size());
  }

  @Test(expected = IllegalStateException.class)
  public void testConstructorCurrentLayerNegativeOne() {
    this.m.getCurrentLayer();
  }

  @Test
  public void testAddLayer() {
    this.m.addLayer("l");
    this.m.setCurrentLayer(0);
    assertNull(this.m.getCurrentLayer().getImage());
    assertEquals("l", this.m.getCurrentLayer().getName());
    assertTrue(this.m.getCurrentLayer().getVisibility());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddLayerNullName() {
    this.m.addLayer(null);
  }

  @Test
  public void testSetCurrentLayerImage() {
    this.m.addLayer("l");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(this.i1);
    assertEquals(1, this.m.getCurrentLayer().getImage().getWidth());
    assertEquals(1, this.m.getCurrentLayer().getImage().getHeight());
    assertEquals(0, this.m.getCurrentLayer().getImage().getPixelAt(0, 0).getRed());
    assertEquals(0, this.m.getCurrentLayer().getImage().getPixelAt(0, 0).getGreen());
    assertEquals(0, this.m.getCurrentLayer().getImage().getPixelAt(0, 0).getBlue());
    assertEquals("l", this.m.getCurrentLayer().getName());
    assertTrue(this.m.getCurrentLayer().getVisibility());
  }

  @Test(expected = IllegalStateException.class)
  public void testSetCurrentLayerImageNoLayers() {
    this.m.setCurrentLayerImage(this.i1);
  }

  @Test(expected = IllegalStateException.class)
  public void testSetCurrentLayerImageNull() {
    this.m.setCurrentLayerImage(null);
  }

  @Test
  public void testSetCurrentLayerValidIndexPositive() {
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    assertNull(this.m.getCurrentLayer().getImage());
    assertEquals("", this.m.getCurrentLayer().getName());
    assertTrue(this.m.getCurrentLayer().getVisibility());
  }

  @Test
  public void testSetCurrentLayerValidIndexNegativeOne() {
    this.m.setCurrentLayer(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerInvalidIndexHigh() {
    this.m.setCurrentLayer(10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerInvalidIndexLow() {
    this.m.setCurrentLayer(-10);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCurrentLayerInvalidIndex() {
    this.m.getCurrentLayer();
  }

  @Test
  public void testGetCurrentLayer() {
    this.m.addLayer("test");
    this.m.setCurrentLayer(0);
    assertEquals("test", this.m.getCurrentLayer().getName());
    assertNull(this.m.getCurrentLayer().getImage());
    assertTrue(this.m.getCurrentLayer().getVisibility());
  }

  @Test(expected = IllegalStateException.class)
  public void testSetCurrentLayerVisibilityInvalidIndex() {
    this.m.setCurrentLayerVisibility(true);
  }

  @Test
  public void testSetCurrentLayerVisibility() {
    this.m.addLayer("");
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    this.m.setCurrentLayerVisibility(false);
    assertFalse(this.m.getCurrentLayer().getVisibility());
    this.m.setCurrentLayer(1);
    assertTrue(this.m.getCurrentLayer().getVisibility());
  }

  @Test
  public void testGetLayerNamesEmpty() {
    String[] names = {};
    assertArrayEquals(names, this.m.getLayerNames().toArray(new String[0]));
    assertEquals(0, this.m.getLayerNames().size());
  }

  @Test
  public void testGetLayerNames() {
    String[] names = {"1", "2"};
    this.m.addLayer("1");
    this.m.addLayer("2");
    assertArrayEquals(names, this.m.getLayerNames().toArray(new String[0]));
    assertEquals(2, this.m.getLayerNames().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidIndex() {
    this.m.removeLayer(0);
  }

  @Test
  public void testRemoveLayer() {
    assertEquals(0, this.m.getLayerNames().size());
    this.m.addLayer("");
    this.m.addLayer("");
    assertEquals(2, this.m.getLayerNames().size());
    this.m.removeLayer(1);
    assertEquals(1, this.m.getLayerNames().size());
    this.m.removeLayer(0);
    assertEquals(0, this.m.getLayerNames().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyOperationInvalidCurrentLayer() {
    this.m.addLayer("first");

    this.m.applyOperation(
        this.m.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.BLACK, Color.WHITE)),
        Operations.BLUR);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyOperationImageIsNull() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);

    this.m.applyOperation(null, Operations.BLUR);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyOperationOperationIsNull() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);

    this.m.applyOperation(
        this.m.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.BLACK, Color.WHITE)),
        null);
  }

  @Test
  public void testApplyOperationBlurSetsAndReturns() {

    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    Image checkerboard = this.m
        .createProgrammaticImage(new CreateCheckerboard(2, 4, Color.CYAN, Color.ORANGE));

    this.m.setCurrentLayerImage(checkerboard);

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(255, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(200, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(200, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());

    Image i = this.m.applyOperation(this.m.getCurrentLayerImage(), Operations.BLUR);

    assertEquals(64, i.getPixelAt(0, 0).getRed());
    assertEquals(130, i.getPixelAt(0, 0).getGreen());
    assertEquals(80, i.getPixelAt(0, 0).getBlue());

    assertEquals(80, i.getPixelAt(0, 1).getRed());
    assertEquals(126, i.getPixelAt(0, 1).getGreen());
    assertEquals(64, i.getPixelAt(0, 1).getBlue());

    assertEquals(80, i.getPixelAt(1, 0).getRed());
    assertEquals(126, i.getPixelAt(1, 0).getGreen());
    assertEquals(64, i.getPixelAt(1, 0).getBlue());

    assertEquals(64, i.getPixelAt(1, 1).getRed());
    assertEquals(130, i.getPixelAt(1, 1).getGreen());
    assertEquals(80, i.getPixelAt(1, 1).getBlue());

    assertEquals(64, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(130, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(80, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(80, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(126, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(64, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(80, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(126, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(64, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(64, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(130, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(80, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testApplyOperationSharpenSetsAndReturns() {

    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    Image checkerboard = this.m
        .createProgrammaticImage(new CreateCheckerboard(2, 4, Color.CYAN, Color.ORANGE));

    this.m.setCurrentLayerImage(checkerboard);

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(255, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(200, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(200, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());

    Image i = this.m.applyOperation(this.m.getCurrentLayerImage(), Operations.SHARPEN);

    assertEquals(128, i.getPixelAt(0, 0).getRed());
    assertEquals(255, i.getPixelAt(0, 0).getGreen());
    assertEquals(255, i.getPixelAt(0, 0).getBlue());

    assertEquals(255, i.getPixelAt(0, 1).getRed());
    assertEquals(255, i.getPixelAt(0, 1).getGreen());
    assertEquals(128, i.getPixelAt(0, 1).getBlue());

    assertEquals(255, i.getPixelAt(1, 0).getRed());
    assertEquals(255, i.getPixelAt(1, 0).getGreen());
    assertEquals(128, i.getPixelAt(1, 0).getBlue());

    assertEquals(128, i.getPixelAt(1, 1).getRed());
    assertEquals(255, i.getPixelAt(1, 1).getGreen());
    assertEquals(255, i.getPixelAt(1, 1).getBlue());

    assertEquals(128, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(128, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(128, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(128, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testApplyOperationMonochromeSetsAndReturns() {

    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    Image checkerboard = this.m
        .createProgrammaticImage(new CreateCheckerboard(2, 4, Color.CYAN, Color.ORANGE));

    this.m.setCurrentLayerImage(checkerboard);

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(255, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(200, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(200, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());

    Image i = this.m.applyOperation(this.m.getCurrentLayerImage(), Operations.MONOCHROME);

    assertEquals(200, i.getPixelAt(0, 0).getRed());
    assertEquals(200, i.getPixelAt(0, 0).getGreen());
    assertEquals(200, i.getPixelAt(0, 0).getBlue());

    assertEquals(197, i.getPixelAt(0, 1).getRed());
    assertEquals(197, i.getPixelAt(0, 1).getGreen());
    assertEquals(197, i.getPixelAt(0, 1).getBlue());

    assertEquals(197, i.getPixelAt(1, 0).getRed());
    assertEquals(197, i.getPixelAt(1, 0).getGreen());
    assertEquals(197, i.getPixelAt(1, 0).getBlue());

    assertEquals(200, i.getPixelAt(1, 1).getRed());
    assertEquals(200, i.getPixelAt(1, 1).getGreen());
    assertEquals(200, i.getPixelAt(1, 1).getBlue());

    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(197, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(197, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(197, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(197, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(197, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(197, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testApplyOperationSepiaSetsAndReturns() {

    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    Image checkerboard = this.m
        .createProgrammaticImage(new CreateCheckerboard(2, 4, Color.CYAN, Color.ORANGE));

    this.m.setCurrentLayerImage(checkerboard);

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(255, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(200, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(200, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());

    Image i = this.m.applyOperation(this.m.getCurrentLayerImage(), Operations.SEPIA);

    assertEquals(244, i.getPixelAt(0, 0).getRed());
    assertEquals(217, i.getPixelAt(0, 0).getGreen());
    assertEquals(169, i.getPixelAt(0, 0).getBlue());

    assertEquals(254, i.getPixelAt(0, 1).getRed());
    assertEquals(226, i.getPixelAt(0, 1).getGreen());
    assertEquals(176, i.getPixelAt(0, 1).getBlue());

    assertEquals(254, i.getPixelAt(1, 0).getRed());
    assertEquals(226, i.getPixelAt(1, 0).getGreen());
    assertEquals(176, i.getPixelAt(1, 0).getBlue());

    assertEquals(244, i.getPixelAt(1, 1).getRed());
    assertEquals(217, i.getPixelAt(1, 1).getGreen());
    assertEquals(169, i.getPixelAt(1, 1).getBlue());

    assertEquals(244, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(217, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(169, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(254, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(226, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(176, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(254, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(226, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(176, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(244, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(217, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(169, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());
  }


  @Test(expected = IllegalArgumentException.class)
  public void testCreateProgrammaticImageCheckerboardProgrammaticCreatorIsNull() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);

    this.m.createProgrammaticImage(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testCreateProgrammaticImageCheckerboardCurrentLayerIsInvalid() {
    this.m.addLayer("first");

    this.m.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.CYAN, Color.ORANGE));
  }

  @Test
  public void testCreateProgrammaticImageCheckerboardSetsLayerAndReturns() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);

    try {
      this.m.getCurrentLayerImage();
    } catch (IllegalStateException e) {
      assertEquals("Current layer has no Image", e.getMessage());
    }

    Image checkerboard = this.m
        .createProgrammaticImage(new CreateCheckerboard(2, 4, Color.CYAN, Color.ORANGE));

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getBlue());

    assertEquals(255, checkerboard.getPixelAt(0, 1).getRed());
    assertEquals(200, checkerboard.getPixelAt(0, 1).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 1).getBlue());

    assertEquals(255, checkerboard.getPixelAt(1, 0).getRed());
    assertEquals(200, checkerboard.getPixelAt(1, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(1, 0).getBlue());

    assertEquals(0, checkerboard.getPixelAt(1, 1).getRed());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getGreen());
    assertEquals(255, checkerboard.getPixelAt(1, 1).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 0).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 0).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(0, 1).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(0, 1).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(0, 1).getBlue());

    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 0).getRed());
    assertEquals(200, this.m.getCurrentLayerImage().getPixelAt(1, 0).getGreen());
    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 0).getBlue());

    assertEquals(0, this.m.getCurrentLayerImage().getPixelAt(1, 1).getRed());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getGreen());
    assertEquals(255, this.m.getCurrentLayerImage().getPixelAt(1, 1).getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInCurrentLayerAtXIsInvalid() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(this.i2);

    this.m.getPixelInCurrentLayerAt(1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelInCurrentLayerAtYIsInvalid() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(this.i2);

    this.m.getPixelInCurrentLayerAt(0, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void testGetPixelInCurrentLayerAtNoImageInLayer() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);

    this.m.getPixelInCurrentLayerAt(0, 0);
  }

  @Test
  public void testGetPixelInCurrentLayerAtGetsPixelCorrectly() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(this.i2);

    assertEquals(10, this.m.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(10, this.m.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(10, this.m.getPixelInCurrentLayerAt(0, 0).getBlue());
  }

  @Test
  public void testGetCurrentLayerImagePixelsGetsAllPixelsCorrectly() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(
        this.m.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.BLACK, Color.WHITE)));

    List<List<Pixel>> allPixels = this.m.getCurrentLayerImagePixels();

    assertEquals(0, allPixels.get(0).get(0).getRed());
    assertEquals(0, allPixels.get(0).get(0).getGreen());
    assertEquals(0, allPixels.get(0).get(0).getBlue());

    assertEquals(255, allPixels.get(0).get(1).getRed());
    assertEquals(255, allPixels.get(0).get(1).getGreen());
    assertEquals(255, allPixels.get(0).get(1).getBlue());

    assertEquals(255, allPixels.get(1).get(0).getRed());
    assertEquals(255, allPixels.get(1).get(0).getGreen());
    assertEquals(255, allPixels.get(1).get(0).getBlue());

    assertEquals(0, allPixels.get(1).get(1).getRed());
    assertEquals(0, allPixels.get(1).get(1).getGreen());
    assertEquals(0, allPixels.get(1).get(1).getBlue());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCurrentLayerImagePixelsNoImageInLayer() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.getCurrentLayerImagePixels();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCurrentLayerImageNoImageInLayer() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.getCurrentLayerImage();
  }

  @Test
  public void testGetCurrentLayerImageGetsImageSuccessfully() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(
        this.m.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.BLACK, Color.WHITE)));

    Image image = this.m.getCurrentLayerImage();

    assertEquals(0, image.getPixelAt(0, 0).getRed());
    assertEquals(0, image.getPixelAt(0, 0).getGreen());
    assertEquals(0, image.getPixelAt(0, 0).getBlue());

    assertEquals(255, image.getPixelAt(0, 1).getRed());
    assertEquals(255, image.getPixelAt(0, 1).getGreen());
    assertEquals(255, image.getPixelAt(0, 1).getBlue());

    assertEquals(255, image.getPixelAt(1, 0).getRed());
    assertEquals(255, image.getPixelAt(1, 0).getGreen());
    assertEquals(255, image.getPixelAt(1, 0).getBlue());

    assertEquals(0, image.getPixelAt(1, 1).getRed());
    assertEquals(0, image.getPixelAt(1, 1).getGreen());
    assertEquals(0, image.getPixelAt(1, 1).getBlue());
  }

  @Test
  public void testGetCurrentLayerNameGetsLayerNameSuccessfully() {
    this.m.addLayer("first");
    this.m.addLayer("second");

    this.m.setCurrentLayer(0);
    assertEquals("first", this.m.getCurrentLayerName());

    this.m.setCurrentLayer(1);
    assertEquals("second", this.m.getCurrentLayerName());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCurrentLayerNameCurrentLayerInvalid() {
    this.m.addLayer("first");
    this.m.addLayer("second");
    this.m.getCurrentLayerName();
  }

  @Test
  public void testGetCurrentLayerVisibilityGetsLayerVisibilitySuccessfully() {
    this.m.addLayer("first");
    this.m.addLayer("second");

    this.m.setCurrentLayer(0);
    assertTrue(this.m.getCurrentLayerVisibility());

    this.m.setCurrentLayer(1);
    this.m.setCurrentLayerVisibility(false);
    assertFalse(this.m.getCurrentLayerVisibility());
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCurrentLayerVisibilityCurrentLayerInvalid() {
    this.m.addLayer("first");
    this.m.addLayer("second");
    this.m.getCurrentLayerVisibility();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCurrentLayerImageCurrentLayerInvalid() {
    this.m.addLayer("first");
    this.m.addLayer("second");
    this.m.getCurrentLayerImage();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetCurrentLayerImagePixelsCurrentLayerInvalid() {
    this.m.addLayer("first");
    this.m.addLayer("second");
    this.m.getCurrentLayerImagePixels();
  }

  @Test(expected = IllegalStateException.class)
  public void testGetPixelInCurrentLayerAtCurrentLayerInvalid() {
    this.m.addLayer("first");
    this.m.addLayer("second");
    this.m.getPixelInCurrentLayerAt(0, 0);
  }

  @Test
  public void testGetLayerImagesNoLayers() {
    Image[] images = {};
    assertArrayEquals(images, this.m.getLayerImages().toArray(new Image[0]));
  }

  @Test
  public void testGetLayerImages() {
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(this.i1);
    List<Image> i = this.m.getLayerImages();
    assertEquals(1, i.get(0).getHeight());
    assertEquals(1, i.get(0).getHeight());
    assertEquals(0, i.get(0).getPixelAt(0, 0).getRed());
    assertEquals(0, i.get(0).getPixelAt(0, 0).getGreen());
    assertEquals(0, i.get(0).getPixelAt(0, 0).getBlue());
  }

  @Test
  public void testVerifyLayerDimensionsNull() {
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    this.m.verifyLayerDimensions(null);
    assertEquals("", this.m.getCurrentLayer().getName());
  }

  @Test
  public void testVerifyLayerDimensions() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(this.i1);
    this.m.verifyLayerDimensions(this.i1);
    assertEquals(1, this.m.getCurrentLayerImage().getHeight());
    assertEquals(1, this.m.getCurrentLayerImage().getWidth());
    this.m.addLayer("second");
    this.m.setCurrentLayer(1);
    this.m.setCurrentLayerImage(this.i2);
    this.m.verifyLayerDimensions(this.i2);
    assertEquals(1, this.m.getCurrentLayerImage().getHeight());
    assertEquals(1, this.m.getCurrentLayerImage().getWidth());
  }

  @Test
  public void testGetCurrentLayerIndex() {
    assertEquals(-1, this.m.getCurrentLayerIndex());
    this.m.addLayer("");
    assertEquals(-1, this.m.getCurrentLayerIndex());
    this.m.setCurrentLayer(0);
    assertEquals(0, this.m.getCurrentLayerIndex());
    this.m.setCurrentLayer(-1);
    assertEquals(-1, this.m.getCurrentLayerIndex());
    this.m.addLayer("");
    this.m.setCurrentLayer(1);
    assertEquals(1, this.m.getCurrentLayerIndex());
  }
}
