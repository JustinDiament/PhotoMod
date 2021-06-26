import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.ImageInteractiveController;
import controller.ImageInteractiveControllerImpl;
import java.awt.Color;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.programmatic.CreateCheckerboard;
import org.junit.Before;
import org.junit.Test;
import view.Features;
import view.FeaturesImpl;
import view.ImageView;

/**
 * Test class for the FeaturesImpl class.
 */
public class FeaturesImplTest {

  private Features features;
  private ImageLayerModel model;
  private StringBuilder log;

  @Before
  public void init() {
    this.log = new StringBuilder();
    ImageView mockView = new ImageMockView(this.log);
    this.model = new ImageLayerModelImpl();
    ImageInteractiveController controller = new ImageInteractiveControllerImpl(this.model,
        mockView);
    this.features = new FeaturesImpl(controller);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testControllerCannotBeNull() {
    new FeaturesImpl(null);
  }

  @Test
  public void testSepiaEventResultsInSepia() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    this.features.handleSepiaEvent();

    assertEquals(254, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(226, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(176, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(244, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(217, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(169, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(244, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(217, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(169, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(254, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(226, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(176, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testSepiaEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.features.handleSepiaEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testSepiaFailsViewCorrectlyIndicatesError() {
    this.features.handleSepiaEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testMonochromeEventResultsInMonochrome() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    this.features.handleMonochromeEvent();

    assertEquals(197, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(197, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(197, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(197, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(197, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(197, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testMonochromeEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.features.handleMonochromeEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testMonochromeFailsViewCorrectlyIndicatesError() {
    this.features.handleMonochromeEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testSharpenEventResultsInSharpen() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    this.features.handleSharpenEvent();

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(128, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(128, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(128, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(128, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testSharpenEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.features.handleSharpenEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testSharpenFailsViewCorrectlyIndicatesError() {
    this.features.handleSharpenEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testBlurEventResultsInBlur() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    this.features.handleBlurEvent();

    assertEquals(80, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(126, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(64, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(64, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(130, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(80, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(64, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(130, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(80, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(80, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(126, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(64, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testBlurEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.features.handleBlurEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testBlurFailsViewCorrectlyIndicatesError() {
    this.features.handleBlurEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testDownscaleEventResultsInDownscale() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    assertEquals(2, this.model.getCurrentLayerImage().getWidth());
    assertEquals(2, this.model.getCurrentLayerImage().getHeight());

    this.features.handleDownscaleEvent();

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(1, this.model.getCurrentLayerImage().getWidth());
    assertEquals(1, this.model.getCurrentLayerImage().getHeight());
  }

  @Test
  public void testDownscaleEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.features.handleDownscaleEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got XScale. Mock returned 55.\n"
        + "Got YScale. Mock returned 65.\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testDownscaleFailsViewCorrectlyIndicatesError() {
    this.features.handleDownscaleEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got XScale. Mock returned 55.\n"
            + "Got YScale. Mock returned 65.\n"
            + "Rendered a new image.\n",
        this.log.toString());
  }

  @Test
  public void testMosaicEventResultsInMosaic() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());

    this.features.handleMosaicEvent();

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testMosaicEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.features.handleMosaicEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got seeds. Mock returned 100.\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testMosaicFailsViewCorrectlyIndicatesError() {
    this.features.handleMosaicEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got seeds. Mock returned 100.\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testCurrentLayerEventChangesCurrentLayer() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(0);

    assertEquals("one", this.model.getCurrentLayerName());

    this.features.handleCurrentLayerEvent();

    assertEquals("two", this.model.getCurrentLayerName());
  }

  @Test
  public void testCurrentLayerEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(0);

    this.features.handleCurrentLayerEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got current layer name. Returned two\n"
        + "Changed current layer text to: two\n"
        + "Changed current visible layer text to: two\n", this.log.toString());
  }

  @Test
  public void testCurrentLayerFailsViewCorrectlyIndicatesError() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.features.handleCurrentLayerEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got current layer name. Returned two\n"
            + "Rendered error popup with this message: "
            + "No layer with the specified name to change to exists.\n",
        this.log.toString());
  }

  @Test
  public void testAddLayerEventAddsLayer() {
    assertEquals(0, this.model.getLayerNames().size());

    this.features.handleAddLayerEvent();

    assertEquals(1, this.model.getLayerNames().size());
  }

  @Test
  public void testAddLayerEventViewProperlyInteractedWith() {
    this.features.handleAddLayerEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got new layer name. Mock returned NewName.\n"
        + "Added new layer to dropdown with name: NewName\n"
        + "Changed current visible layer text to: NewName\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testAddLayerFailsViewCorrectlyIndicatesError() {
    this.model.addLayer("NewName");

    this.features.handleAddLayerEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got new layer name. Mock returned NewName.\n"
            + "Rendered error popup with this message: Layer with the given name already exists.\n",
        this.log.toString());
  }

  @Test
  public void testRemoveLayerRemovesLayer() {
    this.model.addLayer("RemovedLayer");

    assertEquals(1, this.model.getLayerNames().size());
    assertTrue(this.model.getLayerNames().contains("RemovedLayer"));

    this.features.handleRemoveLayerEvent();

    assertEquals(0, this.model.getLayerNames().size());
    assertFalse(this.model.getLayerNames().contains("RemovedLayer"));
  }

  @Test
  public void testRemoveLayerEventViewProperlyInteractedWith() {
    this.model.addLayer("RemovedLayer");

    this.features.handleRemoveLayerEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got removed layer name. Returned RemovedLayer\n"
        + "Changed current layer text to: None\n"
        + "Changed current visible layer text to: None\n"
        + "Removed layer with this name: RemovedLayer\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testRemoveLayerFailsViewCorrectlyIndicatesError() {
    this.model.addLayer("one");

    this.features.handleRemoveLayerEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got removed layer name. Returned RemovedLayer\n"
            + "Rendered error popup with this message: "
            + "No layer with the specified name to remove exists.\n",
        this.log.toString());
  }

  @Test
  public void testImportEventImportsImage() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.BLACK));

    assertEquals(16, this.model.getCurrentLayerImage().getWidth());
    assertEquals(16, this.model.getCurrentLayerImage().getHeight());

    this.features.handleImportEvent();

    assertEquals(77, this.model.getCurrentLayerImage().getWidth());
    assertEquals(102, this.model.getCurrentLayerImage().getHeight());
  }

  @Test
  public void testImportEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.features.handleImportEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got imported file path. Returned res//test//quetzal//quetzaljpg.jpg.\n"
        + "Got import extension. Returned png.\n"
        + "Rendered a new image.\n"
        + "Changed current visible layer text to: one\n", this.log.toString());
  }

  @Test
  public void testImportFailsViewCorrectlyIndicatesError() {

    this.features.handleImportEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got imported file path. Returned res//test//quetzal//quetzaljpg.jpg.\n"
            + "Got import extension. Returned png.\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testExportLayerEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.BLACK));

    this.features.handleExportLayerEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n"
        + "Got export extension. Returned jpg.\n", this.log.toString());
  }

  @Test
  public void testExportLayerFailsViewCorrectlyIndicatesError() {

    this.features.handleExportLayerEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n"
            + "Got export extension. Returned jpg.\n"
            + "Rendered error popup with this message: No layers are visible\n",
        this.log.toString());
  }

  @Test
  public void testExportAllEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.BLACK));
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(4, 4, Color.BLUE, Color.RED));

    this.features.handleExportAllEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n"
        + "Got exportall extension. Returned ppm.\n", this.log.toString());
  }

  @Test
  public void testExportAllFailsViewCorrectlyIndicatesError() {

    this.features.handleExportAllEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n"
            + "Got exportall extension. Returned ppm.\n",
        this.log.toString());
  }

  @Test
  public void testCreateCheckerboardEventCreatesBoard() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(4, 4, Color.GREEN, Color.RED));

    assertEquals(4, this.model.getCurrentLayerImage().getWidth());
    assertEquals(4, this.model.getCurrentLayerImage().getHeight());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 3).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 3).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 3).getBlue());

    this.features.handleCreateCheckerboardEvent();

    assertEquals(16, this.model.getCurrentLayerImage().getWidth());
    assertEquals(16, this.model.getCurrentLayerImage().getHeight());

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 15).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 15).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 15).getBlue());
  }

  @Test
  public void testCreateCheckerboardEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.features.handleCreateCheckerboardEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got chosen color one for checkerboard. Mock returned white\n"
        + "Got chosen color two for checkerboard. Mock returned black\n"
        + "Got chosen checkerboard size. Mock returned 16.\n"
        + "Got chosen number of checkerboard squares. Mock returned 4.\n"
        + "Rendered a new image.\n"
        + "Changed current visible layer text to: one\n", this.log.toString());
  }

  @Test
  public void testCreateCheckerboardEventFailsViewCorrectlyIndicatesError() {

    this.features.handleCreateCheckerboardEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got chosen color one for checkerboard. Mock returned white\n"
            + "Got chosen color two for checkerboard. Mock returned black\n"
            + "Got chosen checkerboard size. Mock returned 16.\n"
            + "Got chosen number of checkerboard squares. Mock returned 4.\n"
            + "Rendered error popup with this message: Layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testVisibilityEventSwapsVisibility() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    assertTrue(this.model.getCurrentLayerVisibility());

    this.features.handleVisibilityEvent();

    assertFalse(this.model.getCurrentLayerVisibility());

    this.features.handleVisibilityEvent();

    assertTrue(this.model.getCurrentLayerVisibility());
  }

  @Test
  public void testVisibilityEventViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.features.handleVisibilityEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n"
        + "Changed current visible layer text to: None\n", this.log.toString());
  }

  @Test
  public void testVisibilityEventFailsViewCorrectlyIndicatesError() {

    this.features.handleVisibilityEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testScriptEventRunsScript() {
    assertEquals(0, this.model.getLayerNames().size());

    this.features.handleExecuteScriptEvent();

    assertEquals(8, this.model.getLayerNames().size());

    assertEquals("one", this.model.getLayerNames().get(0));
    assertEquals("two", this.model.getLayerNames().get(1));
    assertEquals("quetzaljpgsq", this.model.getLayerNames().get(2));
    assertEquals("quetzalpngsq", this.model.getLayerNames().get(3));
    assertEquals("quetzalppmsq", this.model.getLayerNames().get(4));
    assertEquals("three", this.model.getLayerNames().get(5));
    assertEquals("five", this.model.getLayerNames().get(6));
    assertEquals("mos", this.model.getLayerNames().get(7));
  }

  @Test
  public void testScriptEventViewProperlyInteractedWith() {
    this.features.handleExecuteScriptEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got script file path. Returned res//Script.txt.\n"
        + "Rendered error popup with this message: Image processing has been quit.\n"
        + "\n"
        + "Rendered a new image.\n"
        + "Added new layer to dropdown with name: one\n"
        + "Added new layer to dropdown with name: two\n"
        + "Added new layer to dropdown with name: quetzaljpgsq\n"
        + "Added new layer to dropdown with name: quetzalpngsq\n"
        + "Added new layer to dropdown with name: quetzalppmsq\n"
        + "Added new layer to dropdown with name: three\n"
        + "Added new layer to dropdown with name: five\n"
        + "Added new layer to dropdown with name: mos\n"
        + "Changed current visible layer text to: mos\n"
        + "Changed current layer text to: mos\n", this.log.toString());
  }

  @Test
  public void testScriptEventFailsViewCorrectlyIndicatesError() {
    this.model.addLayer("one");

    this.features.handleExecuteScriptEvent();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got script file path. Returned res//Script.txt.\n"
            + "Rendered error popup with this message: Command failed to execute. "
            + "Reason: Layer with the given name already exists.\n"
            + "Image processing has been quit.\n"
            + "\n"
            + "Rendered a new image.\n"
            + "Added new layer to dropdown with name: one\n"
            + "Added new layer to dropdown with name: two\n"
            + "Added new layer to dropdown with name: quetzaljpgsq\n"
            + "Added new layer to dropdown with name: quetzalpngsq\n"
            + "Added new layer to dropdown with name: quetzalppmsq\n"
            + "Added new layer to dropdown with name: three\n"
            + "Added new layer to dropdown with name: five\n"
            + "Added new layer to dropdown with name: mos\n"
            + "Changed current visible layer text to: mos\n"
            + "Changed current layer text to: mos\n",
        this.log.toString());
  }
}
