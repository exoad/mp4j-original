package project.connection;

public class Bridge {
  static {
    System.loadLibrary("CApi");
  }
  public synchronized native String cliPrint(String s);
}
