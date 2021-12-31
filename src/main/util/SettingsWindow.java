package main.util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.net.URL;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Component;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import main.advisors.CXX;
import main.advisors.JSONParser;

public class SettingsWindow implements Runnable, ActionListener {
  public static JFrame frame;
  private JPanel panel;
  private JLabel title, information;
  private JButton changeMode;
  private CXX run = new CXX();

  public SettingsWindow(WelcomeWindow something) throws IOException {
    FlatDarkLaf.setup();
    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setPreferredSize(new Dimension(500, 600));

    title = new JLabel("Settings");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(title.getFont().deriveFont(title.getFont().getSize() * 1.5f));

    changeMode = new JButton("Change to Light Mode");
    changeMode.setAlignmentX(Component.CENTER_ALIGNMENT);
    URL changeModeICON = getClass().getResource("/colormode_icon.png");
    Icon changeModeICO = new ImageIcon(changeModeICON);
    changeMode.setIcon(changeModeICO);
    changeMode.addActionListener(this);
    run.callAPI();
    String json = run.callAPI();
    String versionInfo = ("<html><p>Latest Release: " + JSONParser.parseElement("latest_release", json) + "<br>"
        + "Latest Patch: " + JSONParser.parseElement("latest_patch", json) + "<br>" + "Latest Beta: "
        + JSONParser.parseElement("latest_beta", json) + "</p></html>");

    information = new JLabel(versionInfo);
    information.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    information.setFont(information.getFont().deriveFont(information.getFont().getStyle() | Font.ITALIC));

    panel.add(title);
    panel.add(changeMode);
    panel.add(Box.createHorizontalStrut(10));
    panel.add(Box.createHorizontalStrut(10));
    panel.add(information);

    URL icon = getClass().getResource("/information_icon.png");
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
    FlatDarkLaf.setup();

    panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setPreferredSize(new Dimension(500, 600));

    title = new JLabel("Settings");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setFont(title.getFont().deriveFont(title.getFont().getSize() * 1.5f));

    changeMode = new JButton("Change to Light Mode");
    changeMode.setAlignmentX(Component.CENTER_ALIGNMENT);
    URL changeModeICON = getClass().getResource("/colormode_icon.png");
    Icon changeModeICO = new ImageIcon(changeModeICON);
    changeMode.setIcon(changeModeICO);
    changeMode.addActionListener(this);

    run.callAPI();
    String json = run.callAPI();
    String versionInfo = ("<html><p>Latest Release: " + JSONParser.parseElement("latest_release", json) + "<br>"
        + "Latest Patch: " + JSONParser.parseElement("latest_patch", json) + "<br>" + "Latest Beta: "
        + JSONParser.parseElement("latest_beta", json) + "</p></html>");

    information = new JLabel(versionInfo);
    information.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
    information.setFont(information.getFont().deriveFont(information.getFont().getStyle() | Font.ITALIC));

    panel.add(title);
    panel.add(changeMode);
    panel.add(Box.createHorizontalStrut(10));
    panel.add(Box.createHorizontalStrut(30));
    panel.add(information);

    URL icon = getClass().getResource("/information_icon.png");
    ImageIcon imageIcon = new ImageIcon(icon);
    frame = new JFrame("Music Player | Settings");
    frame.setSize(500, 600);
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

  public static void main(String[] args) throws IOException {
    new SettingsWindow().run();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == changeMode) {

      try {
        UIManager.setLookAndFeel(new FlatLightLaf());
        frame.dispose();
        new SettingsWindow().run();

      } catch (Exception e1) {
        e1.printStackTrace();
      }
    }

  }

  public static Object getInstance() {
    return frame;
  }

}
