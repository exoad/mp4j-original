package main.interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LicenseWindow implements Runnable, ActionListener {
  private final JFrame frame;
  private final JButton agree, disagree;
  public boolean proceed = false;

  public LicenseWindow(int firstTime) throws IOException {

    URL url = getClass().getResource("/license_icon.png");
    assert url != null;
    ImageIcon icon = new ImageIcon(url);
    frame = new JFrame("Music Player | License");
    frame.setIconImage(icon.getImage());
    frame.setSize(500, 600);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

    javax.swing.JLabel label = new javax.swing.JLabel("Music Player - License");
    label.setIcon(icon);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);

    javax.swing.JTextPane textArea = new javax.swing.JTextPane();
    textArea.setEditable(false);

    BufferedReader br = new BufferedReader(
        new InputStreamReader(java.util.Objects.requireNonNull(getClass().getResource("/license.txt")).openStream()));
    StringBuilder license = new StringBuilder();
    try {
      String line = br.readLine();
      while (line != null) {
        license.append(line).append("\n");
        line = br.readLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    textArea.setText(license.toString());
    StyledDocument doc = textArea.getStyledDocument();
    SimpleAttributeSet center = new SimpleAttributeSet();
    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
    doc.setParagraphAttributes(0, doc.getLength(), center, false);
    SimpleAttributeSet bold = new SimpleAttributeSet();
    StyleConstants.setBold(bold, true);
    doc.setCharacterAttributes(0, doc.getLength(), bold, false);
    textArea.setSize(new Dimension(400, 400));
    textArea.setFont(textArea.getFont().deriveFont(textArea.getFont().getSize() * 1f));
    textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(textArea);
    scrollPane.setBounds(0, 0, 150, 500);
    scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
    scrollPane.setPreferredSize(new Dimension(450, 500));

    agree = new JButton("Agree");
    disagree = new JButton("Disagree");

    if (firstTime == -1) {
      JPanel panel = new JPanel();
      panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
      
      agree.addActionListener(this);
      agree.setAlignmentX(Component.CENTER_ALIGNMENT);

      
      disagree.addActionListener(this);
      disagree.setAlignmentX(Component.CENTER_ALIGNMENT);

      panel.add(agree);
      panel.add(disagree);

      frame.add(label);
      frame.add(Box.createHorizontalStrut(2));
      frame.add(Box.createVerticalStrut(7));
    }
    frame.add(scrollPane);

  }

  @Override
  public void run() {
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    new LicenseWindow(1).run();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource() == agree) {
      frame.dispose();
      proceed = true;
    } else if(e.getSource() == disagree) {
      frame.dispose();
      proceed = false;
    }
  }

  public boolean isVisible() {
    return frame.isVisible();
  }
}
