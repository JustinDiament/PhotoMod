import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import controller.commands.ChangeCurrentLayerCommand;
import java.awt.Color;
import java.util.Scanner;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.programmatic.CreateCheckerboard;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the class ChangeCurrentLayerCommand.
 */
public class ChangeCurrentLayerCommandTest {

  private ImageLayerModel model;
  private ChangeCurrentLayerCommand change;

  @Before
  public void init() {
    this.model = new ImageLayerModelImpl();
    this.change = new ChangeCurrentLayerCommand();

    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(1);
    this.model.setCurrentLayerVisibility(false);
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.CYAN));
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeCurrentLayerScannerCannotBeNull() {
    change.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeCurrentLayerModelCannotBeNull() {
    change.execute(new Scanner("two"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeCurrentLayerNoLayerNameProvided() {
    change.execute(new Scanner(""), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void changeCurrentLayerNameDoesNotExist() {
    change.execute(new Scanner("three"), this.model);
  }

  @Test
  public void changeCurrentLayerChangeToAnotherLayerSuccessfully() {
    assertNotEquals(null, this.model.getCurrentLayer().getImage());
    assertTrue(this.model.getCurrentLayer().getVisibility());
    assertEquals("one", this.model.getCurrentLayer().getName());

    change.execute(new Scanner("two"), this.model);

    assertNull(this.model.getCurrentLayer().getImage());
    assertFalse(this.model.getCurrentLayer().getVisibility());
    assertEquals("two", this.model.getCurrentLayer().getName());
  }

  @Test
  public void changeCurrentLayerExtraInputIgnored() {
    assertNotEquals(null, this.model.getCurrentLayer().getImage());
    assertTrue(this.model.getCurrentLayer().getVisibility());
    assertEquals("one", this.model.getCurrentLayer().getName());

    change.execute(new Scanner("two stuff"), this.model);

    assertNull(this.model.getCurrentLayer().getImage());
    assertFalse(this.model.getCurrentLayer().getVisibility());
    assertEquals("two", this.model.getCurrentLayer().getName());
  }

  @Test
  public void changeToCurrentLayerStaysOnCurrentLayerProperly() {
    assertNotEquals(null, this.model.getCurrentLayer().getImage());
    assertTrue(this.model.getCurrentLayer().getVisibility());
    assertEquals("one", this.model.getCurrentLayer().getName());

    change.execute(new Scanner("one"), this.model);

    assertNotEquals(null, this.model.getCurrentLayer().getImage());
    assertTrue(this.model.getCurrentLayer().getVisibility());
    assertEquals("one", this.model.getCurrentLayer().getName());
  }
}
