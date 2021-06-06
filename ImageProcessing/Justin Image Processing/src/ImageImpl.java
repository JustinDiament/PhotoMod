import java.util.List;


public class ImageImpl implements Image {

  private final List<List<Pixel>> image;

  // todo: throw an exception if the image doesn't have at least one pixel in it or is null
  public ImageImpl(List<List<Pixel>> image) {
    this.image = image;
  }

  @Override
  public Pixel getPixelAt(int x, int y) {
    return this.image.get(x).get(y);
  }
  // Note this has the first arraylist as horizontal and the others going vertically up off of it 

  @Override
  public int getWidth() {
    return image.size();
  }

  @Override
  public int getHeight() {
    return image.get(0).size();
  }

  @Override
  public void replacePixel(int x, int y, Pixel p) {
    this.image.get(x).set(y, p);
  }
}
