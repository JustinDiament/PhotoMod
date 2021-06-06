import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlurOperation extends FilterOperation {

  @Override
  public void apply(Image img) {
    ImageUtil.requireNonNull(img);

    List<List<Pixel>> copy = new ArrayList<>();
    for (int i = 0; i < img.getWidth(); i++) {
      List<Pixel> rowCopy = new ArrayList<>();
      for (int j = 0; j < img.getHeight(); j++) {
        Pixel pixel = img.getPixelAt(i, j);
        rowCopy.add(new PixelImpl(pixel.getRed(), pixel.getGreen(), pixel.getBlue()));
      }
      copy.add(rowCopy);
    }

    Image imgCopy = new ImageImpl(copy);

    int size = this.kernel.length;

    // traverse through every pixel in the given image
    for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight(); j++) {
        // grab the section of pixels around the current pixel
        Pixel[][] section = this.getImageSection(img, i, j, this.kernel.length);
//        Pixel[][] section = new Pixel[size][size];
//        for (int k = i; k < i + size; k++) {
//          for (int l = j; l < j + size; l++) {
//            Pixel pixel;
//            try {
//              pixel = img.getPixelAt(k - (size / 2), l - (size / 2));
//            } catch (IndexOutOfBoundsException e) {
//              pixel = new PixelImpl(0, 0, 0);
//            }
//            section[k][l] = pixel;
//          }
//        }
        // for each pixel color, calculate the sum of the product of each kernel value multiplied
        // by its corresponding section value
        this.filterPixel(imgCopy, i, j, section);
//        int red = 0;
//        int green = 0;
//        int blue = 0;
//        for (int a = 0; a < size; a++) {
//          for (int b = 0; b < size; b++) {
//            red += (int) (this.kernel[a][b] * section[a][b].getRed());
//            green += (int) (this.kernel[a][b] * section[a][b].getGreen());
//            blue += (int) (this.kernel[a][b] * section[a][b].getBlue());
//          }
//        }
//        img.replacePixel(i, j, new PixelImpl(red, green, blue));
      }
    }
//    img =  new ImageImpl(copy);
    for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight(); j++) {
        img.replacePixel(i, j, imgCopy.getPixelAt(i, j));
      }
    }
    // TODO: issue is that changing the rgb values of previous pixels affects calculation for future pixels
  }

  private Pixel[][] getImageSection(Image img, int x, int y, int kernelSize) {
    Pixel[][] section = new Pixel[kernelSize][kernelSize];
//    int boundary = kernelSize / 2;
//    for (int i = x - boundary; i <= x + boundary; i++) {
//      for (int j = y - boundary; j <= y + boundary; j++) {
//        Pixel pixel;
//        try {
//          pixel = img.getPixelAt(i, j);
//        } catch (IndexOutOfBoundsException e) {
//          pixel = new PixelImpl(0, 0, 0);
//        }
//        section[i + boundary][j + boundary] = pixel;
//      }
//    }
    int sectionX = 0;
    for (int i = x; i < x + kernelSize; i++) {
      int sectionY = 0;
      for (int j = y; j < y + kernelSize; j++) {
        Pixel pixel;
        try {
          pixel = img.getPixelAt(i - (kernelSize / 2), j - (kernelSize / 2));
        } catch (IndexOutOfBoundsException e) {
          pixel = new PixelImpl(0, 0, 0);
        }
        section[sectionX][sectionY] = pixel;
        sectionY++;
      }
      sectionX++;
    }

    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print("R: " + section[i][j].getRed() + " G: " + section[i][j].getGreen() + " B: " + section[i][j].getBlue() + "              ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println();

    return section;
  }


  private void filterPixel(Image img, int x, int y, Pixel[][] section) {
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
    img.replacePixel(x, y, new PixelImpl((int) red, (int) green, (int) blue));
  }

  @Override
  protected double[][] getKernel() {
    double[][] section = {{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
    //return new double[][]{{0.0625, 0.125, 0.0625}, {0.125, 0.25, 0.125}, {0.0625, 0.125, 0.0625}};
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        System.out.print("R: " + section[i][j] + " G: " + section[i][j] + " B: " + section[i][j] + "              ");
      }
      System.out.println();
    }
    System.out.println();
    System.out.println();
    return section;
  }


  public static void main(String[] args) {
    List<List<Pixel>> lp = new ArrayList<>();
    lp.add(Arrays
        .asList(new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10)));
    lp.add(Arrays
        .asList(new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10)));
    lp.add(Arrays
        .asList(new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10)));
    lp.add(Arrays
        .asList(new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10)));
    lp.add(Arrays
        .asList(new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10), new PixelImpl(10, 10, 10),
            new PixelImpl(10, 10, 10)));
    Image img = new ImageImpl(lp);

    for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight(); j++) {
        System.out.print("R: " + img.getPixelAt(i, j).getRed() + " G: " + img.getPixelAt(i, j).getGreen() + " B: " + img.getPixelAt(i, j).getBlue() + " |    ");
      }
      System.out.println();
    }

    ImageOperation b = new BlurOperation();
    b.apply(img);


    System.out.println();
    System.out.println();
    System.out.println();


    for (int i = 0; i < img.getWidth(); i++) {
      for (int j = 0; j < img.getHeight(); j++) {
        System.out.print("R: " + img.getPixelAt(i, j).getRed() + " G: " + img.getPixelAt(i, j).getGreen() + " B: " + img.getPixelAt(i, j).getBlue() + " |    ");
      }
      System.out.println();
    }

  }
}
