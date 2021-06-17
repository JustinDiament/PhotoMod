package model.image.layer;

import java.util.ArrayList;
import java.util.List;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

/**
 * An implementation of a Layer of a multi-layered image. By default, this implementation of a Layer
 * is set to visible.
 */
public class LayerImpl implements Layer {

  private final Image img;
  private final String name;
  private boolean visible;

  /**
   * Creates a new Layer using the given Image, name, and visibility.
   *
   * @param img     the Image to store in this Layer
   * @param name    the name to give to this layer
   * @param visible whether the layer should be visible or invisible
   * @throws IllegalArgumentException if the given layer name is null
   */
  public LayerImpl(Image img, String name, boolean visible) throws IllegalArgumentException {
    this.img = img;
    this.name = ImageUtil.requireNonNull(name);
    this.visible = visible;
  }

  /**
   * Creates a new Layer with the visibility set to true by default.
   *
   * @param img  the Image to store in this layer
   * @param name the name to give to this layer
   * @throws IllegalArgumentException if the given layer name is null
   */
  public LayerImpl(Image img, String name) throws IllegalArgumentException {
    this(img, name, true);
  }

  @Override
  public Image getImage() {
    if (this.img == null) {
      return null;
    }

    List<List<Pixel>> copy = new ArrayList<>();
    for (int i = 0; i < img.getWidth(); i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < img.getHeight(); j++) {
        Pixel p = this.img.getPixelAt(i, j);
        row.add(new PixelImpl(p.getRed(), p.getGreen(), p.getBlue()));
      }
      copy.add(row);
    }
    return new ImageImpl(copy);
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setVisibility(boolean visible) {
    this.visible = visible;
  }

  @Override
  public boolean getVisibility() {
    return this.visible;
  }
}
