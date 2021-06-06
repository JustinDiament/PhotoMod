//import java.util.ArrayList;
//import java.util.List;
//
//public class BlurOperation implements ImageOperations {
//
//  @Override
//  public void apply(Image img) {
//    ImageUtil.requireNonNull(img);
//    // todo: nathan
//    // todo: interface for kernels? or additional method to override in class/abstract class?
//    //  enforce odd dimensions
//    double[][] kernel = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
//    int size = kernel.length;
//
//    List<List<Pixel>> copy = new ArrayList<>();
//
//    // traverse through every pixel in the given image
//    for (int i = 0; i < img.getPixels().size(); i++) {
//      List<Pixel> rowCopy = new ArrayList<>();
//      for (int j = 0; j < img.getPixels().get(i).size(); j++) {
//        // grab the section of pixels around the current pixel
//        Pixel[][] section = new Pixel[size][size];
//        for (int k = i; k < size; k++) {
//          for (int l = j; l < size; l++) {
//            Pixel pixel;
//            try {
//              pixel = img.getPixels().get(k - (size / 2)).get(l - (size / 2));
//            } catch (IndexOutOfBoundsException e) {
//              pixel = new PixelImpl(0, 0, 0);
//            }
//            section[k][l] = pixel;
//          }
//        }
//        // for each pixel color, calculate the sum of the product of each kernel value multiplied
//        // by its corresponding section value
//        int red = 0;
//        int green = 0;
//        int blue = 0;
//        for (int a = 0; a < size; a++) {
//          for (int b = 0; b < size; b++) {
//            red += (int) (kernel[a][b] * section[a][b].getRed());
//            green += (int) (kernel[a][b] * section[a][b].getGreen());
//            blue += (int) (kernel[a][b] * section[a][b].getBlue());
//          }
//        }
//        rowCopy.add(new PixelImpl(red, green, blue));
//      }
//      copy.add(rowCopy);
//    }
//  // return copy;
//  }
//}
