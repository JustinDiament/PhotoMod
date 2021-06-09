import model.image.Image;
import model.image.ImageProcessingModel;
import model.image.ImageProcessingModelImpl;
import model.operation.Operations;

/**
 * Main class used to run the image processing application.
 */
public class Main {

  /**
   * Main method used to run the program.
   *
   * @param args the command line arguments passed to the main method
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "res\\Koala.ppm";
    }

    ImageProcessingModel m = new ImageProcessingModelImpl();
    Image i = m.importImage(filename);
    Image i2 = m.applyOperation(i, Operations.MONOCHROME);
    m.exportImage("res\\Koala_monochrome.ppm", i2);
  }
}
