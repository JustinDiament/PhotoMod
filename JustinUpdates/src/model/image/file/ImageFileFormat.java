package model.image.file;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * Abstracts out common functionality that is used to handle more popular image file formats.
 */
abstract class ImageFileFormat implements ImageFile {

  @Override
  public Image importFile(String filename) throws IllegalArgumentException {
    ImageUtil.requireNonNull(filename);
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(filename));
    }
    catch (IOException e) {
      System.out.println("File " + filename + " not found!");
    }
    ImageUtil.requireNonNull(img);

    List<List<Pixel>> pixels = new ArrayList<>();
    for (int i = 0; i < img.getHeight(); i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < img.getWidth(); j++) {
        Color c = new Color(img.getRGB(j, i));
        row.add(new PixelImpl(c.getRed(), c.getGreen(), c.getBlue()));
      }
      pixels.add(row);
    }
    return this.transpose(pixels);
  }

  @Override
  public void exportFile(String filename, Image img) throws IllegalArgumentException {
    ImageUtil.requireNonNull(img);
    ImageUtil.requireNonNull(filename);
    BufferedImage image = new BufferedImage(img.getWidth(), img.getHeight(),
        BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        Pixel p = img.getPixelAt(j, i);
        Color c = new Color(p.getRed(), p.getGreen(), p.getBlue());
        image.setRGB(j, i, c.getRGB());
      }
    }

    try {
      ImageIO.write(image, this.getExtension(), new File(filename));
    }
    catch (IOException e) {
      throw new IllegalArgumentException(
          String.format("Failed to export the image as a %s file", this.getExtension()));
    }
  }

  /**
   * Gets the extension of the image file format as a String.
   *
   * @return the extension of the specific image file format
   */
  protected abstract String getExtension();

  /**
   * Creates an Image with the given matrix of Pixels transposed.
   *
   * @param pixels the matrix of pixels to be transposed and used to create an Image
   * @return an image using the given pixels transposed
   * @throws IllegalArgumentException if the given pixels are null
   */
  protected Image transpose(List<List<Pixel>> pixels) throws IllegalArgumentException {
    ImageUtil.requireNonNull(pixels);
    List<List<Pixel>> pixelsTranspose = new ArrayList<>();
    for (int i = 0; i < pixels.get(0).size(); i++) {
      List<Pixel> column = new ArrayList<>();
      for (List<Pixel> pixel : pixels) {
        column.add(pixel.get(i));
      }
      pixelsTranspose.add(column);
    }
    return new ImageImpl(pixelsTranspose);
  }
}
