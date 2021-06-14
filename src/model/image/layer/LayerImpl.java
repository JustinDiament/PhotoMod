package model.image.layer;

// todo: implement layer and add public methods to layer

import java.util.ArrayList;
import java.util.List;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;

// todo: docstrings everywhere
public class LayerImpl implements Layer {

  private final Image img;
  // todo: are layers required to have names? (do we even need it for our implementation)
  private final String name;
  private boolean visible;


  public LayerImpl(Image img, String name, boolean visible) throws IllegalArgumentException {
    this.img = img;
    this.name = ImageUtil.requireNonNull(name);
    this.visible = visible;
  }

  public LayerImpl(Image img, String name) {
    this(img, name, false);
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
        row.add(new PixelImpl(p.getRed(), p.getGreen(),p.getBlue()));
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