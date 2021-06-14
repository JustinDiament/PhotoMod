package view;

import java.io.IOException;

/**
 * Represents a textual view for an Image modification program. This view informs the user of
 * what modifications are being applied to an Image and any errors that occur based on user input or
 * otherwise via text-based messages.
 */
public interface ImageTextView {

  /**
   * Render a specific message to the provided data destination.
   *
   * @param message the message to be transmitted
   * @throws IOException if transmission of the board to the provided data destination fails
   */
  void renderMessage(String message) throws IOException;
}
