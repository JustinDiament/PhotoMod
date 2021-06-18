import java.util.List;
import model.image.Image;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.Pixel;
import model.image.layer.Layer;
import model.image.programmatic.ProgrammaticCreator;
import model.operation.Operations;

public class ImageLayerMockModel implements ImageLayerModel {

  private final StringBuilder log;
  private final ImageLayerModel delegate;

  ImageLayerMockModel(StringBuilder log) {
    this.log = log;
    this.delegate = new ImageLayerModelImpl();
  }

  @Override
  public void addLayer(String name) throws IllegalArgumentException {
    log.append("New layer created with this name: ").append(name).append("\n");
    delegate.addLayer(name);
  }

  @Override
  public void setCurrentLayerImage(Image img) throws IllegalArgumentException {
    log.append("Current layer image set\n");
    delegate.setCurrentLayerImage(img);
  }

  @Override
  public void setCurrentLayer(int index) throws IllegalArgumentException {
    log.append("Current layer changed to layer with this index: ").append(index).append("\n");
    delegate.setCurrentLayer(index);
  }

  @Override
  public Layer getCurrentLayer() throws IllegalArgumentException {
    log.append("Current layer searched for\n");
    return null;
  }

  @Override
  public void setCurrentLayerVisibility(boolean visibility) throws IllegalArgumentException {
  }

  @Override
  public List<String> getLayerNames() {
    return null;
  }

  @Override
  public void removeLayer(int index) throws IllegalArgumentException {
  }

  @Override
  public void exportTopImage(String filename, String extension) throws IllegalArgumentException {
  }

  @Override
  public Pixel getPixelInCurrentLayerAt(int x, int y)
      throws IllegalArgumentException, IllegalStateException {
    return null;
  }

  @Override
  public List<List<Pixel>> getCurrentLayerImagePixels() throws IllegalStateException {
    return null;
  }

  @Override
  public Image getCurrentLayerImage() throws IllegalStateException {
    return null;
  }

  @Override
  public String getCurrentLayerName() {
    return null;
  }

  @Override
  public boolean getCurrentLayerVisibility() {
    return false;
  }

  @Override
  public Image applyOperation(Image img, Operations o) throws IllegalArgumentException {
    return null;
  }

  @Override
  public Image createProgrammaticImage(ProgrammaticCreator creator)
      throws IllegalArgumentException {
    return null;
  }

  @Override
  public Image importImage(String filename, String extension) throws IllegalArgumentException {
    return null;
  }

  @Override
  public void exportImage(String filename, String extension, Image img)
      throws IllegalArgumentException {
  }
}
