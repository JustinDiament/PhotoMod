import java.io.IOException;
import java.nio.CharBuffer;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the BadReadable class.
 */
public class BadReadableTest {

  private Readable r;

  /**
   * Initialize variables for testing.
   */
  @Before
  public void init() {
    this.r = new BadReadable();
  }

  // test that read throws an exception when given a CharBuffer
  @Test(expected = IOException.class)
  public void testRead() throws IOException {
    this.r.read(CharBuffer.allocate(9));
  }
}
