package app.core;

import app.global.Items;

import java.io.File;
import java.io.IOException;

/**
 * @author Jack Meng
 */

public abstract class Cache {
  private Cache() {
  }

  public static boolean cleanCache() throws IOException {
    File cache = new File(Items.items[0]);
    if (cache.isDirectory()) {
      for (File f : java.util.Objects.requireNonNull(cache.listFiles())) {
        f.delete();
      }
      File audio = new File(Items.items[0] + "\\audio");
      if (audio.isDirectory()) {
        for (File f : java.util.Objects.requireNonNull(audio.listFiles())) {
          f.delete();
        }
      }


      return true;
    }
    return false;
  }
}
