import java.util.ArrayList;
import java.util.List;


public class ImageImpl implements Image {

  private final List<List<Pixel>> image;

  public ImageImpl(List<List<Pixel>> image) throws IllegalArgumentException {
    this.image = this.getPixels(ImageUtil.requireNonNull(image));

  }

  @Override
  public Pixel getPixelAt(int x, int y) {
    return this.image.get(x).get(y);
  }


  private List<List<Pixel>> getPixels(List<List<Pixel>> image) {
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
  public int getWidth() {
    return this.image.size();
  }

  @Override
  public int getHeight() {
    return this.image.get(0).size();
  }

  @Override
  public void replacePixel(int x, int y, Pixel p) {
    this.image.get(x).set(y, p);
  }


//  public List<List<Integer>> getRed() {
//    List<List<Integer>> redValues = new ArrayList<>();
//    for (List<Pixel> row : this.image) {
//      List<Integer> redValueRow = new ArrayList<>();
//      for (Pixel pixel : row) {
//        redValueRow.add(pixel.getRed());
//      }
//      redValues.add(redValueRow);
//    }
//    return redValues;
//  }


}
