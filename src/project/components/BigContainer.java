package project.components;

import project.constants.ResourceDistributor;
import project.constants.Size;
import project.usables.DeImage;
import project.usables.Scheduled;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class BigContainer implements Scheduled {
  protected JFrame bigFrame;

  public BigContainer(JPanel parentJPanel) {
    bigFrame = new JFrame("Halcyon ~ Mp4J") {
      @Override
      public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.addRenderingHints(new HashMap<>() {
          {
            put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            put(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);

            put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
          }
        });
        super.paint(g2d);
      }
    };
    bigFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    bigFrame.setIconImage(DeImage.resizeImage(new ResourceDistributor().getAppLogo(), 64, 64).getImage());
    bigFrame.setResizable(true);
    bigFrame.setAutoRequestFocus(true);
    bigFrame.setPreferredSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    bigFrame.setMinimumSize(new Dimension(Size.MIN_WIDTH, Size.MIN_HEIGHT));
    bigFrame.setResizable(true);
    bigFrame.setMaximumSize(new Dimension(Size.MAX_WIDTH, Size.MAX_HEIGHT));
    bigFrame.setSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    bigFrame.setLocationRelativeTo(null);
    bigFrame.getContentPane().add(parentJPanel);
    bigFrame.pack();
  }

  public JFrame getBigFrame() {
    return bigFrame;
  }

  @Override
  public void run() {
    bigFrame.setVisible(true);
  }
}
