package main.util;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.net.URL;

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

public class SettingsWindow implements Runnable, ActionListener {
  public static JFrame frame;
  private JPanel panel;
  private JLabel title;
  private JButton changeMode;
  private WelcomeWindow welcomeWindow;

  public SettingsWindow(WelcomeWindow something) {
    FlatDarkLaf.setup();
    welcomeWindow = something;

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

    panel.add(title);
    panel.add(changeMode);
    panel.add(Box.createHorizontalStrut(10));

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

  public SettingsWindow() {
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

    panel.add(title);
    panel.add(changeMode);
    panel.add(Box.createHorizontalStrut(10));

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

  public static void main(String[] args) {
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
