import java.util.List;


public class ImageImpl implements Image {

  private final List<List<Pixel>> image;

  public ImageImpl(List<List<Pixel>> image) {
    this.image = image;
  }

  @Override
  public Pixel getPixelAt(int x, int y) {
    return this.image.get(x).get(y);
  }
}
