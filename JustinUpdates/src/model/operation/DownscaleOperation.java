package model.operation;

import java.util.ArrayList;
import java.util.List;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * Function object to perform the "Downscale" operation on an Image. The Image's horizontal and
 * vertical size are decreased to chosen percents of their original size. New pixel colors are
 * chosen from an average of the four pixels nearest the corresponding location in the original
 * Image.
 */
public class DownscaleOperation implements ImageOperation {

  private final double xScale;
  private final double yScale;

  /**
   * Constructs a DownscaleOperation function object that, when applied, will utilize the given X
   * and Y scales to downscale the given Image.
   *
   * @param xScale the percent (between 0 and 1, inclusive on 1 but not on 0) to scale the Image's
   *               horizontal size to
   * @param yScale the percent (between 0 and 1, inclusive on 1 but not on 0) to scale the Image's
   *               vertical size to
   * @throws IllegalArgumentException if the X and/or Y scale is invalid (less than or equal to zero
   *                                  or greater than one)
   */
  public DownscaleOperation(double xScale, double yScale) throws IllegalArgumentException {
    if (xScale <= 0 || yScale <= 0 || xScale > 1 || yScale > 1) {
      throw new IllegalArgumentException(
          "X and Y scales must be greater than zero and less than or equal to one");
    }

    this.xScale = xScale;
    this.yScale = yScale;
  }

  @Override
  public Image apply(Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);

    int oldWidth = img.getWidth();
    int oldHeight = img.getHeight();

    int newWidth = (int) (oldWidth * this.xScale);
    int newHeight = (int) (oldHeight * this.yScale);

    List<List<Pixel>> downscaledImage = new ArrayList<>();

    for (int i = 0; i < newWidth; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < newHeight; j++) {
        double locationX = ((double) (i * oldWidth)) / (newWidth);
        double locationY = ((double) (j * oldHeight)) / (newHeight);

        if (Math.floor(locationX) == Math.ceil(locationX) ||
            (Math.floor(locationY) == Math.ceil(locationY))) {
          row.add(img.getPixelAt((int) (locationX), (int) (locationY)));
          continue;
        }

        Pixel a = img.getPixelAt((int) Math.floor(locationX), (int) Math.floor(locationY));
        Pixel b = img.getPixelAt((int) Math.ceil(locationX), (int) Math.floor(locationY));
        Pixel c = img.getPixelAt((int) Math.floor(locationX), (int) Math.ceil(locationY));
        Pixel d = img.getPixelAt((int) Math.ceil(locationX), (int) Math.ceil(locationY));

        row.add(new PixelImpl(
            this.getAverageColor(a.getRed(), b.getRed(), c.getRed(), d.getRed(), locationX,
                locationY),
            this.getAverageColor(a.getGreen(), b.getGreen(), c.getGreen(), d.getGreen(), locationX,
                locationY),
            this.getAverageColor(a.getBlue(), b.getBlue(), c.getBlue(), d.getBlue(), locationX,
                locationY)));
      }
      downscaledImage.add(row);
    }
    return new ImageImpl(downscaledImage);
  }

  /**
   * Produces the average color for one color channel between 4 pixels that make up a 2x2 pixel
   * square in an Image.
   *
   * @param a         the color value for one color channel of the pixel in the bottom left corner
   *                  of the 2x2 pixel square
   * @param b         the color value for one color channel of the pixel in the bottom right corner
   *                  of the 2x2 pixel square
   * @param c         the color value for one color channel of the pixel in the top left corner of
   *                  the 2x2 pixel square
   * @param d         the color value for one color channel of the pixel in the top right corner of
   *                  the 2x2 pixel square
   * @param locationX an x coordinate which is located between the x coordinates of the four pixels
   *                  in the pixel square
   * @param locationY an y coordinate which is located between the x coordinates of the four pixels
   *                  in the pixel square
   * @return the average color value for the color channel being operated on of the four pixels in
   * the pixel square
   */
  private int getAverageColor(int a, int b, int c, int d, double locationX, double locationY) {
    double m = (b * (locationX - Math.floor(locationX))) + (a * (Math.ceil(locationX) - locationX));
    double n = (d * (locationX - Math.floor(locationX))) + (c * (Math.ceil(locationX) - locationX));
    return (int) ((n * (locationY - Math.floor(locationY))) +
        (m * (Math.ceil(locationY) - locationY)));
  }
}
