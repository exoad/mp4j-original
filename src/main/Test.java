package main;

import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.JFrame;

import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.net.URL;
import java.awt.Component;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test implements Runnable, ActionListener {
  protected JPanel mp;
  protected JFrame frame;
  protected JButton button;
  protected JLabel main, footer_notice;

  Test() {
    URL frame_icon = getClass().getResource("assets/frame-icon.png/");
    ImageIcon frame_ico = new ImageIcon(frame_icon);

    frame = new JFrame("Music Player Java - v1.0");
    frame.setIconImage(frame_ico.getImage());
    frame.setResizable(false);

    footer_notice = new JLabel(
        "<html><body><strong><u>Supported File Types : .mp3 & .wav</u></strong><br><center>Place files in folder: <code>/musicML/</code></center></body></html><br>");

    footer_notice.setFont(new Font("Courier", Font.PLAIN, 13));
    footer_notice.setAlignmentX(Component.CENTER_ALIGNMENT);
    footer_notice.setAlignmentY(Component.BOTTOM_ALIGNMENT);

    mp = new JPanel();
    mp.add(footer_notice);
    mp.setPreferredSize(new DimensionUIResource(450, 200));

    frame.add(mp);

  }

  public static void main(String[] args) {
    File f = new File("musicML");
    if (!f.exists())
      f.mkdir();
    new Test().run();
  }

  @Override
  public void run() {
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }
}