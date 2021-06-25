import controller.ImageController;
import controller.ImageControllerImpl;
import controller.ImageInteractiveController;
import controller.ImageInteractiveControllerImpl;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import view.ImageView;
import view.ImageViewImpl;

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

    if (args.length < 1) {
      System.out.println("Must specify the mode to run this application");
    } else {
      ImageLayerModel model = new ImageLayerModelImpl();
      switch (args[0]) {
        case "-script":
          if (args.length < 2) {
            System.out.println("Must specify the script filepath");
          } else {
            ImageController controller = new ImageControllerImpl(model, args[1], System.out);
            controller.run();
          }
          break;
        case "-text":
          ImageController controller = new ImageControllerImpl(model);
          controller.run();
          break;
        case "-interactive":
          ImageView view = new ImageViewImpl();
          ImageInteractiveController interactiveController = new ImageInteractiveControllerImpl(
              model, view);
          interactiveController.run();
          break;
        default:
          System.out.println("Invalid mode provided");
      }
    }
  }
}
