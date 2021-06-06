import java.util.List;

public interface Image {
  
  // TODO: get rgb channel methods?



  Pixel getPixelAt(int x, int y);


  int getWidth();

  int getHeight();

  public void replacePixel(int x, int y, Pixel p);


//  List<List<Pixel>> getPixels();

//  List<List<Integer>> getRed();
//
//  List<List<Integer>> getGreen();
//
//  List<List<Integer>> getBlue();


}
