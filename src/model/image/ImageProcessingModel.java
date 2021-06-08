//package model.image;
//
//import model.image.programmatic.ProgrammaticCreator;
//import model.operation.Operations;
//
///**
// * Represents the model of an Image modification program. The model takes in an Image provided by
// * the user and modifies it with operations such as filters chosen by the user.
// */
//public interface ImageProcessingModel {
//
//  /**
//   * Applies the given Image operation to the Image stored by this model class.
//   * // todo: fix
//   * @param o the name of the operation to be applied to the Image
//   * @throws IllegalArgumentException if the given operation name is null or if it is not a valid
//   *                                  operation type
//   */
//  Image applyOperation(Image img, Operations o) throws IllegalArgumentException;
//
//  /**
//   * Produces a programmatically-generated Image based on the specifications of the given
//   * ProgrammaticCreator.
//   *
//   * @param creator a ProgrammaticCreator object for a specific programmatic image type, such as
//   *                checkerboard. Specifies the characteristics of that image
//   * @return the programmatically-generated Image
//   */
//  Image createProgrammaticImage(ProgrammaticCreator creator);
//}