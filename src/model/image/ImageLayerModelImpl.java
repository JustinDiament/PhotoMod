package model.image;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.ImageUtil;
import model.image.file.ImageFile;
import model.image.file.JPEG;
import model.image.file.PNG;
import model.image.layer.Layer;
import model.image.layer.LayerImpl;
import model.image.programmatic.ProgrammaticCreator;
import model.operation.Operations;

// todo: docstrings everywhere, add public methods to interface
public class ImageLayerModelImpl extends ImageProcessingModelImpl implements ImageLayerModel {

  private final List<Layer> layers;
  private int currentLayer;

  public ImageLayerModelImpl() {
    this.layers = new ArrayList<>();
    this.currentLayer = -1;
  }

  @Override
  public void addLayer(String name) {
    this.layers.add(new LayerImpl(null, name));
  }

  @Override
  public void setCurrentLayerImage(Image img) {
    Layer current = this.getCurrentLayer();
    this.layers
        .set(this.currentLayer, new LayerImpl(img, current.getName(), current.getVisibility()));
  }

  @Override
  public void setCurrentLayer(int index) {
    this.isValidLayer(index);
    this.currentLayer = index;
  }

  /**
   * Checks if the given index corresponds to a layer in the list of layers.
   *
   * @param index the index to check for validity
   * @throws IllegalArgumentException if the index falls outside the bounds of the list of layers
   */
  private void isValidLayer(int index) throws IllegalArgumentException {
    if (index < 0 || index >= layers.size()) {
      throw new IllegalArgumentException("Layer index is invalid");
    }
  }

  @Override
  public Layer getCurrentLayer() throws IllegalArgumentException {
    this.isValidLayer(this.currentLayer);
    return this.layers.get(this.currentLayer);
  }

  @Override
  public void setCurrentLayerVisibility(boolean visibility) throws IllegalArgumentException {
    this.isValidLayer(this.currentLayer);
    this.getCurrentLayer().setVisibility(visibility);
  }

  @Override
  public List<String> getLayerNames() {
    List<String> layerNames = new ArrayList<>();
    for (Layer layer : layers) {
      layerNames.add(layer.getName());
    }
    return layerNames;
  }

  @Override
  public void removeLayer(int index) throws IllegalArgumentException {
    this.isValidLayer(index);
    this.layers.remove(index);
  }

  @Override
  public Image applyOperation(Image img, Operations o) throws IllegalArgumentException {
    this.isValidLayer(this.currentLayer);
    Image newImage = super.applyOperation(img, o);
    layers.set(this.currentLayer, new LayerImpl(newImage, this.getCurrentLayer().getName(),
        this.getCurrentLayer().getVisibility()));
    return new ImageImpl(newImage);
  }

  @Override
  public Image createProgrammaticImage(ProgrammaticCreator creator)
      throws IllegalArgumentException {
    this.isValidLayer(this.currentLayer);
    Image newImage = super.createProgrammaticImage(creator);
    layers.set(this.currentLayer, new LayerImpl(newImage, this.getCurrentLayer().getName(),
        this.getCurrentLayer().getVisibility()));
    return new ImageImpl(newImage);
  }

  @Override
  public Image importImage(String filename) throws IllegalArgumentException {
    // initialize local variables
    ImageUtil.requireNonNull(filename);
    String extension = filename.substring(filename.indexOf(".") + 1);

    // check if the provided filename is for a single or multi-layered image
    if (!extension.equals("txt")) {
      Image img = super.importImage(filename);
      this.setCurrentLayerImage(img);
      this.verifyLayerDimensions(img);
      return img;
    }

    // import the image files listed in the text file as ordered layers
    Scanner sc;
    try {
      sc = new Scanner(new FileInputStream(ImageUtil.requireNonNull(filename)));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    while (sc.hasNext()) {
      String s = sc.next();
      String filepath = s.substring(0, s.indexOf("."));
      this.addLayer(filepath);
      this.setCurrentLayer(this.currentLayer == -1 ? 0 : this.currentLayer);
      Image img = super.importImage(s);
      this.setCurrentLayerImage(img);
      this.verifyLayerDimensions(img);
      this.currentLayer++;
    }
    return this.layers.get(this.layers.size() - 1).getImage();
  }

  @Override
  public void exportImage(String filename, Image img) throws IllegalArgumentException {
    try {
      // initialize local variables
      ImageUtil.requireNonNull(filename);
      String extension = filename.substring(filename.indexOf(".") + 1);
      String path = filename.substring(0, filename.indexOf("."));
      StringBuilder sb = new StringBuilder();
      String lineSeparator = System.lineSeparator();

      // create a directory to store all files
      File f = new File(filename);
      if (!f.getParentFile().mkdir() && Files.notExists(Path.of(String.valueOf(f.getParentFile())))) {
        throw new IllegalArgumentException("Unable to create a directory for the layered image");
      }

      // create a text file to store the files paths of all images
      File txt = new File(path + ".txt");
      if (!txt.createNewFile() && !Files.exists(Paths.get(path + ".txt"))) {
        throw new IOException();
      }
      FileWriter txtWriter = new FileWriter(txt.getPath());

      // export all layers as separate images and write their paths to the text file
      for (Layer l : this.layers) {
        String imagePath = path + l.getName() + "." + extension;
        super.exportImage(imagePath, l.getImage());
        sb.append(imagePath).append(lineSeparator);
      }

      txtWriter.write(sb.toString());
      txtWriter.close();
    } catch (NullPointerException | IOException e) {
      throw new IllegalArgumentException("Unable to create a text file");
    }
  }

  @Override
  public void exportTopImage(String filename) throws IllegalArgumentException {
    ImageUtil.requireNonNull(filename);

    for (int i = this.layers.size() - 1; i >= 0; i--) {
      Layer l = this.layers.get(i);
      if (l.getVisibility()) {
        super.exportImage(filename, l.getImage());
        return;
      }
    }
    throw new IllegalArgumentException("No layers are visible");
  }

  /**
   * Checks that the given image shares the same dimensions as the rest of the layers.
   *
   * @param img the image to check the dimensions of
   * @throws IllegalArgumentException if the given image has different dimensions
   */
  private void verifyLayerDimensions(Image img) throws IllegalArgumentException {
    if (this.layers.size() == 0) {
      return;
    }
    for (Layer l : this.layers) {
      Image i = l.getImage();
      if ((i != null) && (i.getWidth() != img.getWidth() || i.getHeight() != img.getHeight())) {
        throw new IllegalArgumentException("Layers in the model have differing dimensions");
      }
    }
  }

  @Override
  protected Map<String, ImageFile> getFiles() {
    Map<String, ImageFile> files = super.getFiles();
    files.put("jpeg", new JPEG());
    files.put("jpg", new JPEG());
    files.put("png", new PNG());
    return files;
  }
}
