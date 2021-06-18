package controller;

import controller.commands.BlurCommand;
import controller.commands.ChangeCurrentLayerCommand;
import controller.commands.Command;
import controller.commands.CreateCheckerboardCommand;
import controller.commands.CreateLayerCommand;
import controller.commands.ExportAllCommand;
import controller.commands.ExportCommand;
import controller.commands.ImportCommand;
import controller.commands.MonochromeCommand;
import controller.commands.RemoveLayerCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import controller.commands.VisibilityCommand;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageLayerModel;
import view.ImageTextView;
import view.ImageTextViewImpl;

/**
 * Represents an implementation of the controller for an Image modification program. This controller
 * can accept commands for the Image modification program through user input on the keyboard, any
 * Readable object, or from a text file containing a list of commands. These commands will create
 * and modify the layers of a composite Image. This controller implementation accepts one word
 * commands followed by a specification/modifier for how that command should be applied.
 */
public class ImageControllerImpl implements ImageController {

  private final ImageLayerModel model;
  private final Readable rd;
  private final ImageTextView view;

  /**
   * Constructs a ImageControllerImpl object that carries out operations based on the given model's
   * specifications and reads commands from the given Readable object.
   *
   * @param model the model to carry out the requested operations
   * @param rd    the Readable object that specifies operations and modifiers to be carried out on
   *              the model's Image and its layers. The Readable can be user input from the keyboard
   *              via System.in, a StringReader, or a FileReader to read commands from a text file
   * @param ap    an Appendable object that will be passed to a view to communicate any errors that
   *              occur in command input
   * @throws IllegalArgumentException if any of the given arguments are null
   */
  public ImageControllerImpl(ImageLayerModel model, Readable rd, Appendable ap)
      throws IllegalArgumentException {

    this.model = ImageUtil.requireNonNull(model);
    this.rd = ImageUtil.requireNonNull(rd);
    this.view = new ImageTextViewImpl(ImageUtil.requireNonNull(ap));
  }

  /**
   * Constructs a ImageControllerImpl object that carries out operations based on the given model's
   * specifications and reads commands from the file with the given name.
   *
   * @param model    the model to carry out the requested operations
   * @param fileName the name of a file containing commands to be used to control the Image
   *                 modification program
   * @param ap       an Appendable object that will be passed to a view to communicate any errors
   *                 that occur in command input
   * @throws IllegalArgumentException if any of the given arguments are null
   */
  public ImageControllerImpl(ImageLayerModel model, String fileName, Appendable ap)
      throws IllegalArgumentException {

    this.model = ImageUtil.requireNonNull(model);
    this.view = new ImageTextViewImpl(ImageUtil.requireNonNull(ap));

    try {
      this.rd = new FileReader(ImageUtil.requireNonNull(fileName));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException(String.format("File %s not found", fileName));
    }
  }

  /**
   * Constructs a ImageControllerImpl object that carries out operations based on the given model's
   * specifications, reads commands from the keyboard via System.in, and renders messages to the
   * user via System.out
   *
   * @param model the model to carry out the requested operations
   * @throws IllegalArgumentException if any of the given arguments are null
   */
  public ImageControllerImpl(ImageLayerModel model)
      throws IllegalArgumentException {
    this(model, new InputStreamReader(System.in), System.out);
  }

  /**
   * Produces a Map of the text commands for various operations to the function objects that carry
   * out the operations supported by this controller implementation.
   *
   * @return a HashMap of the operations supported by this controller implementation
   */
  protected Map<String, Command> getCommands() {
    Map<String, Command> commands = new HashMap<>();
    commands.put("blur", new BlurCommand());
    commands.put("sharpen", new SharpenCommand());
    commands.put("sepia", new SepiaCommand());
    commands.put("monochrome", new MonochromeCommand());
    commands.put("current", new ChangeCurrentLayerCommand());
    commands.put("createlayer", new CreateLayerCommand());
    commands.put("removelayer", new RemoveLayerCommand());
    commands.put("visibility", new VisibilityCommand());
    commands.put("createcheckerboard", new CreateCheckerboardCommand());
    commands.put("import", new ImportCommand());
    commands.put("exportall", new ExportAllCommand());
    commands.put("export", new ExportCommand());

    return commands;
  }

  /**
   * Send a message to the provided data destination to be rendered by the view.
   *
   * @param message the message to be transmitted
   * @throws IllegalStateException if transmission of the message to the provided destination fails
   */
  private void renderMessage(String message) throws IllegalStateException {
    try {
      this.view.renderMessage(message + "\n");
    } catch (IOException e) {
      throw new IllegalStateException("Failed transmitting the message to the provided Appendable");
    }
  }

  @Override
  public void run() {
    Scanner scanner = new Scanner(this.rd);
    Map<String, Command> possibleCommands = this.getCommands();

    while (scanner.hasNext()) {
      String latestCommand = scanner.next().toLowerCase();

      if (this.isQuit(latestCommand)) {
        return;
      }

      Command commandToRun = possibleCommands.getOrDefault(latestCommand, null);

      if (commandToRun != null) {
        try {
          commandToRun.execute(scanner, this.model);
        } catch (IllegalArgumentException | IllegalStateException e) {
          this.renderMessage("Command failed to execute. Reason: " + e.getMessage());
        }
      } else {
        this.renderMessage("Provided command is invalid or not supported.");
      }
    }
  }

  /**
   * Checks if the user input indicates that the program should quit scripting.
   *
   * @param input the message to check for quitting
   * @return whether the user input has indicated to quit scripting
   */
  private boolean isQuit(String input) {
    if (input.equalsIgnoreCase("quit")) {
      this.renderMessage("Image processing has been quit.");
      return true;
    }
    return false;
  }
}
