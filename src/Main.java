import controller.ImageController;
import controller.ImageControllerImpl;
import java.util.Scanner;
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

    System.out.println(
        "Enter '1' to run ScriptOne.txt, '2' to run ScriptTwo.txt, '3' to run from the command line, or '0' to quit");
    ImageController controller = null;
    ImageLayerModel model = new ImageLayerModelImpl();
    Scanner sc = new Scanner(System.in);

    while (controller == null) {
      int i = sc.nextInt();
      switch (i) {
        case 0:
          return;
        case 1:
          controller = new ImageControllerImpl(model, "res\\ScriptOne.txt", System.out);
          break;
        case 2:
          controller = new ImageControllerImpl(model, "res\\ScriptTwo.txt", System.out);
          break;
        case 3:
          controller = new ImageControllerImpl(model);
          break;
        default:
          System.out.println("Please enter a valid number");
          break;
      }
    }
    controller.run();
  }
}
