import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import model.operation.BlurOperation;
import model.image.Image;
import model.image.ImageImpl;
import model.operation.ImageOperation;
import model.image.Pixel;
import model.image.PixelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BlurOperation class.
 */
public class BlurOperationTest {

  private ImageOperation b1;
  private Pixel p1;
  private Pixel p2;
  private Pixel p3;
  private Image i1;
  private Image i2;
  private Image i3;
  private Image i4;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.b1 = new BlurOperation();
    this.p1 = new PixelImpl(10, 10, 10);
    this.p2 = new PixelImpl(0, 0, 0);
    this.p3 = new PixelImpl(255, 255, 255);

    List<List<Pixel>> l1 = new ArrayList<>();
    List<List<Pixel>> l2 = new ArrayList<>();
    List<List<Pixel>> l3 = new ArrayList<>();

    for (int i = 0; i < 5; i++) {
      List<Pixel> row1 = new ArrayList<>();
      List<Pixel> row2 = new ArrayList<>();
      List<Pixel> row3 = new ArrayList<>();
      for (int j = 0; j < 5; j++) {
        row1.add(this.p1);
        row2.add(this.p2);
        row3.add(this.p3);
      }
      l1.add(row1);
      l2.add(row2);
      l3.add(row3);
    }

    this.i1 = new ImageImpl(l1);
    this.i2 = new ImageImpl(l2);
    this.i3 = new ImageImpl(l3);
    this.i4 = null;
  }

  // test that apply cannot be given a null image
  @Test(expected = IllegalArgumentException.class)
  public void testApplyNull() {
    this.b1.apply(null);
  }

  // test that pixels are correctly blurred when the kernel overlaps 4 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // X _ _ _ X
  // _ _ _ _ _
  // _ _ _ _ _
  // _ _ _ _ _
  // X _ _ _ X
  @Test
  public void testApplyKernelFour() {
    p1 = this.i1.getPixelAt(0, 0);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.i4 = this.b1.apply(this.i1);
    p1 = this.i4.getPixelAt(0, 0);
    assertEquals(6, p1.getRed());
    assertEquals(6, p1.getGreen());
    assertEquals(6, p1.getBlue());
  }

  // test that pixels are correctly blurred when the kernel overlaps 6 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // _ X X X _
  // X _ _ _ X
  // X _ _ _ X
  // X _ _ _ X
  // _ X X X _
  @Test
  public void testApplyKernelSix() {
    p1 = this.i1.getPixelAt(0, 1);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.i4 = this.b1.apply(this.i1);
    p1 = this.i4.getPixelAt(0, 1);
    assertEquals(8, p1.getRed());
    assertEquals(8, p1.getGreen());
    assertEquals(8, p1.getBlue());
  }

  // test that pixels are correctly blurred when the kernel overlaps 9 pixels
  // in a 5x5 image, the affected pixels are marked by an X in the below diagram
  // _ _ _ _ _
  // _ X X X _
  // _ X X X _
  // _ X X X _
  // _ _ _ _ _
  @Test
  public void testApplyKernelNine() {
    p1 = this.i1.getPixelAt(1, 1);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
    this.i4 = this.b1.apply(this.i1);
    p1 = this.i4.getPixelAt(1, 1);
    assertEquals(10, p1.getRed());
    assertEquals(10, p1.getGreen());
    assertEquals(10, p1.getBlue());
  }

  // test that apply correctly blurs an entire image
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
    int[][] expectedBlurValues = {
        {6, 8, 8, 8, 6},
        {8, 10, 10, 10, 8},
        {8, 10, 10, 10, 8},
        {8, 10, 10, 10, 8},
        {6, 8, 8, 8, 6}};
    this.i4 = this.b1.apply(this.i1);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = this.i4.getPixelAt(i, j);
        int expectedBlurValue = expectedBlurValues[i][j];
        assertEquals(expectedBlurValue, p1.getRed());
        assertEquals(expectedBlurValue, p1.getGreen());
        assertEquals(expectedBlurValue, p1.getBlue());
      }
    }
  }

  // test that pixel RGB values are clamped from underflow when the blur is applied
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
    this.i4 = this.b1.apply(this.i2);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p2 = this.i4.getPixelAt(i, j);
        assertTrue(0 <= p2.getRed());
        assertTrue(0 <= p2.getGreen());
        assertTrue(0 <= p2.getBlue());
      }
    }
  }

  // test that pixel RGB values are clamped from overflow when the blur is applied
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
    this.i4 = this.b1.apply(this.i3);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p3 = this.i4.getPixelAt(i, j);
        assertTrue(255 >= p3.getRed());
        assertTrue(255 >= p3.getGreen());
        assertTrue(255 >= p3.getBlue());
      }
    }
  }
}
