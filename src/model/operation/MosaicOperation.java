package model.operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * Function object to perform the "Mosaic" operation on an Image. The given image is broken down by
 * clustering pixels around randomly generated seeds and replacing each pixel in the cluster with
 * the average color of that cluster.
 */
public class MosaicOperation implements ImageOperation {

  private final int seeds;
  private final Random rand;

  /**
   * Creates a mosaic operation with a given number of seeds and a Random object.
   *
   * @param seeds the number of seeds to use to create clusters
   * @param rand  the Random object used to generate random seed locations
   * @throws IllegalArgumentException if the given number of seeds is negative or the random object
   *                                  is null
   */
  public MosaicOperation(int seeds, Random rand) throws IllegalArgumentException {
    this.seeds = MosaicOperation.isNegativeInt(seeds);
    this.rand = ImageUtil.requireNonNull(rand);
  }

  /**
   * Creates a mosaic operation with a given number of random seeds.
   *
   * @param seeds the number of seeds to use to create clusters
   * @throws IllegalArgumentException if the given number of seeds is negative
   */
  public MosaicOperation(int seeds) throws IllegalArgumentException {
    this(seeds, new Random());
  }

  @Override
  public Image apply(Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);

    MosaicOperation.Seed[] seeds = new MosaicOperation.Seed[this.seeds];
    for (int i = 0; i < this.seeds; i++) {
      seeds[i] = new MosaicOperation.Seed(this.rand.nextInt(img.getWidth()),
          this.rand.nextInt(img.getHeight()));
    }

    int[][] seedColors = new int[this.seeds][3];
    int[] numPixelsPerSeed = this.clusterImage(img, seedColors, seeds);
    this.averageClusterColor(seedColors, numPixelsPerSeed);
    return this.createMosaicImage(img, seedColors, seeds);
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
   * Returns the index of the closest seed to the given coordinates.
   *
   * @param x     the given x-coordinate of the point
   * @param y     the given y-coordinate of the point
   * @param seeds the list of seeds to measure the distances from the given coordinates
   * @return the index of the closest seed from the list of seeds to the given coordinates
   */
  private int closestSeedIndex(int x, int y, MosaicOperation.Seed... seeds) {
    double minDistance = Integer.MAX_VALUE;
    int minDistanceIndex = 0;
    for (int i = 0; i < seeds.length; i++) {
      double distance = seeds[i].distance(x, y);
      if (distance < minDistance) {
        minDistance = distance;
        minDistanceIndex = i;
      }
    }
    return minDistanceIndex;
  }

  /**
   * Pairs each pixel in the given image to the closest seed to create a cluster for each seed.
   *
   * @param img        the original image to be broken down into a mosaic
   * @param seedColors the sum of the RGB values for each cluster
   * @param seeds      the randomly chosen seeds used to break down the original image
   * @return the number of pixels in the given image for each cluster
   */
  private int[] clusterImage(Image img, int[][] seedColors, MosaicOperation.Seed[] seeds) {
    int[] numPixelsPerSeed = new int[this.seeds];
    for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight(); j++) {
        int seedNum = this.closestSeedIndex(i, j, seeds);
        Pixel pix = img.getPixelAt(i, j);
        seedColors[seedNum][0] += pix.getRed();
        seedColors[seedNum][1] += pix.getGreen();
        seedColors[seedNum][2] += pix.getBlue();
        numPixelsPerSeed[seedNum]++;
      }
    }
    return numPixelsPerSeed;
  }

  /**
   * Averages the RGB values for each cluster of pixels.
   *
   * @param seedColors       the sum of the RGB values for each cluster
   * @param numPixelsPerSeed the number of pixels in each cluster
   */
  private void averageClusterColor(int[][] seedColors, int[] numPixelsPerSeed) {
    for (int i = 0; i < this.seeds; i++) {
      for (int j = 0; j < 3; j++) {
        try {
          seedColors[i][j] /= numPixelsPerSeed[i];
        } catch (ArithmeticException ignored) {
        }
      }
    }
  }

  /**
   * Creates a new image with the color of each pixel in the given image replaced with the average
   * color of its cluster.
   *
   * @param img        the original image to be broken down into a mosaic
   * @param seedColors the average RGB values for each cluster
   * @param seeds      the randomly chosen seeds used to break down the original image
   * @return a image that has been clustered with the given seeds
   */
  private Image createMosaicImage(Image img, int[][] seedColors, MosaicOperation.Seed[] seeds) {
    List<List<Pixel>> copy = new ArrayList<>();
    for (int i = 0; i < img.getWidth(); i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < img.getHeight(); j++) {
        int[] seedColor = seedColors[this.closestSeedIndex(i, j, seeds)];
        row.add(new PixelImpl(seedColor[0], seedColor[1], seedColor[2]));
      }
      copy.add(row);
    }
    return new ImageImpl(copy);
  }

  /**
   * Represents a seed used to break down an image to generate a mosaic. Each seed has coordinates
   * that represent its position within the image.
   */
  private static class Seed {

    private final int x;
    private final int y;

    /**
     * Constructs a seed with the given x and y coordinates.
     *
     * @param x the given x-coordinate of the point
     * @param y the given y-coordinate of the point
     * @throws IllegalArgumentException if either of the given coordinates are negative
     */
    private Seed(int x, int y) throws IllegalArgumentException {
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
