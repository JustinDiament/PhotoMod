import model.image.Image;
import view.Features;
import view.ImageView;

/**
 * Represent a mock Image view for testing purposes. Produces responses and writes to a log to
 * indicate that other classes methods have correctly interacted with a real view, an interaction
 * which cannot normally be tested due to the view's use of the Swing GUI.
 */
public class ImageMockView implements ImageView {

  StringBuilder log;

  /**
   * Produces an ImageMockView object that writes information about called methods to its log
   * object.
   *
   * @param log a StringBuilder object to write information about called methods to
   */
  public ImageMockView(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void addViewEventListener(Features feature) throws IllegalArgumentException {
    this.log.append("Added a new listener: ").append(feature.getClass()).append("\n");
  }

  @Override
  public void renderImage(Image img) {
    this.log.append("Rendered a new image.\n");
  }

  @Override
  public String getXScale() {
    this.log.append("Got XScale. Mock returned 55.\n");
    return "55";
  }

  @Override
  public String getYScale() {
    this.log.append("Got YScale. Mock returned 65.\n");
    return "65";
  }

  @Override
  public String getSeeds() {
    this.log.append("Got seeds. Mock returned 100.\n");
    return "100";
  }

  @Override
  public String getCheckerboardSize() {
    this.log.append("Got chosen checkerboard size. Mock returned 16.\n");
    return "16";
  }

  @Override
  public String getNumSquares() {
    this.log.append("Got chosen number of checkerboard squares. Mock returned 4.\n");
    return "4";
  }

  @Override
  public String getColorOne() {
    this.log.append("Got chosen color one for checkerboard. Mock returned white\n");
    return "white";
  }

  @Override
  public String getColorTwo() {
    this.log.append("Got chosen color two for checkerboard. Mock returned black\n");
    return "black";
  }

  @Override
  public String getNewLayerName() {
    this.log.append("Got new layer name. Mock returned NewName.\n");
    return "NewName";
  }

  @Override
  public void addNewLayerToDropdown(String layerName) throws IllegalArgumentException {
    this.log.append("Added new layer to dropdown with name: ").append(layerName).append("\n");
  }

  @Override
  public String getImportFilePath() {
    this.log.append("Got imported file path. Returned res//test//quetzal//quetzaljpg.jpg.\n");
    return "res//test//quetzal//quetzaljpg.jpg";
  }

  @Override
  public String getExportFilePath() {
    this.log.append("Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n");
    return "res//test//quetzal//quetzalpng2.png";
  }

  @Override
  public String getScriptFilePath() {
    this.log.append("Got script file path. Returned res//Script.txt.\n");
    return "res//Script.txt";
  }

  @Override
  public String getFileTypeExtension() {
    this.log.append("Got file type extension. Returned png.\n");
    return "png";
  }

  @Override
  public String getSelectedCurrentLayer() {
    this.log.append("Got current layer name. Returned two\n");
    return "two";
  }

  @Override
  public String getSelectedRemoveLayer() {
    this.log.append("Got removed layer name. Returned RemovedLayer\n");
    return "RemovedLayer";
  }

  @Override
  public void changeCurrentLayerText(String layerName) throws IllegalArgumentException {
    this.log.append("Changed current layer text to: ").append(layerName).append("\n");
  }

  @Override
  public void changeCurrentVisibleLayerText(String layerName) throws IllegalArgumentException {
    this.log.append("Changed current visible layer text to: ").append(layerName).append("\n");
  }

  @Override
  public void removeLayerName(String layerName) throws IllegalArgumentException {
    this.log.append("Removed layer with this name: ").append(layerName).append("\n");
  }

  @Override
  public void renderErrorMessage(String str) throws IllegalArgumentException {
    this.log.append("Rendered error popup with this message: ").append(str).append("\n");
  }

  @Override
  public String getImportExtension() {
    this.log.append("Got import extension. Returned png.\n");
    return "png";
  }

  @Override
  public String getExportExtension() {
    this.log.append("Got export extension. Returned jpg.\n");
    return "jpg";
  }

  @Override
  public String getExportAllExtension() {
    this.log.append("Got exportall extension. Returned ppm.\n");
    return "ppm";
  }
}
