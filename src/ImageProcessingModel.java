/**
 * Represents the model of an Image modification program. The model takes in an Image provided by
 * the user and modifies it with operations such as filters chosen by the user.
 */
public interface ImageProcessingModel {

  /**
   * Applies the given Image operation to the Image stored by this model class.
   *
   * @param o the name of the operation to be applied to the Image
   * @throws IllegalArgumentException if the given operation name is null or if it is not a valid
   *                                  operation type
   */
  void applyOperation(Operations o) throws IllegalArgumentException;
}