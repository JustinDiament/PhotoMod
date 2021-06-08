import static org.junit.Assert.assertEquals;

import model.image.Pixel;
import model.image.PixelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the PixelImpl class.
 */
public class PixelImplTest {

  Pixel p1;
  Pixel p2;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.p1 = new PixelImpl(0, 0, 0);
    this.p2 = new PixelImpl(255, 255, 255);
  }

  // test that the constructor clamps red values under 0
  @Test
  public void testClampRedUnderflow() {
    this.p1 = new PixelImpl(-1, 128, 128);
    assertEquals(0, this.p1.getRed());
  }

  // test that the constructor clamps red values over 255
  @Test
  public void testClampRedOverflow() {
    this.p1 = new PixelImpl(256, 128, 128);
    assertEquals(255, this.p1.getRed());
  }

  // test that the constructor clamps green values under 0
  @Test
  public void testClampGreenUnderflow() {
    this.p1 = new PixelImpl(128, -1, 128);
    assertEquals(0, this.p1.getGreen());
  }

  // test that the constructor clamps green values over 255
  @Test
  public void testClampGreenOverflow() {
    this.p1 = new PixelImpl(128, 256, 128);
    assertEquals(255, this.p1.getGreen());
  }

  // test that the constructor clamps blue values under 0
  @Test
  public void testClampBlueUnderflow() {
    this.p1 = new PixelImpl(128, 128, -1);
    assertEquals(0, this.p1.getBlue());
  }

  // test that the constructor clamps blue values over 255
  @Test
  public void testClampBlueOverflow() {
    this.p1 = new PixelImpl(128, 128, 256);
    assertEquals(255, this.p1.getBlue());
  }

  // test that the pixel's red value can be retrieved
  @Test
  public void testGetRed() {
    assertEquals(255, this.p2.getRed());
  }

  // test that the pixel's green value can be retrieved
  @Test
  public void testGetGreen() {
    assertEquals(255, this.p2.getGreen());
  }

  // test that the pixel's blue value can be retrieved
  @Test
  public void testGetBlue() {
    assertEquals(255, this.p2.getBlue());
  }
}
