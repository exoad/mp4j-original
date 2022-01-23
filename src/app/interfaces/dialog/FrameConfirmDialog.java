package app.interfaces.dialog;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import app.interfaces.event.RoundFrame;

public class FrameConfirmDialog implements ActionListener {
  private final JFrame frame;
  private final JFrame frameToListen;
  private final JButton okButton, cancelButton;
  private final Runnable after;

  public FrameConfirmDialog(String message, JFrame parent, Runnable second) {
    after = second;
    frameToListen = parent;
    javax.swing.JLabel label = new javax.swing.JLabel("<html><center><p>" + message + "</p></center></html>");
    label.setFont(label.getFont().deriveFont(label.getFont().getSize() * 1.5f));
    label.setAlignmentX(Component.CENTER_ALIGNMENT);

    okButton = new JButton("Yes");
    okButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    okButton.addActionListener(this);

    cancelButton = new JButton("No");
    cancelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    cancelButton.addActionListener(this);

    javax.swing.JPanel pane = new javax.swing.JPanel();
    pane.setPreferredSize(new Dimension(300, 150));
    pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

    pane.add(label);
    pane.add(okButton);
    pane.add(cancelButton);

    frame = new JFrame("Confirm Action");
    frame.setIconImage(parent.getIconImage());
    frame.add(pane);
    frame.setUndecorated(true);
    frame.setSize(300, 150);
    frame.addComponentListener(new RoundFrame(frame));
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setResizable(false);
    frame.setVisible(true);
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource().equals(okButton)) {
      frame.dispose();
      frameToListen.dispose();
      after.run();
    } else if(e.getSource().equals(cancelButton)) {
      frame.dispose();
    }
    
  }
  
}
