package model.image.programmatic;

import model.image.Image;

/**
 * To represent a function object that is intended to create a specific type of programmatic image
 * with configurable parameters.
 */
public interface ProgrammaticCreator {

  /**
   * Produces a programmatic Image of the implementing class's respective type and based on its
   * parameters' specifications.
   *
   * @return the programmatically-generated Image
   */
  Image create();
}