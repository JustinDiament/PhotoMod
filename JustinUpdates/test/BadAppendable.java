import java.io.IOException;

/**
 * An appendable object used to test exceptions.
 */
public class BadAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException("Unsupported operation");
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException("Unsupported operation");
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException("Unsupported operation");
  }
}