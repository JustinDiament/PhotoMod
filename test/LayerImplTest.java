import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.image.Image;
import model.image.ImageImpl;
import model.image.Pixel;
import model.image.PixelImpl;
import model.image.layer.Layer;
import model.image.layer.LayerImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the LayerImpl class.
 */
public class LayerImplTest {

  private Image i1;
  private Layer l1;
  private Layer l2;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    List<List<Pixel>> l1 = new ArrayList<>();
    l1.add(new ArrayList<>(Collections.singletonList(new PixelImpl(10, 10, 10))));
    this.i1 = new ImageImpl(l1);


    this.l1 = new LayerImpl(this.i1, "l1");
    this.l2 = new LayerImpl(this.i1, "l2", true);
  }

  @Test
  public void testNullImageConstructor() {
    this.l1 = new LayerImpl(null, "l1");
    assertNull(this.l1.getImage());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullNameConstructor() {
    new LayerImpl(this.i1, null);
  }

  @Test
  public void testDefaultVisibilityFalse() {
    assertFalse(this.l1.getVisibility());
  }

  @Test
  public void testGetNullImage() {
    assertNull(new LayerImpl(null, "").getImage());
  }

  @Test
  public void testGetImageDeepCopy() {
    assertNotEquals(this.i1, this.l1.getImage());
    assertEquals(1, this.l1.getImage().getHeight());
    assertEquals(1, this.l1.getImage().getWidth());
    assertEquals(ImageImpl.class, this.l1.getImage().getClass());
  }

  @Test
  public void testGetName() {
    assertEquals("l1", this.l1.getName());
  }

  @Test
  public void testGetVisibility() {
    assertFalse(this.l1.getVisibility());
    assertTrue(this.l2.getVisibility());
  }

  @Test
  public void testSetVisibility() {
    assertFalse(this.l1.getVisibility());
    this.l1.setVisibility(true);
    assertTrue(this.l1.getVisibility());
    this.l1.setVisibility(false);
    assertFalse(this.l1.getVisibility());
  }
}
