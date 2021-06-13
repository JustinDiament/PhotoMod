package model.operation;

import java.util.ArrayList;
import java.util.List;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * Represents filtering image operations, which are a subset of all image operations that involve
 * the use of a kernel to filter each of the pixels in an image.
 */
public abstract class FilterOperation implements ImageOperation {

  protected final double[][] kernel;

  /**
   * Create a filter operation containing a kernel to be used for its specific filter operation.
   *
   * @throws IllegalStateException if the kernel does not have odd dimensions
   */
  public FilterOperation() throws IllegalStateException {
    this.kernel = this.getKernel();

    if (this.kernel.length % 2 == 0 || this.kernel[0].length % 2 == 0) {
      throw new IllegalStateException("Invalid kernel implementation");
    }
  }

  @Override
  public Image apply(Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);
    List<List<Pixel>> copy = new ArrayList<>();
    for (int i = 0; i < img.getWidth(); i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < img.getHeight(); j++) {
        row.add(this.filterPixel(this.getImageSection(img, i, j)));
      }
      copy.add(row);
    }
    return new ImageImpl(copy);
  }

  /**
   * Generate the specific kernel for its corresponding filter operation.
   *
   * @return the kernel to be used to filter an image
   */
  protected abstract double[][] getKernel();

  /**
   * Grabs the section of pixels from an image surrounding the pixel at the given coordinates. The
   * obtained section matches the specified size of the filter kernel.
   *
   * @param img the image from which the section is obtained
   * @param x   the x coordinate of the location of the desired pixel
   * @param y   the y coordinate of the location of the desired pixel
   * @return a section of pixels surrounding the specified pixel matching the size of the kernel
   * @throws IllegalArgumentException if the given image is null
   */
  private Pixel[][] getImageSection(Image img, int x, int y)
      throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);
    int kernelSize = this.kernel.length;
    Pixel[][] section = new Pixel[kernelSize][kernelSize];
    int sectionX = 0;
    for (int i = x; i < x + kernelSize; i++) {
      int sectionY = 0;
      for (int j = y; j < y + kernelSize; j++) {
        Pixel pixel;
        try {
          Pixel temp = img.getPixelAt(i - (kernelSize / 2), j - (kernelSize / 2));
          pixel = new PixelImpl(temp.getRed(), temp.getGreen(), temp.getBlue());
        } catch (IllegalArgumentException e) {
          pixel = new PixelImpl(0, 0, 0);
        }
        section[sectionX][sectionY] = pixel;
        sectionY++;
      }
      sectionX++;
    }
    return section;
  }

  /**
   * Applies the filtering operation to each of the color values of the pixel at the given
   * coordinates using the specified kernel and the corresponding section of the image.
   * <p>The filtering operation is calculated by finding the product of each number in the kernel
   * with the corresponding RGB value in the image section, then calculating the total sum of these
   * values.</p>
   *
   * @param section section of pixels surrounding the specified pixel
   * @return a Pixel with the modified RGB values
   * @throws IllegalArgumentException if the given image or section are null
   */
  private Pixel filterPixel(Pixel[][] section) throws IllegalArgumentException {
    ImageUtil.requireNonNull(section);
    if (section.length != this.kernel.length || section[0].length != this.kernel[0].length) {
      throw new IllegalArgumentException("Kernel and pixel section must have the same dimensions");
    }

    double red = 0;
    double green = 0;
    double blue = 0;
    for (int i = 0; i < this.kernel.length; i++) {
      for (int j = 0; j < this.kernel.length; j++) {
        red += this.kernel[i][j] * section[i][j].getRed();
        green += this.kernel[i][j] * section[i][j].getGreen();
        blue += this.kernel[i][j] * section[i][j].getBlue();
      }
    }
    return new PixelImpl((int) Math.round(red), (int) Math.round(green), (int) Math.round(blue));
  }
}
