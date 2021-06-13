package model.image;

// TODO: if we need new public methods, add ImageLayerModel interface that extends existing
//  interface

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.image.file.ImageFile;
import model.image.layer.Layer;
import model.image.layer.LayerImpl;
import model.image.programmatic.ProgrammaticCreator;
import model.operation.ImageOperation;
import model.operation.Operations;

// todo: controller has currentLayer field that gets mutated based on user input

public class ImageLayerModelImpl extends ImageProcessingModelImpl implements ImageLayerModel {

  // todo: document why we need these fields
  private final List<Layer> layers;
  private int currentLayer;

  public ImageLayerModelImpl() {
    this.layers = new ArrayList<>();
    this.currentLayer = -1;
  }

  @Override
  // todo: add this to new interface
  public void addLayer(String name) {
    this.layers.add(null);
  }

  // todo: add this to new interface
  @Override
  public void setCurrentLayer(int index) {
    this.isValidLayer(index);
    this.currentLayer = index;
  }

  private void isValidLayer(int index) throws IllegalArgumentException {
    if (index < 0 || index >= layers.size()) {
      throw new IllegalArgumentException("Provided index layer is out of bounds");
    }
  }

  @Override
  public List<String> getLayerNames() {
    //todo: write
    return null;
  }

  @Override
  public void removeLayer(int index) throws IllegalArgumentException {
    //todo: write
  }

  // todo: add this to new interface
  @Override
  public Layer getCurrentLayer() throws IllegalArgumentException {
    this.isValidLayer(this.currentLayer);
    return this.layers.get(this.currentLayer);
  }

  // todo: add this to new interface
  @Override
  public void setCurrentLayerVisibility(boolean visibility) {
    this.getCurrentLayer().setVisibility(visibility);
  }

  @Override
  public Image applyOperation(Image img, Operations o) throws IllegalArgumentException {
    Image newImage = super.applyOperation(img, o);
    layers.set(this.currentLayer, new LayerImpl(newImage, this.layers.get(currentLayer).getName(),
        this.layers.get(currentLayer).getVisibility()));
    return newImage;
  }

  @Override
  public Image createProgrammaticImage(ProgrammaticCreator creator)
      throws IllegalArgumentException {
    Image newImage = super.createProgrammaticImage(creator);
    layers.set(this.currentLayer, new LayerImpl(newImage, this.layers.get(currentLayer).getName(),
        this.layers.get(currentLayer).getVisibility()));
    return newImage;
  }

  @Override
  public Image importImage(String filename) throws IllegalArgumentException {
    return null;
    // todo: make sure all images in multi-layered image have same dimensions
  }

  @Override
  public void exportImage(String filename, Image img) throws IllegalArgumentException {
    // todo: figure out how to export multi-layered images (see chat)
  }

  // todo: export method for layers

  @Override
  protected Map<String, ImageFile> getFiles() {
    Map<String, ImageFile> files = super.getFiles();
    // files.put("jpeg", new JPEG());
    // files.put("jpg", new JPEG());
    // files.put("png", new PNG());
    return files;
  }


}
