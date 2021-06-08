import java.awt.Color;
import model.image.Image;
import model.image.programmatic.CreateCheckerboard;
import model.image.programmatic.ProgrammaticCreator;
import org.junit.Assert;
import org.junit.Test;

public class CreateCheckerboardTest {
  public CreateCheckerboardTest() {
  }

  @Test(
      expected = IllegalArgumentException.class
  )
  public void testColorOneCannotBeNull() {
    new CreateCheckerboard(3, 9, (Color)null, Color.BLACK);
  }

  @Test(
      expected = IllegalArgumentException.class
  )
  public void testColorTwoCannotBeNull() {
    new CreateCheckerboard(3, 9, Color.WHITE, (Color)null);
  }

  @Test(
      expected = IllegalArgumentException.class
  )
  public void testSizeMustBePositive() {
    new CreateCheckerboard(0, 9, Color.WHITE, Color.BLACK);
  }

  @Test(
      expected = IllegalArgumentException.class
  )
  public void testNumSquaresMustBePositive() {
    new CreateCheckerboard(3, -1, Color.WHITE, Color.BLACK);
  }

  @Test
  public void testThreeByThreeBoard() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(3, 9, Color.WHITE, Color.BLACK);
    Image checkerboard = checkerboardCreator.create();
    Assert.assertEquals(3L, (long)checkerboard.getWidth());
    Assert.assertEquals(3L, (long)checkerboard.getHeight());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getBlue());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getBlue());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 1).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getBlue());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getBlue());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void testSizeIsRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(5, 9, Color.BLUE, Color.GREEN);
    Image checkerboard = checkerboardCreator.create();
    Assert.assertEquals(3L, (long)checkerboard.getWidth());
    Assert.assertEquals(3L, (long)checkerboard.getHeight());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void testNumSquaresIsRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(3, 15, Color.BLUE, Color.GREEN);
    Image checkerboard = checkerboardCreator.create();
    Assert.assertEquals(3L, (long)checkerboard.getWidth());
    Assert.assertEquals(3L, (long)checkerboard.getHeight());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void testBothSizeAndNumSquaresRoundedDown() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(5, 15, Color.BLUE, Color.GREEN);
    Image checkerboard = checkerboardCreator.create();
    Assert.assertEquals(3L, (long)checkerboard.getWidth());
    Assert.assertEquals(3L, (long)checkerboard.getHeight());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 2).getBlue());
  }

  @Test
  public void testMultiplePixelsPerSquare() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(4, 4, Color.BLUE, Color.GREEN);
    Image checkerboard = checkerboardCreator.create();
    Assert.assertEquals(4L, (long)checkerboard.getWidth());
    Assert.assertEquals(4L, (long)checkerboard.getHeight());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 3).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 3).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 3).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 3).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 3).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 3).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 3).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 3).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 3).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(3, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(3, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 2).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 2).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(3, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 3).getRed());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 3).getGreen());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(3, 3).getBlue());
  }

  @Test
  public void testBothColorsTheSame() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(4, 4, Color.GREEN, Color.GREEN);
    Image checkerboard = checkerboardCreator.create();
    Assert.assertEquals(4L, (long)checkerboard.getWidth());
    Assert.assertEquals(4L, (long)checkerboard.getHeight());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 3).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 3).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 3).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 3).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(1, 3).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(1, 3).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 3).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(2, 3).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(2, 3).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(3, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 0).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 1).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(3, 1).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 1).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 2).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(3, 2).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 2).getBlue());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 3).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(3, 3).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(3, 3).getBlue());
  }

  @Test
  public void testOneByOne() {
    ProgrammaticCreator checkerboardCreator = new CreateCheckerboard(1, 1, Color.GREEN, Color.RED);
    Image checkerboard = checkerboardCreator.create();
    Assert.assertEquals(1L, (long)checkerboard.getWidth());
    Assert.assertEquals(1L, (long)checkerboard.getHeight());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getRed());
    Assert.assertEquals(255L, (long)checkerboard.getPixelAt(0, 0).getGreen());
    Assert.assertEquals(0L, (long)checkerboard.getPixelAt(0, 0).getBlue());
  }
}
