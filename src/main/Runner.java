package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

import main.advisors.PropertiesReader;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;

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
  private static HashMap<String, String> keyedHolder = new HashMap<>();
  private static PropertiesReader properties;

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

    File apiCache = new File(Items.items[0]);
    if (!apiCache.isDirectory()) {
      apiCache.mkdir();
    }
    File mpSaves = new File(Items.items[1]);
    if (!mpSaves.isDirectory()) {
      mpSaves.mkdir();
    }

    File mpLogs = new File(Items.items[2]);
    if(!mpLogs.isDirectory()) {
      mpLogs.mkdir();
    }
  }

  private static void initLAF() throws IOException {
    try {
      holder = PropertiesReader.generalProp();
      keyedHolder = PropertiesReader.keyyedProp();
    } catch (IOException e) {
      e.printStackTrace();
    }
    properties = new PropertiesReader();
    String laf = properties.getProp("gui.defaultTheme");
    switch (laf) {
      case "regulardark":
        FlatDarkLaf.setup();
        break;
      case "material":
        FlatMaterialDarkerIJTheme.setup();
        break;
      case "onedark":
        FlatOneDarkIJTheme.setup();
        break;
      case "arcdark":
        FlatArcDarkIJTheme.setup();
        break;
      case "nord":
        FlatNordIJTheme.setup();
        break;
      case "dracula":
        FlatDraculaIJTheme.setup();
        break;
      case "gruvbox":
        FlatGruvboxDarkMediumIJTheme.setup();
        break;
      case "vuesion":
        FlatVuesionIJTheme.setup();
        break;
      case "regularlight":
        FlatLightLaf.setup();
        break;
      case "solarized":
        FlatSolarizedLightIJTheme.setup();
        break;
      default:
        FlatDarkLaf.setup();
        break;
    }
  }

  /**
   * @param args
   * @throws InterruptedException
   * @throws IOException
   */
  public static void main(String[] args) throws InterruptedException, IOException {
    new Runner().run();
    initLAF();
    new main.util.Splash(Items.SPLASH_SECONDS).run();
    new main.util.WelcomeWindow(readInfo()).run();
  }

}
