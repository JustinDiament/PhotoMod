import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.ImageController;
import controller.ImageControllerImpl;
import java.io.StringReader;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import org.junit.Before;
import org.junit.Test;

public class ImageControllerImplTest {

  private ImageLayerModel model;
  private ImageLayerModel mockModel;
  private ImageController controller;
  private StringBuilder ap;
  private StringBuilder mockAp;

  @Before
  public void init() {
    this.model = new ImageLayerModelImpl();
    this.mockAp = new StringBuilder();
    this.mockModel = new ImageLayerMockModel(mockAp);
    this.ap = new StringBuilder();
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorOneModelCannotBeNull() {
    new ImageControllerImpl(null, new StringReader(""), new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorTwoModelCannotBeNull() {
    new ImageControllerImpl(null,
        "res//test//layer//test18.jpg",
        new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorThreeModelCannotBeNull() {
    new ImageControllerImpl(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorOneReadableCannotBeNull() {
    new ImageControllerImpl(this.model, (Readable) null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorTwoFileNameCannotBeNull() {
    new ImageControllerImpl(this.model, (String) null, new StringBuilder());
  }

  @Test(expected = IllegalArgumentException.class)
  public void imageControllerImplConstructorTwoFileNameNotFound() {
    new ImageControllerImpl(this.model, "res//test//layer//test9.jpg", new StringBuilder());
  }

  @Test
  public void testRunCreateLayerCommand() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one"),
        this.ap);

    this.controller.run();

    assertEquals("one", this.model.getCurrentLayerName());
  }

  @Test
  public void testRunCreateLayerCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n", this.mockAp.toString());
  }

  @Test
  public void testRunCreateLayerCommandLayerAlreadyExists() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "createlayer one"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Layer with the given name already exists.\n",
        this.ap.toString());
  }

  @Test
  public void testRunCreateLayerCommandNoNameGiven() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer "),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Name not provided for new layer.\n",
        this.ap.toString());
  }

  @Test
  public void testRunChangeCurrentLayerCommand() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one"),
        this.ap);

    try {
      this.model.getCurrentLayerName();
    } catch (IllegalStateException e) {
      assertEquals("Current layer index is invalid", e.getMessage());
    }

    this.controller.run();

    assertEquals("one", this.model.getCurrentLayerName());
  }

  @Test
  public void testRunCurrentLayerCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
        + "Current layer changed to layer with this index: 0\n", this.mockAp.toString());
  }

  @Test
  public void testRunChangeCurrentLayerCommandBadName() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current eight"),
        this.ap);

    this.controller.run();

    assertEquals(
        "Command failed to execute. Reason: No layer with the specified name to change to "
            + "exists.\n",
        this.ap.toString());
  }

  @Test
  public void testRunChangeCurrentLayerCommandNoNameProvided() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: No layer name provided to change to.\n",
        this.ap.toString());
  }

  @Test
  public void testRunBlurCommand() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black "
            + "blur"),
        this.ap);

    this.controller.run();

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(80, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(64, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(64, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(80, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testRunBlurCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black "
            + "blur"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
        + "Current layer changed to layer with this index: 0\n"
        + "Created programmatic image of this type: class model.image.programmatic."
        + "CreateCheckerboard\n"
        + "Applied this type of operation to the current layer: BLUR\n", this.mockAp.toString());
  }

  @Test
  public void testRunBlurCommandNoImage() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "blur"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Input cannot be null\n", this.ap.toString());
  }

  @Test
  public void testRunSepiaCommandNoImage() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "sepia"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Input cannot be null\n", this.ap.toString());
  }

  @Test
  public void testRunMonochromeCommandNoImage() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "monochrome"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Input cannot be null\n", this.ap.toString());
  }

  @Test
  public void testRunSharpenCommandNoImage() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "sharpen"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Input cannot be null\n", this.ap.toString());
  }

  @Test
  public void testRunSepiaCommand() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black "
            + "sepia"),
        this.ap);

    this.controller.run();

    assertEquals(48, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(42, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(33, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(48, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(42, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(33, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testRunSepiaCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black "
            + "sepia"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
        + "Current layer changed to layer with this index: 0\n"
        + "Created programmatic image of this type: class model.image.programmatic."
        + "CreateCheckerboard\n"
        + "Applied this type of operation to the current layer: SEPIA\n", this.mockAp.toString());
  }

  @Test
  public void testRunMonochromeCommand() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black "
            + "monochrome"),
        this.ap);

    this.controller.run();

    assertEquals(18, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(18, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(18, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(18, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(18, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(18, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testRunMonochromeCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black "
            + "monochrome"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Created programmatic image of this type: class model.image.programmatic."
            + "CreateCheckerboard\n"
            + "Applied this type of operation to the current layer: MONOCHROME\n",
        this.mockAp.toString());
  }

  @Test
  public void testRunSharpenCommand() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black "
            + "sharpen"),
        this.ap);

    this.controller.run();

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(128, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(128, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }

  @Test
  public void testRunSharpenCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black "
            + "sharpen"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
        + "Current layer changed to layer with this index: 0\n"
        + "Created programmatic image of this type: class model.image.programmatic."
        + "CreateCheckerboard\n"
        + "Applied this type of operation to the current layer: SHARPEN\n", this.mockAp.toString());
  }

  @Test
  public void testRunRemoveLayerCommand() {
    this.model.addLayer("one");

    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "current one "
            + "removelayer one"),
        this.ap);

    assertEquals(1, this.model.getLayerNames().size());

    this.controller.run();

    assertEquals(0, this.model.getLayerNames().size());
  }

  @Test
  public void testRemoveLayerCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "removelayer one"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
        + "Current layer changed to layer with this index: 0\n"
        + "Removed layer at this index: 0\n"
        + "Current layer changed to layer with this index: -1\n", this.mockAp.toString());
  }

  @Test
  public void testRunRemoveLayerCommandNoLayerName() {
    this.model.addLayer("one");

    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "current one "
            + "removelayer "),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: No name of layer to remove provided\n",
        this.ap.toString());
  }

  @Test
  public void testRunRemoveLayerCommandInvalidLayer() {
    this.model.addLayer("one");

    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "current one "
            + "removelayer eight"),
        this.ap);

    this.controller.run();

    assertEquals(
        "Command failed to execute. Reason: No layer with the specified name to remove exists.\n",
        this.ap.toString());
  }

  @Test
  public void testRunVisibilityCommand() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "current one "
            + "visibility invisible"),
        this.ap);

    assertTrue(this.model.getCurrentLayerVisibility());

    this.controller.run();

    assertFalse(this.model.getCurrentLayerVisibility());
  }


  @Test
  public void testVisibilityCommandOnMock() {
    this.mockModel.addLayer("one");
    this.mockModel.setCurrentLayer(0);

    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "current one "
            + "visibility invisible"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
        + "Current layer changed to layer with this index: 0\n"
        + "Current layer changed to layer with this index: 0\n"
        + "Current layer changed to this visibility level: false\n", this.mockAp.toString());
  }

  @Test
  public void testRunVisibilityCommandInvalidLevelOfVisibility() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "current one "
            + "visibility clear"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Specified visibility level is invalid.\n",
        this.ap.toString());
  }

  @Test
  public void testRunVisibilityCommandNoVisibilityLevelProvided() {
    this.model.addLayer("one");
    this.model.setCurrentLayer(0);

    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "current one "
            + "visibility "),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: No visibility level provided.\n",
        this.ap.toString());
  }

  @Test
  public void testRunVisibilityCurrentLayerInvalid() {
    this.model.addLayer("one");

    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "visibility visible"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Current layer index is invalid\n",
        this.ap.toString());
  }

  @Test
  public void testRunCreateCheckerboard() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black"),
        this.ap);

    this.controller.run();

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(0, 1).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getGreen());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 0).getBlue());

    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getRed());
    assertEquals(0, this.model.getPixelInCurrentLayerAt(1, 1).getGreen());
    assertEquals(255, this.model.getPixelInCurrentLayerAt(1, 1).getBlue());
  }


  @Test
  public void testCreateCheckerboardCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue black"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Created programmatic image of this type: class model.image.programmatic."
            + "CreateCheckerboard\n",
        this.mockAp.toString());
  }

  @Test
  public void testRunCreateCheckerboardBadSize() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard l 4 blue black"),
        this.ap);

    this.controller.run();

    assertEquals(
        "Command failed to execute. Reason: Size of checkerboard provided is not an integer.\n"
            + "Provided command is invalid or not supported.\n"
            + "Provided command is invalid or not supported.\n"
            + "Provided command is invalid or not supported.\n", this.ap.toString());
  }

  @Test
  public void testRunCreateCheckerboardBadNumSquares() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 l blue black"),
        this.ap);

    this.controller.run();

    assertEquals(
        "Command failed to execute. Reason: Size of checkerboard provided is not an integer.\n"
            + "Provided command is invalid or not supported.\n"
            + "Provided command is invalid or not supported.\n", this.ap.toString());
  }

  @Test
  public void testRunCreateCheckerboardBadColorOne() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 bluuuue black"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: First requested color not supported.\n"
        + "Provided command is invalid or not supported.\n", this.ap.toString());
  }

  @Test
  public void testRunCreateCheckerboardBadColorTwo() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "createcheckerboard 2 4 blue blaaaaack"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Second requested color not supported.\n",
        this.ap.toString());
  }

  @Test
  public void testRunImport() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "import res//test//layer//test.jpg jpg"),
        this.ap);

    this.controller.run();

    assertEquals(10, this.model.getPixelInCurrentLayerAt(0, 0).getRed());
    assertEquals(10, this.model.getPixelInCurrentLayerAt(0, 0).getGreen());
    assertEquals(10, this.model.getPixelInCurrentLayerAt(0, 0).getBlue());
  }

  @Test
  public void testImportCommandOnMock() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "import res//test//layer//test.jpg jpg"),
        this.ap);

    this.controller.run();

    assertEquals("New layer created with this name: one\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Current layer image set\n",
        this.mockAp.toString());
  }

  @Test
  public void testRunImportNoImageTypeProvided() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "import res//test//layer//test.jpg "),
        this.ap);

    this.controller.run();

    assertEquals(
        "Command failed to execute. Reason: Not all specifications to import file provided.\n",
        this.ap.toString());
  }

  @Test
  public void testRunImportBadFile() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "import res//test//layer//test111.jpg jpg"),
        this.ap);

    this.controller.run();

    assertEquals("Command failed to execute. Reason: Failed to import file.\n",
        this.ap.toString());
  }

  @Test
  public void testQuit() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "quit"),
        this.ap);

    this.controller.run();

    assertEquals("Image processing has been quit.\n", this.ap.toString());
  }

  @Test
  public void testNonSupportedCommand() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "create one "),
        this.ap);

    this.controller.run();

    assertEquals("Provided command is invalid or not supported.\n"
        + "Provided command is invalid or not supported.\n", this.ap.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void testBadAppendable() {
    this.controller = new ImageControllerImpl(this.model,
        new StringReader(""
            + "create one "),
        new BadAppendable());

    this.controller.run();
  }

  @Test
  public void testRunExportOnMockToMakeSureItProperlyCommunicates() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "current one "
            + "import res//test//layer//test.jpg jpg "
            + "blur "
            + "export res//test//test3.jpg jpg"),
        this.ap);

    this.controller.run();

    assertEquals(
        "New layer created with this name: one\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Current layer image set\n"
            + "Applied this type of operation to the current layer: BLUR\n"
            + "Export occurring\n",
        this.mockAp.toString());
  }

  @Test
  public void testRunExportAllOnMockToMakeSureItProperlyCommunicates() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "createlayer two "
            + "current one "
            + "import res//test//layer//test.jpg jpg "
            + "sharpen "
            + "current two "
            + "import res//test//layer//test.jpg jpg "
            + "blur "
            + "exportAll res//test//layer2//test4 jpg"),
        this.ap);

    this.controller.run();

    assertEquals(
        "New layer created with this name: one\n"
            + "New layer created with this name: two\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Current layer image set\n"
            + "Applied this type of operation to the current layer: SHARPEN\n"
            + "Current layer changed to layer with this index: 1\n"
            + "Current layer image set\n"
            + "Applied this type of operation to the current layer: BLUR\n"
            + "Multi-layer export occurring\n",
        this.mockAp.toString());
  }

  @Test
  public void testBadInputThenKeepsGoing() {
    this.controller = new ImageControllerImpl(this.mockModel,
        new StringReader(""
            + "createlayer one "
            + "createlayer two "
            + "current one "
            + "current bad "
            + "import res//test//layer//test.jpg jpg "
            + "sharpen "
            + "bad "
            + "current two "
            + "import res//test//layer//test.jpg jpg "
            + "blur "),
        this.ap);

    this.controller.run();

    assertEquals(
        "New layer created with this name: one\n"
            + "New layer created with this name: two\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Current layer image set\n"
            + "Applied this type of operation to the current layer: SHARPEN\n"
            + "Current layer changed to layer with this index: 1\n"
            + "Current layer image set\n"
            + "Applied this type of operation to the current layer: BLUR\n",
        this.mockAp.toString());

    assertEquals(
        "Command failed to execute. Reason: No layer with the specified name to change "
            + "to exists.\n"
            + "Provided command is invalid or not supported.\n",
        this.ap.toString());
  }

  @Test
  public void testScriptOne() {
    this.controller = new ImageControllerImpl(this.mockModel,
        "res//ScriptOne.txt",
        this.ap);

    this.controller.run();

    assertEquals(
        "New layer created with this name: one\n"
            + "New layer created with this name: two\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Current layer image set\n"
            + "Applied this type of operation to the current layer: SHARPEN\n"
            + "Current layer changed to layer with this index: 1\n"
            + "Current layer image set\n"
            + "Applied this type of operation to the current layer: BLUR\n"
            + "Applied this type of operation to the current layer: SEPIA\n"
            + "New layer created with this name: three\n"
            + "New layer created with this name: four\n"
            + "Current layer changed to layer with this index: 2\n"
            + "Current layer image set\n"
            + "Current layer changed to layer with this index: 3\n"
            + "Created programmatic image of this type: class model.image.programmatic."
            + "CreateCheckerboard\n"
            + "Removed layer at this index: 3\n"
            + "Current layer changed to layer with this index: -1\n"
            + "Current layer changed to layer with this index: 2\n"
            + "Current layer changed to this visibility level: false\n"
            + "Export occurring\n"
            + "Current layer changed to this visibility level: true\n"
            + "Export occurring\n"
            + "Export occurring\n"
            + "Applied this type of operation to the current layer: SHARPEN\n"
            + "Applied this type of operation to the current layer: MONOCHROME\n"
            + "Multi-layer export occurring\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Applied this type of operation to the current layer: SHARPEN\n"
            + "Multi-layer export occurring\n"
            + "Applied this type of operation to the current layer: SEPIA\n"
            + "Multi-layer export occurring\n",
        this.mockAp.toString());

    assertEquals(
        "Image processing has been quit.\n",
        this.ap.toString());
  }


  @Test
  public void testScriptTwo() {
    this.controller = new ImageControllerImpl(this.mockModel,
        "res//ScriptTwo.txt",
        this.ap);

    this.controller.run();

    assertEquals(
        "New layer created with this name: one\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Current layer image set\n"
            + "New layer created with this name: two\n"
            + "New layer created with this name: three\n"
            + "Current layer changed to layer with this index: 2\n"
            + "Current layer image set\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Applied this type of operation to the current layer: SHARPEN\n"
            + "Applied this type of operation to the current layer: SEPIA\n"
            + "Current layer changed to this visibility level: false\n"
            + "Current layer changed to layer with this index: 1\n"
            + "Current layer image set\n"
            + "Current layer changed to this visibility level: false\n"
            + "Applied this type of operation to the current layer: MONOCHROME\n"
            + "Export occurring\n"
            + "Current layer changed to this visibility level: true\n"
            + "Current layer changed to layer with this index: 2\n"
            + "Current layer changed to this visibility level: true\n"
            + "Export occurring\n"
            + "Removed layer at this index: 2\n"
            + "Current layer changed to layer with this index: -1\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Export occurring\n"
            + "Applied this type of operation to the current layer: BLUR\n"
            + "Export occurring\n"
            + "Current layer changed to layer with this index: 1\n"
            + "Created programmatic image of this type: class model.image.programmatic."
            + "CreateCheckerboard\n"
            + "Export occurring\n"
            + "Multi-layer export occurring\n"
            + "Applied this type of operation to the current layer: SHARPEN\n"
            + "Applied this type of operation to the current layer: SEPIA\n"
            + "Current layer changed to layer with this index: 0\n"
            + "Created programmatic image of this type: class model.image.programmatic."
            + "CreateCheckerboard\n"
            + "Multi-layer export occurring\n"
            + "Applied this type of operation to the current layer: MONOCHROME\n"
            + "Applied this type of operation to the current layer: BLUR\n"
            + "Multi-layer export occurring\n",
        this.mockAp.toString());

    assertEquals(
        "",
        this.ap.toString());
  }
}
