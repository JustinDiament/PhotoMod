import java.awt.Color;
import model.image.Image;
import model.image.programmatic.CreateCheckerboard;
import model.image.programmatic.ProgrammaticCreator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the CreateCheckerboard class.
 */
public class CreateCheckerboardTest {

  @Test(expected = IllegalArgumentException.class)
  public void testColorOneCannotBeNull() {
    new CreateCheckerboard(3, 9, null, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testColorTwoCannotBeNull() {
    new CreateCheckerboard(3, 9, Color.WHITE, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSizeMustBePositive() {
    new CreateCheckerboard(0, 9, Color.WHITE, Color.BLACK);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNumSquaresMustBePositive() {
    new CreateCheckerboard(3, -1, Color.WHITE, Color.BLACK);
  }

  @Test
  public void testThreeByThreeBoard() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(3, 9, Color.WHITE,
        Color.BLACK);

    Image checkerboard = checkerboardCreator.create();

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
  public void testSizeIsRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(5, 9, Color.BLUE, Color.GREEN);

    Image checkerboard = checkerboardCreator.create();

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
  public void testNumSquaresIsRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(3, 15, Color.BLUE,
        Color.GREEN);

    Image checkerboard = checkerboardCreator.create();

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
  public void testBothSizeAndNumSquaresRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(5, 15, Color.BLUE,
        Color.GREEN);

    Image checkerboard = checkerboardCreator.create();

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
  public void testMultiplePixelsPerSquare() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(4, 4, Color.BLUE, Color.GREEN);

    Image checkerboard = checkerboardCreator.create();

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
  public void testBothColorsTheSame() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(4, 4, Color.GREEN,
        Color.GREEN);

    Image checkerboard = checkerboardCreator.create();

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
  public void testOneByOne() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(1, 1, Color.GREEN, Color.RED);

    Image checkerboard = checkerboardCreator.create();

    assertEquals(1, checkerboard.getWidth());
    assertEquals(1, checkerboard.getHeight());

    assertEquals(0, checkerboard.getPixelAt(0, 0).getRed());
    assertEquals(255, checkerboard.getPixelAt(0, 0).getGreen());
    assertEquals(0, checkerboard.getPixelAt(0, 0).getBlue());
  }
}
