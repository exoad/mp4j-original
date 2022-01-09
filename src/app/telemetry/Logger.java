package app.telemetry;

import java.io.IOException;

import app.global.Items;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Logger {
  public static String log(Object t) {
    long time = System.currentTimeMillis();
    File curr = new File(Items.items[2] + "/" + "Log_" + time + ".log");

    try(BufferedWriter bw = new BufferedWriter(new FileWriter(curr))) {
      bw.write(time + " " + t.toString());
      bw.write("Stack Trace Dump\n");
      bw.write(t.toString());
      bw.flush();
      bw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return curr.getAbsolutePath();
  }

  public static boolean clear() {
    File curr = new File(Items.items[2]);
    File[] files = curr.listFiles();
    for (File f : files) {
      if (f.getName().endsWith(".log")) {
        f.delete();
      }
    }
    return true;
  }
}
