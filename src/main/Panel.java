package main;

import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JFrame;

import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.net.URL;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel implements Runnable, ActionListener {
  protected JPanel bp, mainPanel;
  private static JFrame frame;
  protected JButton play_btn, pause_btn;
  protected JLabel main, header_notice;

  Panel() {
    URL frame_icon = getClass().getResource("assets/frame-icon.png/");
    URL play_icon = getClass().getResource("assets/play_button.png");
    URL pause_icon = getClass().getResource("assets/pause_button.png");

    Icon pause_button_ico = new ImageIcon(pause_icon);
    Icon play_button_ico = new ImageIcon(play_icon);
    ImageIcon frame_ico = new ImageIcon(frame_icon);

    frame = new JFrame("Music Player Java - v1.0");
    frame.setIconImage(frame_ico.getImage());
    frame.setResizable(false);

    play_btn = new JButton(play_button_ico);
    play_btn.addActionListener(this);

    pause_btn = new JButton(pause_button_ico);
    pause_btn.addActionListener(this);

    header_notice = new JLabel(
        "<html><body><strong><u>Supported File Types : .mp3 & .wav</u></strong><br><center>Place files in folder: <code>/musicML/</code></center></body></html><br>");

    header_notice.setFont(new Font("Courier", Font.PLAIN, 13));
    header_notice.setAlignmentX(Component.CENTER_ALIGNMENT);
    header_notice.setAlignmentY(Component.BOTTOM_ALIGNMENT);

    

    bp = new JPanel();
    bp.add("1", play_btn);
    bp.add("2", pause_btn);

    bp.setPreferredSize(new Dimension(450, 200));

    mainPanel = new JPanel(new CardLayout());
    mainPanel.add(bp);

    frame.add(mainPanel);

  }
  public static void main(String[] args) throws Exception {
    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    File f = new File("musicML");
    if (!f.exists())
      f.mkdir();
    new Panel().run();
  }



@Override
public void actionPerformed(ActionEvent e) {
  // TODO Auto-generated method stub
  
}

@Override
public void run() {
  // TODO Auto-generated method stub
  
}}
