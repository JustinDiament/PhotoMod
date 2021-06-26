import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.ImageInteractiveController;
import controller.ImageInteractiveControllerImpl;
import controller.commands.BlurCommand;
import controller.commands.MonochromeCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import java.awt.Color;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.programmatic.CreateCheckerboard;
import org.junit.Before;
import org.junit.Test;
import view.ImageView;

public class ImageInteractiveControllerImplTest {

  private ImageInteractiveController controller;
  private ImageView mockView;
  private ImageLayerModel model;
  private StringBuilder log;

  @Before
  public void init() {
    this.log = new StringBuilder();
    this.mockView = new ImageMockView(this.log);
    this.model = new ImageLayerModelImpl();
    this.controller = new ImageInteractiveControllerImpl(this.model, this.mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testModelCannotBeNull() {
    new ImageInteractiveControllerImpl(null, this.mockView);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testViewCannotBeNull() {
    new ImageInteractiveControllerImpl(this.model, null);
  }

  @Test
  public void testRun() {
    this.controller.run();

    assertEquals("Added a new listener: class view.FeaturesImpl\n",
        this.log.toString());
  }

  @Test
  public void testOperationCommandExecuteSepiaResultsInSepia() {
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

    this.controller.operationCommandExecute(new SepiaCommand());

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
  public void testOperationCommandExecuteSepiaViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.controller.operationCommandExecute(new SepiaCommand());

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testOperationExecuteSepiaFailsViewCorrectlyIndicatesError() {
    this.controller.operationCommandExecute(new SepiaCommand());

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testOperationCommandExecuteMonochromeResultsInMonochrome() {
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

    this.controller.operationCommandExecute(new MonochromeCommand());

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
  public void testOperationCommandExecuteMonochromeViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.controller.operationCommandExecute(new MonochromeCommand());

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testOperationCommandExecuteMonochromeFailsViewCorrectlyIndicatesError() {
    this.controller.operationCommandExecute(new MonochromeCommand());

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testSharpenCommandExecuteResultsInSharpen() {
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

    this.controller.operationCommandExecute(new SharpenCommand());

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
  public void testSharpenCommandExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.controller.operationCommandExecute(new SharpenCommand());

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testSharpenCommandExecuteFailsViewCorrectlyIndicatesError() {
    this.controller.operationCommandExecute(new SharpenCommand());

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testBlurCommandExecuteResultsInBlur() {
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

    this.controller.operationCommandExecute(new BlurCommand());

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
  public void testBlurCommandExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.controller.operationCommandExecute(new BlurCommand());

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testBlurCommandExecuteFailsViewCorrectlyIndicatesError() {
    this.controller.operationCommandExecute(new BlurCommand());

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testDownscaleExecuteEventResultsInDownscale() {
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

    this.controller.downscaleExecute();

    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(200, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(1, this.model.getCurrentLayerImage().getWidth());
    assertEquals(1, this.model.getCurrentLayerImage().getHeight());
  }

  @Test
  public void testDownscaleExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.controller.downscaleExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got XScale. Mock returned 55.\n"
        + "Got YScale. Mock returned 65.\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testDownscaleExecuteFailsViewCorrectlyIndicatesError() {
    this.controller.downscaleExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got XScale. Mock returned 55.\n"
            + "Got YScale. Mock returned 65.\n"
            + "Rendered a new image.\n",
        this.log.toString());
  }

  @Test
  public void testMosaicExecuteResultsInMosaic() {
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

    this.controller.mosaicExecute();

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
  public void testMosaicExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(2, 4, Color.ORANGE, Color.CYAN));

    this.controller.mosaicExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got seeds. Mock returned 100.\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testMosaicExecuteFailsViewCorrectlyIndicatesError() {
    this.controller.mosaicExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got seeds. Mock returned 100.\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testCurrentLayerExecuteChangesCurrentLayer() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(0);

    assertEquals("one", this.model.getCurrentLayerName());

    this.controller.currentLayerExecute();

    assertEquals("two", this.model.getCurrentLayerName());
  }

  @Test
  public void testCurrentLayerExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(0);

    this.controller.currentLayerExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got current layer name. Returned two\n"
        + "Changed current layer text to: two\n"
        + "Changed current visible layer text to: two\n", this.log.toString());
  }

  @Test
  public void testCurrentLayerExecuteViewCorrectlyIndicatesError() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.controller.currentLayerExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got current layer name. Returned two\n"
            + "Rendered error popup with this message: No layer with the specified name to change to exists.\n",
        this.log.toString());
  }

  @Test
  public void testAddLayerExecuteAddsLayer() {
    assertEquals(0, this.model.getLayerNames().size());

    this.controller.addLayerExecute();

    assertEquals(1, this.model.getLayerNames().size());
  }

  @Test
  public void testAddLayerExecuteViewProperlyInteractedWith() {
    this.controller.addLayerExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got new layer name. Mock returned NewName.\n"
        + "Added new layer to dropdown with name: NewName\n"
        + "Changed current visible layer text to: NewName\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testAddLayerExecuteFailsViewCorrectlyIndicatesError() {
    this.model.addLayer("NewName");

    this.controller.addLayerExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got new layer name. Mock returned NewName.\n"
            + "Rendered error popup with this message: Layer with the given name already exists.\n",
        this.log.toString());
  }

  @Test
  public void testRemoveLayerExecuteRemovesLayer() {
    this.model.addLayer("RemovedLayer");

    assertEquals(1, this.model.getLayerNames().size());
    assertTrue(this.model.getLayerNames().contains("RemovedLayer"));

    this.controller.removeLayerExecute();

    assertEquals(0, this.model.getLayerNames().size());
    assertFalse(this.model.getLayerNames().contains("RemovedLayer"));
  }

  @Test
  public void testRemoveLayerExecuteViewProperlyInteractedWith() {
    this.model.addLayer("RemovedLayer");

    this.controller.removeLayerExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got removed layer name. Returned RemovedLayer\n"
        + "Changed current layer text to: None\n"
        + "Changed current visible layer text to: None\n"
        + "Removed layer with this name: RemovedLayer\n"
        + "Rendered a new image.\n", this.log.toString());
  }

  @Test
  public void testRemoveLayerExecuteFailsViewCorrectlyIndicatesError() {
    this.model.addLayer("one");

    this.controller.removeLayerExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got removed layer name. Returned RemovedLayer\n"
            + "Rendered error popup with this message: No layer with the specified name to remove exists.\n",
        this.log.toString());
  }

  @Test
  public void testImportExecuteImportsImage() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.BLACK));

    assertEquals(16, this.model.getCurrentLayerImage().getWidth());
    assertEquals(16, this.model.getCurrentLayerImage().getHeight());

    this.controller.importExecute();

    assertEquals(77, this.model.getCurrentLayerImage().getWidth());
    assertEquals(102, this.model.getCurrentLayerImage().getHeight());
  }

  @Test
  public void testImportExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.controller.importExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got imported file path. Returned res//test//quetzal//quetzaljpg.jpg.\n"
        + "Got import extension. Returned png.\n"
        + "Rendered a new image.\n"
        + "Changed current visible layer text to: one\n", this.log.toString());
  }

  @Test
  public void testImportExecuteFailsViewCorrectlyIndicatesError() {

    this.controller.importExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got imported file path. Returned res//test//quetzal//quetzaljpg.jpg.\n"
            + "Got import extension. Returned png.\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testExportLayerExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.BLACK));

    this.controller.exportLayerExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n"
        + "Got export extension. Returned jpg.\n", this.log.toString());
  }

  @Test
  public void testExportLayerExecuteFailsViewCorrectlyIndicatesError() {

    this.controller.exportLayerExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n"
            + "Got export extension. Returned jpg.\n"
            + "Rendered error popup with this message: No layers are visible\n",
        this.log.toString());
  }

  @Test
  public void testExportAllExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.BLACK));
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(4, 4, Color.BLUE, Color.RED));

    this.controller.exportAllExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n"
        + "Got exportall extension. Returned ppm.\n", this.log.toString());
  }

  @Test
  public void testExportAllExecuteFailsViewCorrectlyIndicatesError() {

    this.controller.exportAllExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got exported file path. Returned res//test//quetzal//quetzalpng2.png.\n"
            + "Got exportall extension. Returned ppm.\n",
        this.log.toString());
  }

  @Test
  public void testCreateCheckerboarExecuteCreatesBoard() {
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

    this.controller.createCheckerboardExecute();

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
  public void testCreateCheckerboardExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.controller.createCheckerboardExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Got chosen color one for checkerboard. Mock returned white\n"
        + "Got chosen color two for checkerboard. Mock returned black\n"
        + "Got chosen checkerboard size. Mock returned 16.\n"
        + "Got chosen number of checkerboard squares. Mock returned 4.\n"
        + "Rendered a new image.\n"
        + "Changed current visible layer text to: one\n", this.log.toString());
  }

  @Test
  public void testCreateCheckerboardExecuteEventFailsViewCorrectlyIndicatesError() {

    this.controller.createCheckerboardExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Got chosen color one for checkerboard. Mock returned white\n"
            + "Got chosen color two for checkerboard. Mock returned black\n"
            + "Got chosen checkerboard size. Mock returned 16.\n"
            + "Got chosen number of checkerboard squares. Mock returned 4.\n"
            + "Rendered error popup with this message: Layer index is invalid\n",
        this.log.toString());
  }

  @Test
  public void testVisibilityExecuteSwapsVisibility() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    assertTrue(this.model.getCurrentLayerVisibility());

    this.controller.visibilityExecute();

    assertFalse(this.model.getCurrentLayerVisibility());

    this.controller.visibilityExecute();

    assertTrue(this.model.getCurrentLayerVisibility());
  }

  @Test
  public void testVisibilityExecuteViewProperlyInteractedWith() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.controller.visibilityExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
        + "Rendered a new image.\n"
        + "Changed current visible layer text to: None\n", this.log.toString());
  }

  @Test
  public void testVisibilityExecuteFailsViewCorrectlyIndicatesError() {

    this.controller.visibilityExecute();

    assertEquals("Added a new listener: class view.FeaturesImpl\n"
            + "Rendered error popup with this message: Current layer index is invalid\n",
        this.log.toString());
  }
}
