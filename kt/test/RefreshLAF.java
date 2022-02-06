package test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.formdev.flatlaf.*;
@Deprecated
public class RefreshLAF implements ActionListener, Runnable {
  JFrame frame;
  JPanel panel;
  JButton button;
  JLabel label;
  boolean isDarkMode = false;
  public RefreshLAF() {
    FlatLightLaf.setup();
    frame = new JFrame("test");
    panel = new JPanel();
    button = new JButton("Refresh");
    button.addActionListener(this);
    panel.add(button);
    label = new JLabel("Refresh LAF");
    panel.add(label);
    panel.setPreferredSize(new java.awt.Dimension(400, 400));



    frame.add(panel);
    frame.setSize(400, 400);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

  }

  public static void main(String[] args) {
    new RefreshLAF().run();
  }

  @Override
  public void run() {
    frame.pack();
  }

  @Override
  public void actionPerformed(ActionEvent e) {  
    try {
      if (isDarkMode) {
        UIManager.setLookAndFeel(new FlatDarkLaf());
      } else {
        UIManager.setLookAndFeel(new FlatLightLaf());
      }
      SwingUtilities.updateComponentTreeUI(frame);
      isDarkMode = !isDarkMode;
    } catch (UnsupportedLookAndFeelException e1) {
      e1.printStackTrace();
    }
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
    
  }
}
