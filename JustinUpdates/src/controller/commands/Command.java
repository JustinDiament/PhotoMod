package controller.commands;

import java.util.Scanner;
import model.image.ImageLayerModel;

/**
 * Represents an interface for function objects that complete various operations on Images, such as
 * adding and removing layers, loading and saving Images, and applying filters.
 */
public interface Command {

  /**
   * Execute the operation of a specific command object's type on the given model's Images.
   *
   * @param scanner the Scanner object to read data to configure the command's execution parameters
   * @param model   the ImageLayerModel object that controls the execution of commands and the
   *                Images being modified
   * @throws IllegalArgumentException if the command fails to execute successfully
   */
  void execute(Scanner scanner, ImageLayerModel model) throws IllegalArgumentException;
}
