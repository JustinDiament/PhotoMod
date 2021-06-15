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
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "res\\n\\popeyes_test.txt";
    }

//    ImageLayerModel l = new ImageLayerModelImpl();
//    l.addLayer("eee");
//    l.setCurrentLayer(0);
//    l.importImage(filename);
//    l.addLayer("asdfffff");
//    l.setCurrentLayer(0);
//    l.setCurrentLayerImage(new PNG().importFile("res\\n\\popeyes_test.png"));
//    l.exportImage("res\\n\\popeyes_test.png", null);

//    ImageProcessingModel m = new ImageProcessingModelImpl();
//    Image i = m.importImage(filename);
//    Image i2 = m.applyOperation(i, Operations.BLUR);
//    m.exportImage("res\\popeyes_blur.ppm", i2);


//    ImageFile ppm = new PPM();
//    ImageFile jpeg = new JPEG();
//    ImageFile png = new PNG();
//    ImageOperation o = new SepiaOperation();
//    ImageOperation b = new BlurOperation();
//    ImageOperation s = new SharpenOperation();
//    Image i = png.importFile(filename);
//    Image i2 = s.apply(i);
//    png.exportFile("res\\popeyes_sharpen.png", i2);

    List<List<Pixel>> li = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      List<Pixel> row = new ArrayList<>();
      for (int j = 0; j < 5; j++) {
        row.add(new PixelImpl(0, 0, 0));
      }
      li.add(row);
    }
    Image aa = new ImageImpl(li);
    ImageLayerModel lm = new ImageLayerModelImpl();
    lm.addLayer("");
    lm.setCurrentLayer(0);
    lm.setCurrentLayerImage(aa);
    lm.exportImage("res\\test_bad.jpg", null);
  }
}
