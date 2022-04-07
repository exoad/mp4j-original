package project.components;

import java.awt.Dimension;

import project.constants.Size;
import project.usables.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class BigContainer implements Scheduled {
  protected JFrame bigFrame;

  public BigContainer(JPanel parentJPanel) {
    bigFrame = new JFrame("~ Mp4J");
    bigFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    bigFrame.setIconImage(new ImageIcon(getClass().getResource("/icons/others/frame-icon.png")).getImage());

    bigFrame.setPreferredSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    bigFrame.setLocationRelativeTo(null);
    bigFrame.add(parentJPanel);
    bigFrame.pack();
  }

  @Override
  public void run() {
    bigFrame.setVisible(true);
  }
}
