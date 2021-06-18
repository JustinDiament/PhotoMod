import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import controller.commands.VisibilityCommand;
import java.awt.Color;
import java.util.Scanner;
import model.image.ImageLayerModel;
import model.image.ImageLayerModelImpl;
import model.image.programmatic.CreateCheckerboard;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the class VisibilityCommand.
 */
public class VisibilityCommandTest {

  private ImageLayerModel model;
  private VisibilityCommand visibility;

  @Before
  public void init() {
    this.model = new ImageLayerModelImpl();
    this.visibility = new VisibilityCommand();

    this.model.addLayer("one");
    this.model.addLayer("two");
    this.model.setCurrentLayer(1);
    this.model.setCurrentLayerVisibility(false);
    this.model.setCurrentLayer(0);
    this.model.createProgrammaticImage(new CreateCheckerboard(16, 4, Color.ORANGE, Color.CYAN));
  }

  @Test(expected = IllegalArgumentException.class)
  public void visibilityScannerCannotBeNull() {
    visibility.execute(null, this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void visibilityCurrentLayerModelCannotBeNull() {
    visibility.execute(new Scanner("visible"), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void visibilityNoLevelProvided() {
    visibility.execute(new Scanner(""), this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void visibilityInvalidVisibleLevel() {
    visibility.execute(new Scanner("hello"), this.model);
  }

  @Test
  public void visibilityChangeVisibleLayerToInvisibleSuccessfully() {
    assertTrue(this.model.getCurrentLayer().getVisibility());

    visibility.execute(new Scanner("invisible"), this.model);

    assertFalse(this.model.getCurrentLayer().getVisibility());
  }

  @Test
  public void visibilityVisibleLayerStaysVisibleSuccessfully() {
    assertTrue(this.model.getCurrentLayer().getVisibility());

    visibility.execute(new Scanner("visible"), this.model);

    assertTrue(this.model.getCurrentLayer().getVisibility());
  }

  @Test
  public void visibilityChangeInvisibleLayerToVisibleSuccessfully() {
    this.model.setCurrentLayer(1);

    assertFalse(this.model.getCurrentLayer().getVisibility());

    visibility.execute(new Scanner("visible"), this.model);

    assertTrue(this.model.getCurrentLayer().getVisibility());
  }

  @Test
  public void visibilityInvisibleLayerStaysInvisibleSuccessfully() {
    this.model.setCurrentLayer(1);

    assertFalse(this.model.getCurrentLayer().getVisibility());

    visibility.execute(new Scanner("invisible"), this.model);

    assertFalse(this.model.getCurrentLayer().getVisibility());
  }

  @Test
  public void visibilityExtraInputIgnored() {
    this.model.setCurrentLayer(1);

    assertFalse(this.model.getCurrentLayer().getVisibility());

    visibility.execute(new Scanner("invisible stuff"), this.model);

    assertFalse(this.model.getCurrentLayer().getVisibility());
  }
}
