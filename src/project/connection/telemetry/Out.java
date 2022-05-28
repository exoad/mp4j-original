package project.connection.telemetry;

public class Out {
  private Out() {}

  public static void write(Object s) {
    System.err.print(s);
  }

  public static void write(Object... s) {
    for (Object o : s) {
      System.err.print(o);
    }
  }
}
