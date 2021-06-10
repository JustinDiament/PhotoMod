package model;

/**
 * This class contains utility methods to be reused throughout the entire project.
 */
public class ImageUtil {

  /**
   * Checks that the given input is not null.
   *
   * @param obj the object to be checked for being null
   * @param <T> the generic type of the given input
   * @return the input if it is not null
   * @throws IllegalArgumentException if the given input is null
   */
  public static <T> T requireNonNull(T obj) throws IllegalArgumentException {
    if (obj == null) {
      throw new IllegalArgumentException("Input cannot be null");
    }
    return obj;
  }
}
