package app.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class PropertiesBuffer {
  private PropertiesBuffer() {
  }
  private static boolean checkSums(final File f) {
    return f.exists() && f.isFile() && f.canRead() && f.canWrite() && f.length() > 0;
  }
  public static Map<Object, Object> readProperties(final File f) {
    if(checkSums(f)) {
      try {
        Properties p = new Properties();
        p.load(new FileInputStream(f));
        return p;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return Collections.emptyMap();
  }
}
