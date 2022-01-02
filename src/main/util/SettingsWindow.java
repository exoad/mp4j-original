package main.util;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.net.URL;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import main.advisors.CXX;
import main.advisors.JSONParser;
import main.advisors.PropertiesReader;
import main.telemetry.Logger;
import main.VersionInfo;
import main.advisors.Cache;

public class SettingsWindow implements Runnable, ActionListener {
  public static JFrame frame;
  private final JPanel panel;
  private final JLabel title;
  private final JLabel information;
  private final JButton verifyFile, clearCache, resetProperties, clearLogs;
  private final CXX run = new CXX();

  public SettingsWindow(WelcomeWindow something) throws IOException {
    
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setPreferredSize(new Dimension(500, 400));

    title = new JLabel("Settings");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(title.getFont().deriveFont(title.getFont().getSize() * 1.5f));

    verifyFile = new JButton("Verify File Integrity");
    verifyFile.setAlignmentX(Component.CENTER_ALIGNMENT);
    URL URLFILEINT = getClass().getResource("/fileint_icon.png");
    assert URLFILEINT != null;
    Icon fileINTCO = new ImageIcon(URLFILEINT);
    verifyFile.setIcon(fileINTCO);
    verifyFile.addActionListener(this);
    run.callAPI();
    String json = run.callAPI();
    String versionInfo = ("<html><p>Your Version: " + VersionInfo.VERSION + "<br>Latest Release: "
        + (JSONParser.parseElement("latest_release", json) == null
            || java.util.Objects.equals(main.advisors.JSONParser.parseElement("latest_release", json), "")
                ? "Unavaliable"
                : JSONParser.parseElement("latest_release", json))
        + "<br>"
        + "Latest Patch: "
        + (JSONParser.parseElement("latest_patch", json) == null
            || java.util.Objects.equals(main.advisors.JSONParser.parseElement("latest_patch", json), "")
                ? "Unavaliable"
                : JSONParser.parseElement("latest_patch", json))
        + "<br>" + "Latest Beta: "
        + (JSONParser.parseElement("latest_beta", json) == null
            || java.util.Objects.equals(main.advisors.JSONParser.parseElement("latest_beta", json), "")
                ? "Unavaliable"
                : JSONParser.parseElement("latest_beta", json))
        + "</p></html>");

    information = new JLabel(versionInfo);
    information.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    information.setFont(information.getFont().deriveFont(information.getFont().getStyle() | Font.ITALIC));

    clearCache = new JButton("Clear Cache");
    clearCache.setAlignmentX(Component.CENTER_ALIGNMENT);
    URL URLCLEARCACHE = getClass().getResource("/trashcan_icon.png");
    assert URLCLEARCACHE != null;
    Icon trashcanCO = new ImageIcon(URLCLEARCACHE);
    clearCache.setIcon(trashcanCO);
    clearCache.addActionListener(this);

    clearLogs = new JButton("Clear Logs");
    clearLogs.setAlignmentX(Component.CENTER_ALIGNMENT);
    clearLogs.setIcon(trashcanCO);
    clearLogs.addActionListener(this);

    resetProperties = new JButton("Reset Properties");
    resetProperties.setAlignmentX(Component.CENTER_ALIGNMENT);
    URL URLRESETPROP = getClass().getResource("/reset_icon.png");
    assert URLRESETPROP != null;
    Icon resetPROPCO = new ImageIcon(URLRESETPROP);
    resetProperties.setIcon(resetPROPCO);
    resetProperties.addActionListener(this);

    panel.add(title);
    panel.add(Box.createHorizontalStrut(15));
    panel.add(verifyFile);
    panel.add(clearCache);
    panel.add(resetProperties);
    panel.add(clearLogs);
    panel.add(Box.createHorizontalStrut(10));
    panel.add(Box.createHorizontalStrut(10));
    panel.add(information);

    URL icon = getClass().getResource("/information_icon.png");
    assert icon != null;
    ImageIcon imageIcon = new ImageIcon(icon);
    frame = new JFrame("Music Player | Settings");
    frame.setSize(500, 600);
    frame.setResizable(false);
    frame.setIconImage(imageIcon.getImage());
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(panel);
  }

  public SettingsWindow() throws IOException {
    

    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setPreferredSize(new Dimension(500, 600));

    title = new JLabel("Settings");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(title.getFont().deriveFont(title.getFont().getSize() * 2.0f));

    verifyFile = new JButton("Check File Integrity");
    verifyFile.setAlignmentX(Component.CENTER_ALIGNMENT);
    URL URLFILEINT = getClass().getResource("/fileint_icon.png");
    assert URLFILEINT != null;
    Icon fileINTCO = new ImageIcon(URLFILEINT);
    verifyFile.setIcon(fileINTCO);
    verifyFile.addActionListener(this);

    run.callAPI();
    String json = run.callAPI();
    String versionInfo = ("<html><p>Your Version: " + VersionInfo.VERSION + "<br>Latest Release: "
        + (JSONParser.parseElement("latest_release", json) == null
            || java.util.Objects.equals(main.advisors.JSONParser.parseElement("latest_release", json), "")
                ? "Unavaliable"
                : JSONParser.parseElement("latest_release", json))
        + "<br>"
        + "Latest Patch: "
        + (JSONParser.parseElement("latest_patch", json) == null
            || java.util.Objects.equals(main.advisors.JSONParser.parseElement("latest_patch", json), "")
                ? "Unavaliable"
                : JSONParser.parseElement("latest_patch", json))
        + "<br>" + "Latest Beta: "
        + (JSONParser.parseElement("latest_beta", json) == null
            || java.util.Objects.equals(main.advisors.JSONParser.parseElement("latest_beta", json), "")
                ? "Unavaliable"
                : JSONParser.parseElement("latest_beta", json))
        + "</p></html>");

    information = new JLabel(versionInfo);
    information.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    information.setFont(information.getFont().deriveFont(information.getFont().getStyle() | Font.ITALIC));

    clearCache = new JButton("Clear Cache");
    clearCache.setAlignmentX(Component.CENTER_ALIGNMENT);
    URL URLCLEARCACHE = getClass().getResource("/trashcan_icon.png");
    assert URLCLEARCACHE != null;
    Icon trashcanCO = new ImageIcon(URLCLEARCACHE);
    clearCache.setIcon(trashcanCO);
    clearCache.addActionListener(this);

    clearLogs = new JButton("Clear Logs");
    clearLogs.setAlignmentX(Component.CENTER_ALIGNMENT);
    clearLogs.setIcon(trashcanCO);
    clearLogs.addActionListener(this);

    resetProperties = new JButton("Reset Properties");
    resetProperties.setAlignmentX(Component.CENTER_ALIGNMENT);
    URL URLRESETPROP = getClass().getResource("/reset_icon.png");
    assert URLRESETPROP != null;
    Icon resetPROPCO = new ImageIcon(URLRESETPROP);
    resetProperties.setIcon(resetPROPCO);
    resetProperties.addActionListener(this);

    panel.add(title);
    panel.add(Box.createHorizontalStrut(15));
    panel.add(verifyFile);
    panel.add(clearCache);
    panel.add(resetProperties);
    panel.add(clearLogs);
    panel.add(Box.createHorizontalStrut(10));
    panel.add(Box.createHorizontalStrut(30));
    panel.add(information);

    URL icon = getClass().getResource("/information_icon.png");
    assert icon != null;
    ImageIcon imageIcon = new ImageIcon(icon);
    frame = new JFrame("Music Player | Settings");
    frame.setSize(500, 400);
    frame.setResizable(false);
    frame.setIconImage(imageIcon.getImage());
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(panel);
  }

  @Override
  public void run() {
    frame.setVisible(true);
    frame.pack();
  }

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    new SettingsWindow().run();
  }

  /**
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(verifyFile)) {
      try {
        String end = run.veriyFile();
        Thread.sleep(600);
        if (end.equals("1")) {
          new ErrorMessage("File Integrity failed. Please consider redownloading");
        } else {
          new OKWindow("File Integrity Passed");
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } else if (e.getSource().equals(clearCache)) {
      try {
        if (Cache.cleanCache()) {
          new OKWindow("Cache Cleared");
        } else {
          new ErrorMessage("Cannot Clear Cache at this moment");
        }
      } catch (Exception e1) {
        e1.printStackTrace();
      }
    } else if (e.getSource().equals(resetProperties)) {
      if (PropertiesReader.reset()) {
        new OKWindow("Properties Reset");
      } else {
        new ErrorMessage("Reset failed");
      }
    } else if (e.getSource().equals(clearLogs)) {
      if(Logger.clear()) {
        new OKWindow("Logs Cleared");
      } else {
        new ErrorMessage("Cannot Clear Logs at this moment");
      }
    }

  }

  /**
   * @return Object
   */
  public static Object getInstance() {
    return frame;
  }

}
