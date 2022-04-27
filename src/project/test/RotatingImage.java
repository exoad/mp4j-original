package project.test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

public class RotatingImage extends JPanel {
  private JFrame f;
  private JPanel p;
  private double currRot = 0.0;

  public RotatingImage() {
    f = new JFrame();
    f.setPreferredSize(new Dimension(500, 500));
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(500, 500));
    f.getContentPane().add(this);
  }

  public void run() {
    f.pack();
    f.setVisible(true);
  }

  private static GraphicsConfiguration getDefaultConfiguration() {
    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    GraphicsDevice gd = ge.getDefaultScreenDevice();
    return gd.getDefaultConfiguration();
  }

  public static BufferedImage rotate(BufferedImage image, double angle) {
    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
    int w = image.getWidth(), h = image.getHeight();
    int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
    GraphicsConfiguration gc = getDefaultConfiguration();
    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
    Graphics2D g = result.createGraphics();
    g.translate((neww - w) / 2, (newh - h) / 2);
    g.rotate(angle, w / 2, h / 2);
    g.drawRenderedImage(image, null);
    g.dispose();
    return result;
  }

  public static BufferedImage imagetoBI(Image image) {
    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D big = bi.createGraphics();
    big.drawImage(image, 0, 0, null);
    big.dispose();
    return bi;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    BufferedImage i = imagetoBI(new ImageIcon("resource/newrsc/disk.png").getImage());
    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    AffineTransform at = new AffineTransform();
    at.rotate(currRot, i.getWidth() / 2, i.getHeight() / 2);
    currRot += Math.PI / 120;
    g2.transform(at);
    g2.drawImage(i, 0, 0, i.getWidth(), i.getHeight(), null);
  }

  public static void main(String... args) {
    RotatingImage r = new RotatingImage();
    r.run();
    new Thread(() -> {
      while (true) {
        r.repaint();
        try {
          Thread.sleep(20);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  
  }
}
