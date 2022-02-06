package app.global;
/**
 * @author Jack Meng
 */
public abstract class SystemType {
  private SystemType() {}
  public static String osCXXExec() {
    if(System.getProperty("os.name").toLowerCase().contains("windows")) {
      return ".exe";
    } else {
      return ".out";
    }
  }

  public static String removeFileExtension(String f) {
    return f.substring(0, f.lastIndexOf('.'));
  }
}
