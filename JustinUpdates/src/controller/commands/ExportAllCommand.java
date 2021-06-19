package controller.commands;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import model.ImageUtil;
import model.image.Image;
import model.image.ImageLayerModel;

/**
 * Function object to export all layers of an Image as a multi-layer Image.
 */
public class ExportAllCommand extends FileCommand {

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

        StringBuilder sb = new StringBuilder();
        String lineSeparator = System.lineSeparator();

        // create a directory to store all files
        File txt = new File(filename + ".txt");
        if (!txt.getParentFile().mkdir() && Files
            .notExists(Path.of(String.valueOf(txt.getParentFile())))) {
          throw new IllegalArgumentException("Unable to create a directory for the layered image");
        }

        // create a text file to store the files paths of all images
        if (!txt.createNewFile() && !Files.exists(Paths.get(filename + ".txt"))) {
          throw new IOException();
        }
        FileWriter txtWriter = new FileWriter(txt.getPath());

        // export all layers as separate images and write their paths to the text file
        List<String> names = model.getLayerNames();
        List<Image> images = model.getLayerImages();

        for (int i = 0; i < names.size(); i++) {
          String imagePath = filename + names.get(i) + "." + extension;
          this.exportImage(imagePath, extension, images.get(i));
          sb.append(imagePath).append(" ").append(extension).append(lineSeparator);
        }

        txtWriter.write(sb.toString());
        txtWriter.close();
      } catch (NullPointerException | IOException | IllegalArgumentException e) {
        throw new IllegalArgumentException("Failed to export file.");
      }
    } else {
      throw new IllegalArgumentException("Not all specifications to export file provided.");
    }
  }
}
