package controller;

import controller.commands.BlurCommand;
import controller.commands.ChangeCurrentLayerCommand;
import controller.commands.Command;
import controller.commands.CreateLayerCommand;
import controller.commands.MonochromeCommand;
import controller.commands.RemoveLayerCommand;
import controller.commands.SepiaCommand;
import controller.commands.SharpenCommand;
import controller.commands.VisibilityCommand;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.ImageUtil;
import model.image.ImageProcessingModel;

public class ImageControllerImpl implements ImageController {

  // store an instance of the model as a field
  // eventually, store an appendable/instance of the view to render messages

  // having two different constructors, take in a readable?
  // is reading from a file similar to readable/scanner?

  // method handle user input from a readable
  // probably will need helper methods to store the supported commands
  // maybe command pattern here? ^

  ImageProcessingModel model;
  Readable rd;
  //Appendable ap; TODO: figure out if/how we are communicating errors in input to the user

  public ImageControllerImpl(ImageProcessingModel model, Readable rd/*, Appendable ap*/)
      throws IllegalArgumentException {

    this.model = ImageUtil.requireNonNull(model);
    this.rd = ImageUtil.requireNonNull(rd);
    //this.ap
  }


  protected Map<String, Command> getCommands() {
    Map<String, Command> commands = new HashMap<>();

    commands.put("Blur", new BlurCommand());
    commands.put("Sharpen", new SharpenCommand());
    commands.put("Sepia", new SepiaCommand());
    commands.put("Monochrome", new MonochromeCommand());
    commands.put("Current", new ChangeCurrentLayerCommand());
    commands.put("CreateLayer", new CreateLayerCommand());
    commands.put("RemoveLayer", new RemoveLayerCommand());
    commands.put("Visibility", new VisibilityCommand());

    return commands;
  }

  public void run() {
    Scanner scanner = new Scanner(rd);

      List<Command> commandsToRun = parseInput(scanner);

  }

  private List<Command> parseInput(Scanner scanner) {
    Map<String, Command> possibleCommands = this.getCommands();

    List<Command> commandsToRun = new ArrayList<>();

    while(scanner.hasNext()) {
      String latestCommand = scanner.next();

      if (scanner.hasNext()) {
        String specification = scanner.next();
      }
      else {
        // todo: decide what to do if odd number of strings in input
      }

      commandsToRun.add(possibleCommands.getOrDefault(latestCommand, null));
      // todo: make it throw a proper error if null

      // probably just run it now not return the list? adjust accordingly
    }

    return null;
  }

}
