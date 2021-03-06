package controller;

/**
 * Represents the controller for an Image modification program. This controller can accept commands
 * for the Image modification program through user input on the keyboard, any Readable object, or
 * from a text file containing a list of commands. These commands will create and modify the layers
 * of a composite Image.
 */
public interface ImageController {

  /**
   * Run the controller and begin reading commands to control the Image modification program.
   *
   * @throws IllegalStateException if the controller is unable to render messages via the view
   */
  void run() throws IllegalStateException;
}
