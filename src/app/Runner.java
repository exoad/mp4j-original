package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMonokaiProContrastIJTheme;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import app.core.PropertiesReader;
import app.global.Items;
import app.global.cli.CliException;

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
 * @see app.global.Sources
 * @see app.Runner
 * @see app.global.Items
 * @see app.interfaces.Splash
 * @see app.interfaces.WelcomeWindow
 */

public class Runner {
  private static PropertiesReader pr;

  public Runner() {
    pr = new app.core.PropertiesReader();
  }
  /**
   * @return String
   * @throws IOException IO is used here
   */
  public static String readInfo() throws IOException {
    if (new File(Items.items[1] + System.getProperty("file.separator") + app.global.Sources.LIFEPRESERVER_PREVDIR)
        .exists()
        || new File(Items.items[1] + System.getProperty("file.separator") + app.global.Sources.LIFEPRESERVER_PREVDIR)
            .isDirectory()) {

      BufferedReader br = new BufferedReader(
          new FileReader(app.global.Items.items[1] + System.getProperty("file.separator")
              + app.global.Sources.LIFEPRESERVER_PREVDIR));
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

  public boolean run() throws IOException {


    System.setProperty("flatlaf.useJetBrainsCustomDecorations", "true");
    System.setProperty("flatlaf.animation", "true");

    File apiCache = new File(Items.items[0]);
    if (!apiCache.isDirectory()) {
      apiCache.mkdir();
    }
    File mpSaves = new File(Items.items[1]);
    if (!mpSaves.isDirectory()) {
      mpSaves.mkdir();
    }

    File customs = new File(Items.items[5]);
    if (!customs.isDirectory()) {
      customs.mkdir();
    }

    File mpLogs = new File(Items.items[2]);
    if (!mpLogs.isDirectory()) {
      mpLogs.mkdir();
    }

    try {
      initLAF();
    } catch (IOException e1) {
      e1.printStackTrace();
    }

    try {
      Socket socket = new Socket();
      socket.connect(new InetSocketAddress("google.com", 80), 3000);
      socket.close();
    } catch (IOException e) {
      BufferedWriter bw = new BufferedWriter(new FileWriter(app.global.Items.items[6]));
      bw.write("0");
      bw.close();
      return false;
    }
    BufferedWriter bw = new BufferedWriter(new FileWriter(app.global.Items.items[6]));
    bw.write("1");
    bw.close();

    return true;
  }

  private static void initLAF() throws IOException {
    CLI.print(new PropertiesReader().toString(), app.global.cli.CliType.WARNING);
    if (pr.getVal("gui.buttonShape").equals("round")) {
      UIManager.put("Button.arc", 999);
      UIManager.put("CheckBox.arc", 999);
      UIManager.put("ComboBox.arc", 999);
      UIManager.put("TextComponent.arc", 999);
    } else if (pr.getVal("gui.buttonShape").equals("square")) {
      UIManager.put("Button.arc", 0);
      UIManager.put("CheckBox.arc", 0);
      UIManager.put("ComboBox.arc", 0);
      UIManager.put("TextComponent.arc", 0);
    }
    String laf = pr.getVal("gui.defaultTheme");
    switch (laf) {
      case "material":
        FlatMaterialDarkerIJTheme.setup();
        CLI.print("Material Theme", app.global.cli.CliType.INFO);
        break;
      case "onedark":
        FlatOneDarkIJTheme.setup();
        CLI.print("One Dark Theme", app.global.cli.CliType.INFO);
        break;
      case "arcdark":
        FlatArcDarkIJTheme.setup();
        CLI.print("Arc Dark Theme", app.global.cli.CliType.INFO);
        break;
      case "nord":
        FlatNordIJTheme.setup();
        CLI.print("Nord Theme", app.global.cli.CliType.INFO);
        break;
      case "dracula":
        FlatDraculaIJTheme.setup();
        CLI.print("Dracula Theme", app.global.cli.CliType.INFO);
        break;
      case "gruvbox":
        FlatGruvboxDarkMediumIJTheme.setup();
        CLI.print("Gruvbox Theme", app.global.cli.CliType.INFO);
        break;
      case "vuesion":
        FlatVuesionIJTheme.setup();
        CLI.print("Vuesion Theme", app.global.cli.CliType.INFO);
        break;
      case "regularlight":
        FlatLightLaf.setup();
        CLI.print("Regular Light Theme", app.global.cli.CliType.INFO);
        break;
      case "solarized":
        FlatSolarizedLightIJTheme.setup();
        CLI.print("Solarized Light Theme", app.global.cli.CliType.INFO);
        break;
      case "gradientogreen":
        FlatMonokaiProContrastIJTheme.setup();
        CLI.print("Gradiento Green Theme", app.global.cli.CliType.INFO);
        break;
      case "materiallighter":
        FlatMaterialLighterIJTheme.setup();
        CLI.print("Material Light Theme", app.global.cli.CliType.INFO);
        break;
    }

  }

  /**
   * @param args
   * @throws InterruptedException
   * @throws IOException
   * @throws CliException
   */
  public static void main(String[] args) throws IOException {

    CLI.print(Runner.readInfo());
    CLI.print(Runner.class);


    /**
     * Disposed:
     * 
     * Thread cli = new Thread(CLI::runAsInterface);
     */



    new Runner().run();
    new app.interfaces.Splash(Items.SPLASH_SECONDS).run();
    new app.interfaces.WelcomeWindow(readInfo()).run();
    
    
    //cli.start(); - Until fully implemented for a standard CLI-based debug session
  }

}
