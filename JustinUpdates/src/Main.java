import controller.ImageController;
import controller.ImageControllerImpl;
import java.util.ArrayList;
import java.util.List;
import model.image.Image;
import model.image.ImageImpl;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.ImageProcessingModel;
import model.image.ImageProcessingModelImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import model.image.file.ImageFile;
import model.image.file.JPEG;
import model.image.file.PNG;
import model.image.file.PPM;
import model.image.layer.Layer;
import model.image.layer.LayerImpl;
import model.operation.BlurOperation;
import model.operation.ImageOperation;
import model.operation.Operations;
import model.operation.SepiaOperation;
import model.operation.SharpenOperation;

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
