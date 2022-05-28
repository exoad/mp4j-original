package project.usables;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileUsable {
  private FileUsable() {}

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
