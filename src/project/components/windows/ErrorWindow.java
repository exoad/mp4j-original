package project.components.windows;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.WindowConstants;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorWindow {
  private JFrame frame;
  private JPanel errorPanel;
  private JTextArea errorText;
  private JButton okButton;

  public ErrorWindow(String message) {
    frame = new JFrame("Error! :(");
    frame.setSize(new Dimension(500, 300));
    frame.setPreferredSize(new Dimension(500, 300));
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);

    errorPanel = new JPanel();
    errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
    errorPanel.setPreferredSize(frame.getPreferredSize());

    errorText = new JTextArea(message);
    errorText.setEditable(false);
    errorText.setLineWrap(true);
    errorText.setFont(errorText.getFont().deriveFont(13f));
    errorText.setAutoscrolls(true);
    errorText.setOpaque(true);
    errorText.setBackground(Color.WHITE);
    errorText.setForeground(Color.BLACK);
    errorText.setBorder(BorderFactory.createBevelBorder(1));
    errorText.setWrapStyleWord(true);
    errorText.setMargin(new java.awt.Insets(0, 30, 0, 30));
    errorText.setAlignmentX(Component.CENTER_ALIGNMENT);

    okButton = new JButton("OK");
    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    okButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
      }
    });
    errorPanel.add(errorText);
    errorPanel.add(okButton);
    frame.add(errorPanel);
    frame.pack();
    frame.setAlwaysOnTop(true);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
    new ErrorWindow("This is a test");
  }
}
