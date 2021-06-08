import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ImageImpl class.
 */
public class ImageImplTest {

  private List<List<Pixel>> pixels;
  private Image image;

  @Before
  public void init() {
    pixels = Arrays.asList(
        new ArrayList<>(Arrays.asList(
            new PixelImpl(5, 6, 7),
            new PixelImpl(10, 12, 14),
            new PixelImpl(15, 18, 21))),
        new ArrayList<>(Arrays.asList(
            new PixelImpl(105, 106, 107),
            new PixelImpl(110, 112, 114),
            new PixelImpl(115, 118, 121))));

    image = new ImageImpl(this.pixels);
  }

  @Test(expected = IllegalArgumentException.class)
  public void listOfPixelsCannotBeNull() {
    new ImageImpl(null);
  }

  @Test
  public void copiedPixelListInImageHasIdenticalPixels() {
    assertEquals(5, this.pixels.get(0).get(0).getRed());
    assertEquals(6, this.pixels.get(0).get(0).getGreen());
    assertEquals(7, this.pixels.get(0).get(0).getBlue());

    assertEquals(10, this.pixels.get(0).get(1).getRed());
    assertEquals(12, this.pixels.get(0).get(1).getGreen());
    assertEquals(14, this.pixels.get(0).get(1).getBlue());

    assertEquals(15, this.pixels.get(0).get(2).getRed());
    assertEquals(18, this.pixels.get(0).get(2).getGreen());
    assertEquals(21, this.pixels.get(0).get(2).getBlue());

    assertEquals(105, this.pixels.get(1).get(0).getRed());
    assertEquals(106, this.pixels.get(1).get(0).getGreen());
    assertEquals(107, this.pixels.get(1).get(0).getBlue());

    assertEquals(110, this.pixels.get(1).get(1).getRed());
    assertEquals(112, this.pixels.get(1).get(1).getGreen());
    assertEquals(114, this.pixels.get(1).get(1).getBlue());

    assertEquals(115, this.pixels.get(1).get(2).getRed());
    assertEquals(118, this.pixels.get(1).get(2).getGreen());
    assertEquals(121, this.pixels.get(1).get(2).getBlue());

    Image imageCopy = new ImageImpl(pixels);

    assertEquals(5, imageCopy.getPixelAt(0, 0).getRed());
    assertEquals(6, imageCopy.getPixelAt(0, 0).getGreen());
    assertEquals(7, imageCopy.getPixelAt(0, 0).getBlue());

    assertEquals(10, imageCopy.getPixelAt(0, 1).getRed());
    assertEquals(12, imageCopy.getPixelAt(0, 1).getGreen());
    assertEquals(14, imageCopy.getPixelAt(0, 1).getBlue());

    assertEquals(15, imageCopy.getPixelAt(0, 2).getRed());
    assertEquals(18, imageCopy.getPixelAt(0, 2).getGreen());
    assertEquals(21, imageCopy.getPixelAt(0, 2).getBlue());

    assertEquals(105, imageCopy.getPixelAt(1, 0).getRed());
    assertEquals(106, imageCopy.getPixelAt(1, 0).getGreen());
    assertEquals(107, imageCopy.getPixelAt(1, 0).getBlue());

    assertEquals(110, imageCopy.getPixelAt(1, 1).getRed());
    assertEquals(112, imageCopy.getPixelAt(1, 1).getGreen());
    assertEquals(114, imageCopy.getPixelAt(1, 1).getBlue());

    assertEquals(115, imageCopy.getPixelAt(1, 2).getRed());
    assertEquals(118, imageCopy.getPixelAt(1, 2).getGreen());
    assertEquals(121, imageCopy.getPixelAt(1, 2).getBlue());
  }

  @Test
  public void getWidthIsSizeOfFirstArrayList() {
    assertEquals(2, image.getWidth());
  }

  @Test
  public void getWidthOnImageWithNoPixels() {
    assertEquals(0, new ImageImpl(Collections.emptyList()).getWidth());
  }

  @Test
  public void getHeightIsSizeOfFirstArrayList() {
    assertEquals(3, image.getHeight());
  }

  @Test
  public void getHeightOnImageWithNoPixels() {
    assertEquals(0,
        new ImageImpl(Collections.singletonList(new ArrayList<>(Collections.emptyList())))
            .getHeight());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPixelAtXTooBig() {
    this.image.getPixelAt(3, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPixelAtXTooSmall() {
    this.image.getPixelAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPixelAtYTooBig() {
    this.image.getPixelAt(0, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPixelAtYTooSmall() {
    this.image.getPixelAt(0, -1);
  }

  @Test
  public void getPixelAtBottomLeftCorner() {
    Pixel zeroZero = this.image.getPixelAt(0, 0);

    assertEquals(5, zeroZero.getRed());
    assertEquals(6, zeroZero.getGreen());
    assertEquals(7, zeroZero.getBlue());
  }

  @Test
  public void getPixelAtTopLeftCorner() {
    Pixel zeroTwo = this.image.getPixelAt(0, 2);

    assertEquals(15, zeroTwo.getRed());
    assertEquals(18, zeroTwo.getGreen());
    assertEquals(21, zeroTwo.getBlue());
  }

  @Test
  public void getPixelAtTopRightCorner() {
    Pixel oneTwo = this.image.getPixelAt(1, 2);

    assertEquals(115, oneTwo.getRed());
    assertEquals(118, oneTwo.getGreen());
    assertEquals(121, oneTwo.getBlue());
  }

  @Test
  public void getPixelAtBottomRightCorner() {
    Pixel oneZero = this.image.getPixelAt(1, 0);

    assertEquals(105, oneZero.getRed());
    assertEquals(106, oneZero.getGreen());
    assertEquals(107, oneZero.getBlue());
  }
}
