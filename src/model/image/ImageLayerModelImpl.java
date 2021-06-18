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

/**
 * Represents the model of an Image modification program. The model processes Images provided by the
 * user and modifies it with operations such as filters chosen by the user. The model stores Images
 * that it processes within Layers that can be individually manipulated, as well as index that
 * points to the current layer to manipulate. The model also provides the ability to import and
 * export both single and multi-layered images.
 */
public class ImageLayerModelImpl extends ImageProcessingModelImpl implements ImageLayerModel {

  private final List<Layer> layers;
  private int currentLayer;

  /**
   * Constructs a layered image processing model that is able to handle image files and perform
   * operations on Image objects that are stored as Layers.
   */
  public ImageLayerModelImpl() {
    this.layers = new ArrayList<>();
    this.currentLayer = -1;
  }

  @Override
  public void addLayer(String name) throws IllegalArgumentException {
    this.layers.add(new LayerImpl(null, name));
  }

  @Override
  public void setCurrentLayerImage(Image img) throws IllegalArgumentException {
    Layer current = this.getCurrentLayer();
    this.layers
        .set(this.currentLayer, new LayerImpl(img, current.getName(), current.getVisibility()));
  }

  @Override
  public void setCurrentLayer(int index) throws IllegalArgumentException {
    if (index == -1) {
      this.currentLayer = index;
      return;
    }
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
  public Layer getCurrentLayer() throws IllegalStateException {
    try {
      this.isValidLayer(this.currentLayer);
    }
    catch (IllegalArgumentException e) {
      throw new IllegalStateException("Current layer index is invalid");
    }
    return this.layers.get(this.currentLayer);
  }

  @Override
  public void setCurrentLayerVisibility(boolean visibility) throws IllegalStateException {
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
  public Image importImage(String filename, String extension) throws IllegalArgumentException {
    // initialize local variables
    ImageUtil.requireNonNull(filename);
    ImageUtil.requireNonNull(extension);

    // check if the provided filename is for a single or multi-layered image
    if (!extension.equals("txt")) {
      Image img = super.importImage(filename, extension);
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
      String path = sc.next();
      String ext = sc.next();
      String filepath = path.substring(0, path.indexOf("."));
      this.addLayer(filepath);
      this.setCurrentLayer(this.currentLayer == -1 ? 0 : this.currentLayer);
      Image img = super.importImage(path, ext);
      this.setCurrentLayerImage(img);
      this.verifyLayerDimensions(img);
      this.currentLayer++;
    }
    return this.layers.get(this.layers.size() - 1).getImage();
  }

  @Override
  public void exportImage(String filename, String extension, Image img) throws IllegalArgumentException {
    try {
      // initialize local variables
      ImageUtil.requireNonNull(filename);
      ImageUtil.requireNonNull(extension);
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
      for (Layer l : this.layers) {
        String imagePath = filename + l.getName() + "." + extension;
        super.exportImage(imagePath, extension, l.getImage());
        sb.append(imagePath).append(" ").append(extension).append(lineSeparator);
      }

      txtWriter.write(sb.toString());
      txtWriter.close();
    } catch (NullPointerException | IOException e) {
      throw new IllegalArgumentException("Unable to create a text file");
    }
  }

  @Override
  public void exportTopImage(String filename, String extension) throws IllegalArgumentException {
    ImageUtil.requireNonNull(filename);
    ImageUtil.requireNonNull(extension);

    for (int i = this.layers.size() - 1; i >= 0; i--) {
      Layer l = this.layers.get(i);
      if (l.getVisibility()) {
        super.exportImage(filename, extension, l.getImage());
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

  @Override
  public Pixel getPixelInCurrentLayerAt(int x, int y)
      throws IllegalArgumentException, IllegalStateException {

    if (this.getCurrentLayer().getImage() != null) {
      return this.getCurrentLayer().getImage().getPixelAt(x, y);
    }
    throw new IllegalStateException("Current layer has no Image");
  }

  @Override
  public List<List<Pixel>> getCurrentLayerImagePixels() throws IllegalStateException {
    Image currentImage = this.getCurrentLayer().getImage();

    if (currentImage != null) {
      List<List<Pixel>> pixels = new ArrayList<>();
      for (int i = 0; i < currentImage.getWidth(); i++) {
        pixels.add(new ArrayList<>());
        for (int j = 0; j < currentImage.getHeight(); j++) {
          pixels.get(i).add(currentImage.getPixelAt(i, j));
        }
      }
      return pixels;
    }
    throw new IllegalStateException("Current layer has no Image");
  }

  @Override
  public Image getCurrentLayerImage() throws IllegalStateException {
    if (this.getCurrentLayer().getImage() != null) {
      return this.getCurrentLayer().getImage();
    }
    throw new IllegalStateException("Current layer has no Image");
  }

  @Override
  public String getCurrentLayerName() throws IllegalStateException {
    return this.getCurrentLayer().getName();
  }

  @Override
  public boolean getCurrentLayerVisibility() throws IllegalStateException {
    return this.getCurrentLayer().getVisibility();
  }
}
