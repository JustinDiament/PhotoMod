package model.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

public class MosaicOperation implements ImageOperation {

  private final int seeds;

  public MosaicOperation(int seeds) throws IllegalArgumentException {
    this.seeds = MosaicOperation.isNegativeInt(seeds);
  }

  public MosaicOperation() {
    this(0);
  }

  @Override
  public Image apply(Image img) throws IllegalArgumentException {
    Random rand = new Random();
    Seed[] seeds = new Seed[this.seeds];
    for (int i = 0; i < seeds.length; i++) {
      seeds[i] = new Seed(rand.nextInt(img.getWidth()), rand.nextInt(img.getHeight()));
      System.out.println(seeds[i].x + " " + seeds[i].y);
    }



    return null;
  }

  // for testing
  public static void main(String[] args) {
    MosaicOperation m = new MosaicOperation(5);
    List<List<Pixel>> copy = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      List<Pixel> column = new ArrayList<>();
      for (int j = 0; j < 10; j++) {
        column.add(new PixelImpl(10, 10, 10));
      }
      copy.add(column);
    }
    Image i = new ImageImpl(copy);
    m.apply(i);
  }


  /**
   * Checks that the given integer is non-negative.
   *
   * @param i the given integer to check for negativity
   * @return the given integer if it is non-negative
   * @throws IllegalArgumentException if the given integer is negative
   */
  private static int isNegativeInt(int i) throws IllegalArgumentException {
    if (i < 0) {
      throw new IllegalArgumentException("Seed cannot have negative coordinates");
    } else {
      return i;
    }
  }








  /**
   *
   */
  private static class Seed {

    private final int x;
    private final int y;

    private Seed(int x, int y) {
      this.x = MosaicOperation.isNegativeInt(x);
      this.y = MosaicOperation.isNegativeInt(y);
    }

    /**
     * Calculates the absolute distance of this seed from the given coordinates.
     *
     * @param x the given x-coordinate of the point
     * @param y the given y-coordinate of the point
     * @return the absolute distance between this seed and the given coordinates
     */
    private double distance(int x, int y) {
      return Math.abs(Math.sqrt(Math.pow((x - this.x), 2) + Math.pow((y - this.y), 2)));
    }

  }

}
