package project.usables.io;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Standard Output unused class.
 * 
 * @author Jack Meng
 */
public class Stdout extends PrintStream {
  public Stdout() {
    super(System.out);
  }

  public Stdout(boolean autoFlush) {
    super(System.out, autoFlush);
  }

  public Stdout(OutputStream out) {
    super(out);
  }

  public Stdout(OutputStream out, boolean autoFlush) {
    super(out, autoFlush);
  }

  /**
   * Prints somethign to the stdout with the current time.
   * 
   * @param obj the object to print
   */
  public synchronized void log(Object obj) {
    super.print("[" + System.currentTimeMillis() + "] " + obj);
  }

  /**
   * Prints somethign to the stdout with the current time.
   * 
   * @param obj the object to print
   */
  public synchronized void logln(Object obj) {
    super.println("[" + System.currentTimeMillis() + "] " + obj);
  }

}
