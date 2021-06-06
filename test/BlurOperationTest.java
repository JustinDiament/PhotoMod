import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BlurOperation class.
 */
public class BlurOperationTest {

  ImageOperation b1;
  Pixel p1;
  List<List<Pixel>> l1;
  Image i1;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.b1 = new BlurOperation();
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
    this.b1.apply(this.i1);
    p1 = this.i1.getPixelAt(0, 0);
    assertEquals(5, p1.getRed());
    assertEquals(5, p1.getGreen());
    assertEquals(5, p1.getBlue());
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
    this.b1.apply(this.i1);
    p1 = this.i1.getPixelAt(0, 1);
    assertEquals(7, p1.getRed());
    assertEquals(7, p1.getGreen());
    assertEquals(7, p1.getBlue());
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
    this.b1.apply(this.i1);
    p1 = this.i1.getPixelAt(1, 1);
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
        {5, 7, 7, 7, 5},
        {7, 10, 10, 10, 7},
        {7, 10, 10, 10, 7},
        {7, 10, 10, 10, 7},
        {5, 7, 7, 7, 5}};
    this.b1.apply(this.i1);
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        this.p1 = this.i1.getPixelAt(i, j);
        int expectedBlurValue = expectedBlurValues[i][j];
        assertEquals(expectedBlurValue, p1.getRed());
        assertEquals(expectedBlurValue, p1.getGreen());
        assertEquals(expectedBlurValue, p1.getBlue());
      }
    }
  }
}
