package controller.commands;

import model.image.ImageLayerModel;

/**
 * Represents an interface for function objects that complete various operations on Images, such as
 * adding and removing layers, loading and saving Images, and applying filters.
 */
public interface Command {

  /**
   * Execute the operation of a specific command object's type on the given model's Images.
   *
   * @param specification a modifier value to effect the execution of a particular command
   * @param model         the ImageLayerModel object that controls the execution of commands and the
   *                      Images being modified
   */
  void execute(String specification, ImageLayerModel model);
}


