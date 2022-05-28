package project;

import java.awt.Dimension;
import java.io.File;

import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class Utils {
  private Utils() {
  }

  
  /** 
   * @param f
   * @return String
   */
  public static String getExtension(File f) {
    String ext = null;
    String s = f.getName();
    int i = s.lastIndexOf('.');

    if (i > 0 && i < s.length() - 1) {
      ext = s.substring(i + 1).toLowerCase();
    }
    return ext;
  }

  public static long bytesToMegabytes(long bytes) {
    return bytes / 1024 / 1024;
  }

  /**
   * Extra Note: this was originally from the SubVolumeView class.
   * @return JSlider returns a random slider for testing
   */
  public static JSlider getRandomSlider() {
    JSlider slider = new JSlider(SwingConstants.VERTICAL, 0, 100, 50);
    slider.setEnabled(false);
    slider.setPreferredSize(new Dimension(20, 180));
    return slider;
  }
}
