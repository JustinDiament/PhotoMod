import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import model.image.Image;
import model.image.ImageImpl;
import model.operation.ImageOperation;
import model.image.Pixel;
import model.image.PixelImpl;
import model.operation.SharpenOperation;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the model.operation.SharpenOperation class.
 */
public class SharpenOperationTest {

  ImageOperation s1;
  Pixel p1;
  Pixel p2;
  Pixel p3;
  List<List<Pixel>> l1;
  List<List<Pixel>> l2;
  List<List<Pixel>> l3;
  Image i1;
  Image i2;
  Image i3;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.s1 = new SharpenOperation();
    this.p1 = new PixelImpl(10, 10, 10);
    this.p2 = new PixelImpl(0, 0, 0);
    this.p3 = new PixelImpl(255, 255, 255);

    this.l1 = new ArrayList<>();
    this.l2 = new ArrayList<>();
    this.l3 = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      List<Pixel> row1 = new ArrayList<>();
      List<Pixel> row2 = new ArrayList<>();
      List<Pixel> row3 = new ArrayList<>();
      for (int j = 0; j < 5; j++) {
        row1.add(this.p1);
        row2.add(this.p2);
        row3.add(this.p3);
      }
      this.l1.add(row1);
      this.l2.add(row2);
      this.l3.add(row3);
    }

    this.i1 = new ImageImpl(this.l1);
    this.i2 = new ImageImpl(this.l2);
    this.i3 = new ImageImpl(this.l3);
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

  // test that pixel RGB values are clamped from underflow when the sharpen is applied
  @Test
  public void testClampUnderflow() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p2 = this.i2.getPixelAt(i, j);
        assertEquals(0, p2.getRed());
        assertEquals(0, p2.getGreen());
        assertEquals(0, p2.getBlue());
      }
    }
    this.s1.apply(this.i2);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p2 = this.i2.getPixelAt(i, j);
        assertTrue(0 <= p2.getRed());
        assertTrue(0 <= p2.getGreen());
        assertTrue(0 <= p2.getBlue());
      }
    }
  }

  // test that pixel RGB values are clamped from overflow when the sharpen is applied
  @Test
  public void testClampOverflow() {
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p3 = this.i3.getPixelAt(i, j);
        assertEquals(255, p3.getRed());
        assertEquals(255, p3.getGreen());
        assertEquals(255, p3.getBlue());
      }
    }
    this.s1.apply(this.i3);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p3 = this.i3.getPixelAt(i, j);
        assertTrue(255 >= p3.getRed());
        assertTrue(255 >= p3.getGreen());
        assertTrue(255 >= p3.getBlue());
      }
    }
  }
}
