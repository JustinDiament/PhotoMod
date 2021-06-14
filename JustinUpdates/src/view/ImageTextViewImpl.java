package view;

import java.io.IOException;

/**
 * Represents an implementation of a textual view for an Image modification program. This view
 * informs the user of what modifications are being applied to an Image and any errors that occur
 * based on user input or otherwise via text-based messages.
 */
public class ImageTextViewImpl implements ImageTextView {

  private final Appendable ap;

  /**
   * Constructs a ImageTextViewImpl object using a given Appendable object.
   *
   * @param ap the Appendable that messages to be displayed in the view will be transcribed to
   */
  public ImageTextViewImpl(Appendable ap) {
    this.ap = (ap == null) ? System.out : ap;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    if (message == null) {
      throw new IOException("Cannot render a null message");
    }
    this.ap.append(message);
  }
}
