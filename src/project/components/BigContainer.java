package project.components;

import project.constants.Size;
import project.usables.DeImage;
import project.usables.Scheduled;
import strict.RuntimeConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BigContainer implements Scheduled {
  protected JFrame bigFrame;

  public BigContainer(JPanel parentJPanel) {
    bigFrame = new JFrame("Halcyon ~ Mp4J");
    bigFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    bigFrame.setIconImage(DeImage.resizeImage(RuntimeConstant.runtimeRD.getAppLogo(), 64, 64).getImage());
    bigFrame.setResizable(true);
    bigFrame.setAutoRequestFocus(true);
    bigFrame.setPreferredSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    bigFrame.setMinimumSize(new Dimension(Size.MIN_WIDTH, Size.MIN_HEIGHT));
    bigFrame.setMaximumSize(new Dimension(Size.MAX_WIDTH, Size.MAX_HEIGHT));
    bigFrame.setResizable(false);
    bigFrame.setSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    bigFrame.setLocationRelativeTo(null);
    bigFrame.getContentPane().add(parentJPanel);
    bigFrame.pack();
    bigFrame.addComponentListener(new ComponentAdapter() {

      @Override
      public void componentResized(ComponentEvent e) {
        double w = bigFrame.getSize().getWidth();
        double h = bigFrame.getSize().getHeight();
        if (w > Size.MAX_WIDTH) {
          bigFrame.setSize(new Dimension(Size.MAX_WIDTH, (int) h));
          bigFrame.repaint();
          bigFrame.revalidate();
        }
        if (h > Size.MAX_HEIGHT) {
          bigFrame.setSize(new Dimension((int) w, Size.MAX_HEIGHT));
          bigFrame.repaint();
          bigFrame.revalidate();
        }

        super.componentResized(e);
      }

    });
  }

  
  /** 
   * @return JFrame
   */
  public JFrame getBigFrame() {
    return bigFrame;
  }

  @Override
  public void run() {
    bigFrame.setVisible(true);
  }
}
