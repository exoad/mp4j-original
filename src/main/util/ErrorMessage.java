package main.util;

import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class ErrorMessage implements ActionListener {
  private JButton okButton;
  private JFrame frame;
  private boolean lightMode = false;

  public ErrorMessage(String message) {
    if (!lightMode)
      FlatDarkLaf.setup();
    else
      FlatLightLaf.setup();
    JPanel panel = new JPanel();
    JLabel label = new JLabel("<html><p>" + message + "</p></html>");
    okButton = new JButton("OK");
    okButton.addActionListener(this);
    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setFont(label.getFont().deriveFont(label.getFont().getSize() * 1.5f));

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(label);
    panel.add(okButton);
    URL url = getClass().getResource("/error_frame_icon.png");
    ImageIcon icon = new ImageIcon(url);
    frame = new JFrame("Error: " + message);
    frame.setIconImage(icon.getImage());
    frame.add(panel);
    frame.setSize(300, 100);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == okButton) {
      frame.dispose();
    }
  }

}
