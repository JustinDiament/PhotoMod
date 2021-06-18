import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.commands.RemoveLayerCommand;
import java.awt.Color;
import java.util.Scanner;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.programmatic.CreateCheckerboard;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the class RemoveLayerCommand.
 */
public class RemoveLayerCommandTest {

  private ImageLayerModel model;
  private RemoveLayerCommand remove;

  @Before
  public void init() {
    this.model = new ImageLayerModelImpl();
    this.remove = new RemoveLayerCommand();

    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(1);
    this.model.setCurrentLayerVisibility(false);
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.CYAN));
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeLayerScannerCannotBeNull() {
    remove.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeCurrentLayerModelCannotBeNull() {
    remove.execute(new Scanner("two"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeLayerNoLayerNameProvided() {
    remove.execute(new Scanner(""), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void removeLayerNameDoesNotExist() {
    remove.execute(new Scanner("three"), this.model);
  }

  @Test
  public void removeLayerRemovedWhileCurrentSuccessfullyAndCurrentLayerReset() {
    this.model.setCurrentLayer(1);
    assertEquals("two", this.model.getCurrentLayer().getName());

    this.model.setCurrentLayer(0);
    assertEquals("one", this.model.getCurrentLayer().getName());

    assertTrue(this.model.getLayerNames().contains("one"));

    remove.execute(new Scanner("one"), this.model);

    try {
      this.model.getCurrentLayer();
    } catch (IllegalArgumentException e) {
      assertEquals("Layer index is invalid", e.getMessage());
    }

    assertFalse(this.model.getLayerNames().contains("one"));
  }

  @Test
  public void removeLayerRemovedWhileNotCurrentSuccessfullyAndCurrentLayerReset() {
    this.model.setCurrentLayer(0);
    assertEquals("one", this.model.getCurrentLayer().getName());

    this.model.setCurrentLayer(1);
    assertEquals("two", this.model.getCurrentLayer().getName());

    assertTrue(this.model.getLayerNames().contains("one"));

    remove.execute(new Scanner("one"), this.model);

    try {
      this.model.getCurrentLayer();
    } catch (IllegalArgumentException e) {
      assertEquals("Layer index is invalid", e.getMessage());
    }

    assertFalse(this.model.getLayerNames().contains("one"));
  }


  @Test
  public void removeLayerExtraInputIgnored() {
    this.model.setCurrentLayer(0);
    assertEquals("one", this.model.getCurrentLayer().getName());

    this.model.setCurrentLayer(1);
    assertEquals("two", this.model.getCurrentLayer().getName());

    assertTrue(this.model.getLayerNames().contains("one"));

    remove.execute(new Scanner("one stuff"), this.model);

    try {
      this.model.getCurrentLayer();
    } catch (IllegalArgumentException e) {
      assertEquals("Layer index is invalid", e.getMessage());
    }

    assertFalse(this.model.getLayerNames().contains("one"));
  }
}
