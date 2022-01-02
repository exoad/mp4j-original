package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

import main.advisors.PropertiesReader;

/**
 * <h1>Runner</h1>
 * <p>
 * This class makes sure everything
 * is running the same events and
 * reads the data from the save locations
 * and loads them into the following
 * windows and classes
 * <p>
 *
 * @author Jack Meng
 *
 * @since 1.1
 * @see main.Sources
 * @see main.Runner
 * @see main.Items
 * @see main.util.Splash
 * @see main.util.WelcomeWindow
 */

public class Runner implements Runnable {
  private static HashSet<String> holder = new HashSet<>();
  /** 
   * @return String
   * @throws IOException
   */
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
    System.setProperty("flatlaf.useJetBrainsCustomDecorations", "true");
    System.setProperty("flatlaf.animation", "false");
    try {
      holder = PropertiesReader.generalProp();
    } catch (IOException e) {
      e.printStackTrace();
    }
    File apiCache = new File(Items.items[0]);
    if (!apiCache.isDirectory()) {
      apiCache.mkdir();
    }
    File mpSaves = new File(Items.items[1]);
    if (!mpSaves.isDirectory()) {
      mpSaves.mkdir();
    }
  }

  /** 
   * @param args
   * @throws InterruptedException
   * @throws IOException
   */
  public static void main(String[] args) throws InterruptedException, IOException {
    new Runner().run();
    new main.util.Splash(Items.SPLASH_SECONDS).run();
    new main.util.WelcomeWindow(readInfo()).run();
  }

}
