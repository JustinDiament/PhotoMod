package model.image;

import model.image.programmatic.ProgrammaticCreator;
import model.operation.Operations;

/**
 * Represents the model of an Image modification program. The model processes Images, modifies them
 * with operations such as filters chosen by the user, and handles file importing and exporting.
 */
public interface ImageProcessingModel {

  /**
   * Applies the given Image operation to the given Image.
   *
   * @param img the given Image to be modified
   * @param o   the name of the operation to be applied to the Image
   * @throws IllegalArgumentException if the given image is null, the given operation name is null
   *                                  or if it is not a valid operation type
   */
  Image applyOperation(Image img, Operations o) throws IllegalArgumentException;

  /**
   * Produces a programmatically-generated Image based on the specifications of the given
   * ProgrammaticCreator.
   *
   * @param creator a ProgrammaticCreator object for a specific programmatic image type, such as
   *                checkerboard. Specifies the characteristics of that image
   * @return the programmatically-generated Image
   * @throws IllegalArgumentException if the given ProgrammaticCreator is null
   */
  Image createProgrammaticImage(ProgrammaticCreator creator) throws IllegalArgumentException;
}
