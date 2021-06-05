import java.util.List;


public class ImageImpl implements Image {

  private final List<List<PixelImpl>> image;

  public ImageImpl(List<List<PixelImpl>> image) {
    this.image = image;
  }

  @Override
  public PixelImpl getPixelAt(int x, int y) {
    return this.image.get(x).get(y);
  }
}
