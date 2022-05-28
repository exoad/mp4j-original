package project.test;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.awt.LinearGradientPaint;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageGradient extends JPanel implements Runnable {
  private JFrame f;

  public ImageGradient() {
    f = new JFrame("test");
    f.setSize(500, 500);
    f.setPreferredSize(new Dimension(500, 500));
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    setPreferredSize(new Dimension(500, 500));

    f.getContentPane().add(this);
    f.pack();
    setOpaque(true);
    setBackground(Color.BLACK);
    new Thread(() -> {
      repaint();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();

  }

  public static BufferedImage applyMask(BufferedImage sourceImage, BufferedImage maskImage, int method) {

    BufferedImage maskedImage = null;
    if (sourceImage != null) {

      int width = maskImage.getWidth();
      int height = maskImage.getHeight();

      maskedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
      Graphics2D mg = maskedImage.createGraphics();

      int x = (width - sourceImage.getWidth()) / 2;
      int y = (height - sourceImage.getHeight()) / 2;

      mg.drawImage(sourceImage, x, y, null);
      mg.setComposite(AlphaComposite.getInstance(method));

      mg.drawImage(maskImage, 0, 0, null);

      mg.dispose();
    }

    return maskedImage;

  }

  private static BufferedImage createGradient(BufferedImage img) {
    BufferedImage alphamask = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2d = alphamask.createGraphics();
    LinearGradientPaint lgp = new LinearGradientPaint(
        new Point(0, 0),
        new Point(alphamask.getWidth(), 0),
        new float[] { 0, 1 },
        new Color[] { new Color(0, 0, 0, 255), new Color(0, 0, 0, 0) });
    g2d.setPaint(lgp);
    g2d.fillRect(0, 0, alphamask.getWidth(), alphamask.getHeight());
    g2d.dispose();
    return applyMask(img, alphamask, AlphaComposite.DST_IN);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File("src/project/test/app-logo_test.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }
    BufferedImage newImage = createGradient(img);
    g.drawImage(newImage, 0, 0, null);
  }

  @Override
  public void run() {
    f.setVisible(true);
  }

  public static void main(String... args) {
    new ImageGradient().run();
  }
}
