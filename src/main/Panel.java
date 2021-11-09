package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class Panel implements Runnable, ActionListener {
  protected JPanel bp, mainPanel;
  protected JButton play_btn, pause_btn;
  protected JLabel main, header_notice;

  Panel() {
    URL frame_icon = getClass().getResource("assets/frame-icon.png/");
    URL play_icon = getClass().getResource("assets/play_button.png");
    URL pause_icon = getClass().getResource("assets/pause_button.png");

    assert pause_icon != null;
    Icon pause_button_ico = new ImageIcon(pause_icon);
    assert play_icon != null;
    Icon play_button_ico = new ImageIcon(play_icon);
    assert frame_icon != null;
    ImageIcon frame_ico = new ImageIcon(frame_icon);

    JFrame frame = new JFrame("Music Player Java - v1.0");
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
