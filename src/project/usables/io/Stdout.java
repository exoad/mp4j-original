package project.usables.io;

import java.io.OutputStream;
import java.io.PrintStream;

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

  public synchronized void log(Object obj) {
    super.print("["+System.currentTimeMillis() + "] " + obj);
  }

  public synchronized void logln(Object obj) {
    super.println("[" + System.currentTimeMillis() + "] " + obj);
  }

}
