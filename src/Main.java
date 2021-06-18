import controller.ImageController;
import controller.ImageControllerImpl;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;

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
    System.out.println("Supported commands:\nblur\nsharpen\nsepia\nmonochrome\ncurrent\ncreatelayer"
        + "\nremovelayer\nvisibility\ncreatecheckerboard\nimport\nexportall\nexport\n");

    ImageLayerModel model = new ImageLayerModelImpl();
    ImageController controller = new ImageControllerImpl(model);
    controller.run();
  }
}
