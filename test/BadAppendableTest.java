import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BadAppendable class.
 */
public class BadAppendableTest {

  private Appendable a;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.a = new BadAppendable();
  }

  // test that append throws an exception when given a CharSequence
  @Test(expected = IOException.class)
  public void testAppend1() throws IOException {
    this.a.append("Hello, world!");
  }

  // test that append throws an exception when given a CharSequence and start/end indices
  @Test(expected = IOException.class)
  public void testAppend2() throws IOException {
    this.a.append("Hello, world!", 1, 3);
  }

  // test that append throws an exception when given a char
  @Test(expected = IOException.class)
  public void testAppend3() throws IOException {
    this.a.append('H');
  }
}
