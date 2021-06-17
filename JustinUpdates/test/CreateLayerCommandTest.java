import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.commands.CreateLayerCommand;
import java.util.Scanner;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the class CreateLayer.
 */
public class CreateLayerCommandTest {

  private ImageLayerModel model;
  private CreateLayerCommand create;

  @Before
  public void init() {
    this.model = new ImageLayerModelImpl();
    this.create = new CreateLayerCommand();

    this.model.addLayer("one");
  }

  @Test(expected = IllegalArgumentException.class)
  public void createLayerScannerCannotBeNull() {
    create.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createLayerModelCannotBeNull() {
    create.execute(new Scanner("two"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createLayerButLayerWithGivenNameAlreadyExists() {
    create.execute(new Scanner("one"), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createLayerButNoNameGiven() {
    create.execute(new Scanner(""), this.model);
  }

  @Test
  public void createLayerSuccessfullyAdded() {
    assertFalse(this.model.getLayerNames().contains("two"));

    create.execute(new Scanner("two"), this.model);

    assertTrue(this.model.getLayerNames().contains("two"));

    this.model.setCurrentLayer(1);
    assertEquals("two", this.model.getCurrentLayer().getName());
    assertTrue(this.model.getCurrentLayer().getVisibility());
    assertEquals(null, this.model.getCurrentLayer().getImage());
  }

  @Test
  public void createLayerExtraInputIgnored() {
    assertFalse(this.model.getLayerNames().contains("two"));

    create.execute(new Scanner("two stuff"), this.model);

    assertTrue(this.model.getLayerNames().contains("two"));

    this.model.setCurrentLayer(1);
    assertEquals("two", this.model.getCurrentLayer().getName());
    assertTrue(this.model.getCurrentLayer().getVisibility());
    assertEquals(null, this.model.getCurrentLayer().getImage());
  }
}
