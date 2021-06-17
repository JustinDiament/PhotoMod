import java.io.IOException;
import java.nio.CharBuffer;

/**
 * A Readable object used to test exceptions.
 */
public class BadReadable implements Readable {

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException("Unsupported operation");
  }
}
