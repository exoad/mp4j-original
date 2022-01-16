package app.interfaces;

import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;

import app.telemetry.Logger;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ErrorMessage implements ActionListener {
  private final JButton okButton, pathBtn;
  private final JFrame frame;
  private final String path;

  public ErrorMessage(String message) {
    this.path = Logger.log(message);
    JPanel panel = new JPanel();

    JLabel label = new JLabel("<html><p>An error occured click below to view the error<br></p></html>");

    okButton = new JButton("OK");
    okButton.addActionListener(this);
    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setFont(label.getFont().deriveFont(label.getFont().getSize() * 1f));

    pathBtn = new JButton("View Error");
    pathBtn.addActionListener(this);
    pathBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    panel.add(label);
    panel.add(okButton);
    panel.add(pathBtn);
    JScrollPane jsp = new JScrollPane(panel);
    URL url = getClass().getResource("/error_frame_icon.png");
    assert url != null;
    ImageIcon icon = new ImageIcon(url);
    frame = new JFrame("Error!");
    frame.setIconImage(icon.getImage());
    frame.add(jsp);
    frame.setSize(400, 150);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setVisible(true);
  }

  
  /** 
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(okButton)) {
      frame.dispose();
    } else if(e.getSource().equals(pathBtn)) {
      try {
        java.awt.Desktop.getDesktop().open(new File(path));
      } catch (IOException e1) {
        e1.printStackTrace();
      }
    }
  }

}
