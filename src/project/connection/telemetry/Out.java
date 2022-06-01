package project.connection.telemetry;

/**
 * This class is created to handle basic I/O
 * properties that the user must know.
 * 
 * The reason System.err.print() is used because
 * the STDOUT is clogged by other libaries and executables,
 * thus making the program run slower.
 * 
 * @author Jack Meng
 * @since 2.1
 */
public class Out {
  private Out() {
  }

  /**
   * Prints the given object to the console.
   * 
   * @param s The object to print
   */
  public static void write(Object s) {
    System.err.print(s);
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
  }
}
