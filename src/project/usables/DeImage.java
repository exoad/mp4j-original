package project.usables;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics2D;

/**
 * This is a class that modifies images that are fed to it.
 * It is primarily used to handle resources that are in image form.
 * 
 * @author Jack Meng
 */
public class DeImage {
  private DeImage() {
  }

  /**
   * Turns an Image read raw from a stream to be enwrapped by a BufferedImage
   * object.
   * 
   * @param image An Image from a stream.
   * @return BufferedImage A modified image that has been convertted and held in a
   *         BufferedImage object.
   */
  public static BufferedImage imagetoBI(Image image) {
    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D big = bi.createGraphics();
    big.drawImage(image, 0, 0, null);
    big.dispose();
    return bi;
  }

  /**
   * @param image  An ImageIcon from a stream.
   * @param width  The width to scale down to
   * @param height The height to scale down to
   * @return ImageIcon A modified image that has been scaled to width and height.
   */
  public static ImageIcon resizeImage(ImageIcon image, int width, int height) {
    Image img = image.getImage();
    Image newImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    return new ImageIcon(newImg);
  }
}
