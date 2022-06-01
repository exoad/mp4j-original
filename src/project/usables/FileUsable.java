package project.usables;

/**
 * Originally made to alter certain things about Files.
 * 
 * @author Jack Meng
 * @since 2.1
 * @deprecated Planning to move this to {@link project.Utils}
 */
@Deprecated(since = "2.1", forRemoval = true)
public class FileUsable {
  private FileUsable() {}

  /**
   * Returns an array of files that have been shuffled from the
   * original given array.
   * @param <T> The type of the array
   * @param files The array to shuffle
   * @return <T> The shuffled array
   */
  public static <T> T[] shuffle(T[] files) {
    for (int i = files.length - 1; i > 0; i--) {
      int index = (int) (Math.random() * (i + 1));
      T temp = files[index];
      files[index] = files[i];
      files[i] = temp;
    }
    return files;
  }
}
