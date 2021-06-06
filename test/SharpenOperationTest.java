import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the SharpenOperation class.
 */
public class SharpenOperationTest {

  ImageOperation s1;
  Pixel p1;
  List<List<Pixel>> l1;
  Image i1;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.s1 = new SharpenOperation();
    this.p1 = new PixelImpl(10, 10, 10);

    // initialize an image with a 5x5 2D list of Pixels with RGB values of 10
    this.l1 = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < 5; j++) {
        row.add(this.p1);
      }
      this.l1.add(row);
    }
    this.i1 = new ImageImpl(this.l1);
  }

  // test that apply cannot be given a null image
  @Test(expected = IllegalArgumentException.class)
  public void testApplyNull() {
    this.s1.apply(null);
  }

  // test that pixels are correctly sharpened when the kernel overlaps 9 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // X _ _ _ X
  // _ _ _ _ _
  // _ _ _ _ _
  // _ _ _ _ _
  // X _ _ _ X
  @Test
  public void testApplyKernelNine() {
    p1 = this.i1.getPixelAt(0, 0);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.s1.apply(this.i1);
    p1 = this.i1.getPixelAt(0, 0);
    assertEquals(11, p1.getRed());
    assertEquals(11, p1.getGreen());
    assertEquals(11, p1.getBlue());
  }

  // test that pixels are correctly sharpened when the kernel overlaps 12 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // _ X _ X _
  // X _ _ _ X
  // _ _ _ _ _
  // X _ _ _ X
  // _ X _ X _
  @Test
  public void testApplyKernelTwelve() {
    p1 = this.i1.getPixelAt(0, 1);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.s1.apply(this.i1);
    p1 = this.i1.getPixelAt(0, 1);
    assertEquals(15, p1.getRed());
    assertEquals(15, p1.getGreen());
    assertEquals(15, p1.getBlue());
  }

  // test that pixels are correctly sharpened when the kernel overlaps 15 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // _ _ X _ _
  // _ _ _ _ _
  // X _ _ _ X
  // _ _ _ _ _
  // _ _ X _ _
  @Test
  public void testApplyKernelFifteen() {
    p1 = this.i1.getPixelAt(0, 2);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.s1.apply(this.i1);
    p1 = this.i1.getPixelAt(0, 2);
    assertEquals(11, p1.getRed());
    assertEquals(11, p1.getGreen());
    assertEquals(11, p1.getBlue());
  }

  // test that pixels are correctly sharpened when the kernel overlaps 16 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // _ _ _ _ _
  // _ X _ X _
  // _ _ _ _ _
  // _ X _ X _
  // _ _ _ _ _
  @Test
  public void testApplyKernelSixteen() {
    p1 = this.i1.getPixelAt(1, 1);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.s1.apply(this.i1);
    p1 = this.i1.getPixelAt(1, 1);
    assertEquals(21, p1.getRed());
    assertEquals(21, p1.getGreen());
    assertEquals(21, p1.getBlue());
  }

  // test that pixels are correctly sharpened when the kernel overlaps 20 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // _ _ _ _ _
  // _ _ X _ _
  // _ X _ X _
  // _ _ X _ _
  // _ _ _ _ _
  @Test
  public void testApplyKernelTwenty() {
    p1 = this.i1.getPixelAt(1, 2);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.s1.apply(this.i1);
    p1 = this.i1.getPixelAt(1, 2);
    assertEquals(16, p1.getRed());
    assertEquals(16, p1.getGreen());
    assertEquals(16, p1.getBlue());
  }

  // test that pixels are correctly sharpened when the kernel overlaps 25 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // _ _ _ _ _
  // _ _ _ _ _
  // _ _ X _ _
  // _ _ _ _ _
  // _ _ _ _ _
  @Test
  public void testApplyKernelTwentyFive() {
    p1 = this.i1.getPixelAt(2, 2);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.s1.apply(this.i1);
    p1 = this.i1.getPixelAt(2, 2);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
  }

  // test that apply correctly sharpens an entire image
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
    int[][] expectedSharpenValues = {
        {11, 15, 11, 15, 11},
        {15, 21, 16, 21, 15},
        {11, 16, 10, 16, 11},
        {15, 21, 16, 21, 15},
        {11, 15, 11, 15, 11}};
    this.s1.apply(this.i1);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = this.i1.getPixelAt(i, j);
        int expectedSharpenValue = expectedSharpenValues[i][j];
        assertEquals(expectedSharpenValue, p1.getRed());
        assertEquals(expectedSharpenValue, p1.getGreen());
        assertEquals(expectedSharpenValue, p1.getBlue());
      }
    }
  }
}
