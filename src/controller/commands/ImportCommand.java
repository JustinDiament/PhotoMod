package controller.commands;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageLayerModel;

/**
 * Function object to import an Image. If the Image is a single, one layer image, it will be loaded
 * into the current layer. If it is a multi-layer Image, its layers will be loaded into separate
 * layers.
 */
public class ImportCommand extends FileCommand {

  @Override
  public void execute(Scanner scanner, ImageLayerModel model) {
    ImageUtil.requireNonNull(scanner);
    ImageUtil.requireNonNull(model);

    String filename = "";

    if (scanner.hasNext()) {
      filename = scanner.next();
    }

    if (scanner.hasNext()) {
      try {
        String extension = scanner.next().toLowerCase();

        // check if the provided filename is for a single or multi-layered image
        if (!extension.equals("txt")) {
          Image img = this.importImage(filename, extension);
          model.setCurrentLayerImage(img);
          model.verifyLayerDimensions(img);
          return;
        }

        // import the image files listed in the text file as ordered layers
        Scanner sc;
        try {
          sc = new Scanner(new FileInputStream(ImageUtil.requireNonNull(filename)));
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("File " + filename + " not found!");
        }

        int currentLayer = model.getCurrentLayerIndex();
        while (sc.hasNext()) {
          String path = sc.next();
          String ext = sc.next();
          String filepath = path.substring(0, path.indexOf("."));
          if (currentLayer == -1) {
            currentLayer = 0;
          }
          model.addLayer(filepath);
          model.setCurrentLayer(currentLayer);
          Image img = super.importImage(path, ext);
          model.setCurrentLayerImage(img);
          model.verifyLayerDimensions(img);
          model.setCurrentLayer(currentLayer++);
        }
      } catch (IllegalArgumentException e) {
        throw new IllegalArgumentException("Failed to import file.");
      }
    } else {
      throw new IllegalArgumentException("Not all specifications to import file provided.");
    }
  }
}
