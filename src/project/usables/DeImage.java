package project.usables;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics2D;


public class DeImage {
  public static BufferedImage imagetoBI(Image image) {
    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D big = bi.createGraphics();
    big.drawImage(image, 0, 0, null);
    big.dispose();
    return bi;
  }

  public static ImageIcon resizeImage(ImageIcon image, int width, int height) {
    Image img = image.getImage();
    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
  }
}
