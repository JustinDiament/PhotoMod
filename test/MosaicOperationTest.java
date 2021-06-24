import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Random;
import model.image.Image;
import model.image.file.ImageFile;
import model.image.file.PNG;
import model.operation.ImageOperation;
import model.operation.MosaicOperation;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MosaicOperation class.
 */
public class MosaicOperationTest {

  private ImageOperation m1;
  private ImageOperation m2;
  private Random r1;
  private Image i1;
  private Image i2;
  private Image i3;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.m1 = new MosaicOperation(100);
    this.r1 = new Random(5);
    this.m2 = new MosaicOperation(100, this.r1);
    ImageFile png = new PNG();
    this.i1 = png.importFile("res//test//quetzal//quetzalpng.png");
    this.i2 = null;
    this.i3 = null;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorOneNegativeSeeds() {
    new MosaicOperation(-1, this.r1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorOneNullRandom() {
    new MosaicOperation(0, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorTwoNegativeSeeds() {
    new MosaicOperation(-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyNull() {
    this.m1.apply(null);
  }

  @Test
  public void testClusterSeed() {
    assertNull(this.i2);
    this.i2 = this.m2.apply(this.i1);
    assertNotNull(this.i2);

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 4; j++) {
        assertEquals(176, this.i2.getPixelAt(i, j).getRed());
        assertEquals(171, this.i2.getPixelAt(i, j).getGreen());
        assertEquals(163, this.i2.getPixelAt(i, j).getBlue());
      }
    }
  }

  @Test
  public void testMosaicAverageColor() {
    assertNull(this.i2);
    this.i2 = this.m2.apply(this.i1);
    assertNotNull(this.i2);

    assertNotEquals(this.i2.getPixelAt(0, 0).getRed(), this.i1.getPixelAt(0, 0).getRed());
    assertNotEquals(this.i2.getPixelAt(0, 0).getGreen(), this.i1.getPixelAt(0, 0).getGreen());
    assertNotEquals(this.i2.getPixelAt(0, 0).getBlue(), this.i1.getPixelAt(0, 0).getBlue());
  }

  @Test
  public void testMosaicOneSeed() {
    this.m2 = new MosaicOperation(1);
    assertNull(this.i2);
    this.i2 = this.m2.apply(this.i1);
    assertNotNull(this.i2);

    for (int i = 0; i < this.i2.getWidth(); i++) {
      for (int j = 0; j < this.i2.getHeight(); j++) {
        assertEquals(168, this.i2.getPixelAt(i, j).getRed());
        assertEquals(157, this.i2.getPixelAt(i, j).getGreen());
        assertEquals(149, this.i2.getPixelAt(i, j).getBlue());
      }
    }
  }

  // this test has a negligible chance to fail if the unseeded random generates the exact same
  // mosaic image as the seeded random
  @Test
  public void testRandomMosaic() {
    assertNull(this.i2);
    this.i2 = this.m2.apply(this.i1);
    assertNotNull(this.i2);

    assertNull(this.i3);
    this.i3 = this.m1.apply(this.i1);
    assertNotNull(this.i3);

    int seededRandomRed = 0;
    int seededRandomGreen = 0;
    int seededRandomBlue = 0;
    int unseededRandomRed = 0;
    int unseededRandomGreen = 0;
    int unseededRandomBlue = 0;

    assertEquals(this.i2.getWidth(), this.i3.getWidth());
    assertEquals(this.i2.getHeight(), this.i3.getHeight());

    for (int i = 0; i < this.i2.getWidth(); i++) {
      for (int j = 0; j < this.i2.getHeight(); j++) {
        seededRandomRed += this.i2.getPixelAt(i, j).getRed();
        seededRandomGreen += this.i2.getPixelAt(i, j).getGreen();
        seededRandomBlue += this.i2.getPixelAt(i, j).getBlue();
        unseededRandomRed += this.i3.getPixelAt(i, j).getRed();
        unseededRandomGreen += this.i3.getPixelAt(i, j).getGreen();
        unseededRandomBlue += this.i3.getPixelAt(i, j).getBlue();
      }
    }

    assertNotEquals(seededRandomRed, unseededRandomRed);
    assertNotEquals(seededRandomGreen, unseededRandomGreen);
    assertNotEquals(seededRandomBlue, unseededRandomBlue);
  }
}
