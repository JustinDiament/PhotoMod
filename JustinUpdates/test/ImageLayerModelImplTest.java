import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the ImageLayerModelImpl class.
 */
public class ImageLayerModelImplTest {

  private ImageLayerModel m;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.m = new ImageLayerModelImpl();
  }

  @Test
  public void testConstructorEmptyLayerList() {
    assertEquals(0, this.m.getLayerNames().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorCurrentLayerNegativeOne() {
    this.m.getCurrentLayer();
  }

  // todo: addLayer
  // todo: setCurrentLayerImage
  // todo: setCurrentLayer

  @Test(expected = IllegalArgumentException.class)
  public void testGetCurrentLayerInvalidIndex() {
    this.m.getCurrentLayer();
  }

  @Test
  public void testGetCurrentLayer() {
    this.m.addLayer("test");
    this.m.setCurrentLayer(0);
    assertEquals("test", this.m.getCurrentLayer().getName());
    assertNull(this.m.getCurrentLayer().getImage());
    assertTrue(this.m.getCurrentLayer().getVisibility());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetCurrentLayerVisibilityInvalidIndex() {
    this.m.setCurrentLayerVisibility(true);
  }

  @Test
  public void testSetCurrentLayerVisibility() {
    this.m.addLayer("");
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    this.m.setCurrentLayerVisibility(false);
    assertFalse(this.m.getCurrentLayer().getVisibility());
    this.m.setCurrentLayer(1);
    assertTrue(this.m.getCurrentLayer().getVisibility());
  }

  @Test
  public void testGetLayerNamesEmpty() {
    String[] names = {};
    assertArrayEquals(names, this.m.getLayerNames().toArray(new String[0]));
    assertEquals(0, this.m.getLayerNames().size());
  }

  @Test
  public void testGetLayerNames() {
    String[] names = {"1", "2"};
    this.m.addLayer("1");
    this.m.addLayer("2");
    assertArrayEquals(names, this.m.getLayerNames().toArray(new String[0]));
    assertEquals(2, this.m.getLayerNames().size());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveLayerInvalidIndex() {
    this.m.removeLayer(0);
  }

  @Test
  public void testRemoveLayer() {
    assertEquals(0, this.m.getLayerNames().size());
    this.m.addLayer("");
    this.m.addLayer("");
    assertEquals(2, this.m.getLayerNames().size());
    this.m.removeLayer(1);
    assertEquals(1, this.m.getLayerNames().size());
    this.m.removeLayer(0);
    assertEquals(0, this.m.getLayerNames().size());
  }

  // todo: applyOperation
  // todo: createProgrammaticImage


  @Test(expected = IllegalArgumentException.class)
  public void testExportTopImageNullFile() {
    this.m.exportTopImage(null, "jpg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportTopImageInvalidFile() {
    this.m.exportTopImage("", "jpg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportTopImageNoVisibleLayers() {
    this.m.exportTopImage("res\\test\\layer\\test.jpg", "jpg");
  }

  @Test
  public void testExportTopImage() {
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    this.m.importImage("res\\test\\layer\\test.jpg", "jpg");
    assertTrue(this.m.getCurrentLayer().getVisibility());
    this.m.exportTopImage("res\\test\\layer\\test.jpg", "jpg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportNullFile() {
    this.m.exportImage(null, "jpg", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExportInvalidFile() {
    this.m.exportImage(".", "jpg", null);
  }

  @Test
  public void testExport() {
    this.m.addLayer("");
    this.m.setCurrentLayer(0);
    this.m.importImage("res\\test\\layer\\test.jpg", "jpg");
    assertTrue(this.m.getCurrentLayer().getVisibility());
    this.m.addLayer("2");
    this.m.setCurrentLayer(1);
    this.m.importImage("res\\test\\layer\\test2.jpg", "jpg");
    assertTrue(this.m.getCurrentLayer().getVisibility());
    this.m.exportImage("res\\test\\layer\\test.jpg", "jpg", null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImportNullFile() {
    this.m.importImage(null, "jpg");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImportInvalidFile() {
    this.m.importImage(".", "jpg");
  }

  @Test
  public void importSingleImage() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.importImage("res\\test\\layer\\test.jpg", "jpg");
    assertTrue(this.m.getCurrentLayer().getVisibility());
    assertEquals(1, this.m.getCurrentLayer().getImage().getHeight());
    assertEquals(1, this.m.getCurrentLayer().getImage().getWidth());
    assertEquals("first", this.m.getCurrentLayer().getName());
  }

  @Test
  public void testImportLayeredImage() {
    this.m.importImage("res\\test\\layer\\test.txt", "txt");
    assertEquals(2, this.m.getLayerNames().size());
    this.m.setCurrentLayer(0);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    assertEquals(1, this.m.getCurrentLayer().getImage().getHeight());
    assertEquals(1, this.m.getCurrentLayer().getImage().getWidth());
    assertEquals("res\\test\\layer\\test", this.m.getCurrentLayer().getName());
    this.m.setCurrentLayer(1);
    assertTrue(this.m.getCurrentLayer().getVisibility());
    assertEquals(1, this.m.getCurrentLayer().getImage().getHeight());
    assertEquals(1, this.m.getCurrentLayer().getImage().getWidth());
    assertEquals("res\\test\\layer\\test2", this.m.getCurrentLayer().getName());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImportLayeredImageInvalidDimensions() {
    this.m.addLayer("first");
    this.m.setCurrentLayer(0);
    this.m.setCurrentLayerImage(this.m.importImage("res\\test\\layer\\test.jpg", "jpg"));
    this.m.addLayer("second");
    this.m.setCurrentLayer(1);
    this.m.setCurrentLayerImage(this.m.importImage("res\\test\\layer\\test_bad.jpg", "jpg"));
  }
}
