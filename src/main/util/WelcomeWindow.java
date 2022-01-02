package main.util;

import javax.swing.JFrame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Component;
import java.net.URI;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Desktop;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class WelcomeWindow implements Runnable, ActionListener {
  private final JFrame frame;
  private final JButton openSelectFile;
  private final JButton github;
  private final JButton license;
  private final JButton settings;
  private final JButton documentation;
  public static String lastDir = "";

  public WelcomeWindow(String lastDir) {
    WelcomeWindow.lastDir = lastDir;
    
    URL frame_icon = getClass().getResource("/welcome_icon.png");
    assert frame_icon != null;
    ImageIcon frame_ico = new ImageIcon(frame_icon);
    javax.swing.JPanel panel = new javax.swing.JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    javax.swing.JLabel title = new javax.swing.JLabel("Music Player");
    title.setIcon(frame_ico);

    Font font = title.getFont();
    title.setFont(font.deriveFont(font.getStyle() | Font.BOLD));

    title.setFont(title.getFont().deriveFont(title.getFont().getSize() * 2.5f));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);

    javax.swing.JLabel description = new javax.swing.JLabel(
        "<html><div style='text-align: center;'><p>This is a very simple Music Player for you. You can check out the GitHub page and look at ongoing updates, and report bugs and issues. If you wanna play something go click on that button called \"Select File\". Currently only wav files are supported!</p></div></html>");
    description.setFont(description.getFont().deriveFont(description.getFont().getSize() * 1.1f));
    description.setAlignmentX(Component.CENTER_ALIGNMENT);

    javax.swing.JLabel version = new javax.swing.JLabel("<html><u>Version 1.0 | Made by Jack Meng</u><br></html>");
    version.setFont(version.getFont().deriveFont(version.getFont().getStyle() | Font.ITALIC));
    version.setAlignmentX(Component.CENTER_ALIGNMENT);

    URL file_icon = getClass().getResource("/file_select_folder_icon.png");
    assert file_icon != null;
    Icon ico = new ImageIcon(file_icon);
    openSelectFile = new JButton("Select File");
    openSelectFile.setIcon(ico);
    openSelectFile.addActionListener(this);
    openSelectFile.setAlignmentX(Component.CENTER_ALIGNMENT);

    URL github_icon = getClass().getResource("/github.png");
    assert github_icon != null;
    Icon git_ico = new ImageIcon(github_icon);
    github = new JButton("GitHub Repository");
    github.setIcon(git_ico);
    github.addActionListener(this);
    github.setAlignmentX(Component.CENTER_ALIGNMENT);

    URL license_icon = getClass().getResource("/license_icon.png");
    assert license_icon != null;
    Icon lic_ico = new ImageIcon(license_icon);
    license = new JButton("Read License");
    license.setIcon(lic_ico);
    license.addActionListener(this);
    license.setAlignmentX(Component.CENTER_ALIGNMENT);

    URL settings_icon = getClass().getResource("/information_icon.png");
    assert settings_icon != null;
    Icon set_ico = new ImageIcon(settings_icon);
    settings = new JButton("Settings");
    settings.setIcon(set_ico);
    settings.addActionListener(this);
    settings.setAlignmentX(Component.CENTER_ALIGNMENT);

    URL docs_icon = getClass().getResource("/documentation_icon.png");
    assert docs_icon != null;
    Icon doc_ico = new ImageIcon(docs_icon);
    documentation = new JButton("Documentation");
    documentation.setIcon(doc_ico);
    documentation.addActionListener(this);
    documentation.setAlignmentX(Component.CENTER_ALIGNMENT);

    panel.add(title);
    panel.add(version);
    panel.add(Box.createVerticalStrut(20));
    panel.add(description);
    panel.add(Box.createVerticalStrut(20));
    panel.add(openSelectFile);
    panel.add(Box.createVerticalStrut(8));
    panel.add(github);
    panel.add(Box.createVerticalStrut(8));
    panel.add(license);
    panel.add(Box.createVerticalStrut(8));
    panel.add(documentation);
    panel.add(Box.createVerticalStrut(8));
    panel.add(settings);
    panel.setPreferredSize(new Dimension(500, 340));

    frame = new JFrame("Music-Player v1.0 | Jack Meng | Welcome!");
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    frame.setIconImage(frame_ico.getImage());
    frame.setResizable(false);
    frame.add(panel);
  }

  @Override
  public void run() {
    frame.setVisible(true);
    frame.pack();
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    new WelcomeWindow(lastDir).run();
  }

  /**
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(openSelectFile)) {
      new SelectFileWindow(lastDir).run();
    } else if (e.getSource().equals(github)) {
      try {
        Desktop.getDesktop().browse(new URI("https://github.com/exoad/MusicPlayer"));
      } catch (java.io.IOException | java.net.URISyntaxException e1) {
        e1.printStackTrace();
        new ErrorMessage(java.util.Arrays.toString(e1.getStackTrace()));
      }
    } else if (e.getSource().equals(license)) {
      try {
        new main.util.LicenseWindow().run();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    } else if (e.getSource().equals(settings)) {
      try {
        new main.util.SettingsWindow().run();
      } catch (IOException ioe) {
        new ErrorMessage(java.util.Arrays.toString(ioe.getStackTrace()));
        ioe.printStackTrace();
      }
    } else if (e.getSource().equals(documentation)) {
      try {
        new main.util.DocumentationWindow().run();
      } catch (IOException ioe) {
        new ErrorMessage(java.util.Arrays.toString(ioe.getStackTrace()));
        ioe.printStackTrace();
      }
    }

  }
}
