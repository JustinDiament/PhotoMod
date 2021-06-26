import org.junit.Before;
import org.junit.Test;
import view.ImageView;
import view.ImageViewImpl;

/**
 * Test class for the ImageViewImpl class. Note that running this test class will cause the GUI to
 * display briefly, then disappear.
 */
public class ImageViewImplTest {

  private ImageView iv;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.iv = new ImageViewImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullViewEventListener() {
    this.iv.addViewEventListener(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNullLayerToDropdown() {
    this.iv.addNewLayerToDropdown(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeCurrentLayerTextNull() {
    this.iv.changeCurrentLayerText(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testChangeCurrentVisibleLayerTextNull() {
    this.iv.changeCurrentVisibleLayerText(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNullLayerName() {
    this.iv.removeLayerName(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRenderNullErrorMessage() {
    this.iv.renderErrorMessage(null);
  }
}
