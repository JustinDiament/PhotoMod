import java.util.List;
import model.image.Image;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.Pixel;
import model.image.layer.Layer;
import model.image.programmatic.ProgrammaticCreator;
import model.operation.Operations;

/**
 * A mock model for testing purposes only. Writes information about actions that modify the Image
 * being edited by the actual model's Image modification program to a StringBuilder to confirm
 * that information is being correctly parsed in the controller and passed to the model. All actual
 * Image modification is delegated to an instance of ImageLayerModelImpl, an actual proper
 * implementation of the ImageLayerModel interface.
 */
public class ImageLayerMockModel implements ImageLayerModel {

  private final StringBuilder log;
  private final ImageLayerModel delegate;

  /**
   * Constructs a ImageLayerMockModel object with a StringBuilder to record information about
   * the information the model receives given.
   *
   * @param log a StringBuilder object to record information about the the data the model receives
   */
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
    return delegate.getCurrentLayer();
  }

  @Override
  public void setCurrentLayerVisibility(boolean visibility) throws IllegalArgumentException {
    log.append("Current layer changed to this visibility level: ").append(visibility).append("\n");
    delegate.setCurrentLayerVisibility(visibility);
  }

  @Override
  public List<String> getLayerNames() {
    return delegate.getLayerNames();
  }

  @Override
  public List<Image> getLayerImages() {
    // todo
    return null;
  }

  @Override
  public void removeLayer(int index) throws IllegalArgumentException {
    log.append("Removed layer at this index: ").append(index).append("\n");
    delegate.removeLayer(index);
  }

  @Override
  public Pixel getPixelInCurrentLayerAt(int x, int y)
      throws IllegalArgumentException, IllegalStateException {
    return delegate.getPixelInCurrentLayerAt(x, y);
  }

  @Override
  public List<List<Pixel>> getCurrentLayerImagePixels() throws IllegalStateException {
    return delegate.getCurrentLayerImagePixels();
  }

  @Override
  public Image getCurrentLayerImage() throws IllegalStateException {
    return delegate.getCurrentLayerImage();
  }

  @Override
  public String getCurrentLayerName() {
    return delegate.getCurrentLayerName();
  }

  @Override
  public boolean getCurrentLayerVisibility() {
    return delegate.getCurrentLayerVisibility();
  }

  @Override
  public void verifyLayerDimensions(Image img) throws IllegalArgumentException {
    this.delegate.verifyLayerDimensions(img);
  }

  @Override
  public int getCurrentLayerIndex() {
    return this.delegate.getCurrentLayerIndex();
  }

  @Override
  public Image getTopImage() throws IllegalArgumentException {
    this.log.append("Export occurring\n");
    return this.delegate.getTopImage();
  }

  @Override
  public Image applyOperation(Image img, Operations o) throws IllegalArgumentException {
    log.append("Applied this type of operation to the current layer: ").append(o).append("\n");
    return delegate.applyOperation(img, o);
  }

  @Override
  public Image createProgrammaticImage(ProgrammaticCreator creator)
      throws IllegalArgumentException {
    log.append("Created programmatic image of this type: ").append(creator.getClass()).append("\n");
    return delegate.createProgrammaticImage(creator);
  }
}
