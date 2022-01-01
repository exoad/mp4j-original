package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Runner implements Runnable {

  public static String readInfo() throws IOException {
    if (new File(Items.items[1] + "/" + main.Sources.LIFEPRESERVER_PREVDIR).exists()
        || new File(Items.items[1] + "/" + main.Sources.LIFEPRESERVER_PREVDIR).isDirectory()) {

      BufferedReader br = new BufferedReader(
          new FileReader(new File(Items.items[1] + "/" + main.Sources.LIFEPRESERVER_PREVDIR)));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();
      while (line != null) {
        sb.append(line);
        line = br.readLine();
      }
      br.close();
      return sb.toString();
    }
    return ".";
  }

  @Override
  public void run() {
    File apiCache = new File(Items.items[0]);
    if (!apiCache.isDirectory()) {
      apiCache.mkdir();
    }
    File mpSaves = new File(Items.items[1]);
    if (!mpSaves.isDirectory()) {
      mpSaves.mkdir();
    }
  }

  public static void main(String[] args) throws InterruptedException, IOException {
    new Runner().run();
    new main.util.Splash(Items.SPLASH_SECONDS).run();
    new main.util.WelcomeWindow(readInfo()).run();
  }

}
