import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import view.ImageTextView;
import view.ImageTextViewImpl;

/**
 * Test class for the ImageTextViewImpl class.
 */
public class ImageTextViewImplTest {

  private ImageTextView v1;
  private ImageTextView v2;
  private ImageTextView v3;
  private ImageTextView v4;
  private ByteArrayOutputStream b1;
  private PrintStream p1;
  private PrintStream p2;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.v1 = new ImageTextViewImpl();
    this.b1 = new ByteArrayOutputStream();
    this.p1 = System.out;
    this.p2 = new PrintStream(this.b1);
    this.v2 = new ImageTextViewImpl(this.p2);
    this.v3 = new ImageTextViewImpl(null);
    this.v4 = new ImageTextViewImpl(new BadAppendable());
  }

  /**
   * Reset variables after testing.
   */
  @After
  public void reset() {
    System.setOut(this.p1);
  }

  @Test
  public void testDefaultAppendableSystemOut() {
    System.setOut(this.p2);
    try {
      this.v1.renderMessage("");
      assertEquals("", this.b1.toString());
    }
    catch (IOException e) {
      assert false;
    }
  }

  @Test
  public void testNullAppendableSystemOut() {
    System.setOut(this.p2);
    try {
      this.v3.renderMessage("");
      assertEquals("", this.b1.toString());
    }
    catch (IOException e) {
      assert false;
    }
  }

  @Test
  public void testValidAppendable() {
    try {
      this.v2.renderMessage("test");
    }
    catch (IOException e) {
      assert false;
    }
    assertEquals("test", this.b1.toString());
    this.p2.append(" test");
    assertEquals("test test", this.b1.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderMessageTransmitFail() throws IOException {
    this.v4.renderMessage("");
  }

  @Test
  public void testRenderMessageCorrectly() {
    try {
      this.v2.renderMessage("test");
    }
    catch (IOException e) {
      assert false;
    }
    assertEquals("test", this.b1.toString());
  }

  @Test(expected = IOException.class)
  public void testRenderNullMessage() throws IOException {
    this.v4.renderMessage(null);
  }
}
