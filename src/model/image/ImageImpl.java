package model.image;

import java.util.ArrayList;
import java.util.List;
import model.ImageUtil;

/**
 * Represents a rectangular image, such as a photograph or programmatically generated image (though
 * it is not limited to these two).
 */
public class ImageImpl implements Image {

  private final List<List<Pixel>> image;

  /**
   * Produces an ImageImpl with the given list of pixels.
   *
   * @param image the list of pixels that will represent the content of this image
   * @throws IllegalArgumentException if the given list of pixels is null
   */
  public ImageImpl(List<List<Pixel>> image) throws IllegalArgumentException {
    this.image = this.getPixels(ImageUtil.requireNonNull(image));
  }

  /**
   * Produces a deep copy of the given list of pixels.
   *
   * @param image the list of pixels to be copied
   * @return a deep copy of the given list
   * @throws IllegalArgumentException if the given list of pixels is null
   */
  private List<List<Pixel>> getPixels(List<List<Pixel>> image) throws IllegalArgumentException {
    ImageUtil.requireNonNull(image);

    List<List<Pixel>> copy = new ArrayList<>();
    for (List<Pixel> row : image) {
      List<Pixel> rowCopy = new ArrayList<>();
      for (Pixel pixel : row) {
        rowCopy.add(new PixelImpl(pixel.getRed(), pixel.getGreen(), pixel.getBlue()));
      }
      copy.add(rowCopy);
    }
    return copy;
  }

  @Override
  public Pixel getPixelAt(int x, int y) throws IllegalArgumentException {
    if (x > this.getWidth() || y > this.getHeight() || x < 0 || y < 0) {
      throw new IllegalArgumentException("x and y must form a valid pixel index for this image");
    }
    return this.image.get(x).get(y);
  }

  @Override
  public int getWidth() {
    return this.image.size();
  }

  @Override
  public int getHeight() {
    return this.image.get(0).size();
  }
}
