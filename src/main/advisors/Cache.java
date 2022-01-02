package main.advisors;

import java.io.IOException;

import main.Items;

import java.io.File;

public abstract class Cache {
  private Cache() {
  }

  public static boolean cleanCache() throws IOException {
    File cache = new File(Items.items[0]);
    if (cache.exists()) {
      for (File f : cache.listFiles()) {
        f.delete();
      }
      cache.delete();
      return true;
    }
    return false;
  }
}
