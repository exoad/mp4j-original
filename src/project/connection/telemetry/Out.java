package project.connection.telemetry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Out {
  private Out() {
  }

  public static void ln() {
    System.err.println();
  }

  public static void write(Object s) {
    System.err.print(s);
    ln();
  }

  /**
   * Prints the given object to the console
   * 
   * This alternative method is used to print any number
   * of objects to the console.
   * 
   * @param s The objects to print as a vararg
   */
  public static void write(Object... s) {
    for (Object o : s) {
      System.err.print(o);
    }
    ln();
  }

  public static void log(Object ... s) {
    for(Object o : s) {
      Date d = new Date(System.currentTimeMillis());
      DateFormat df = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
      System.err.print("[" + df.format(d) + "] > " + o.toString());
    }
    ln();
  }
}
