package test;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class AudioPartitionSlider {
  public static class PartitionUI extends BasicSliderUI {
    
    public PartitionUI(JSlider s) {
      super(s);
    }
    @Override
    public void paintThumb(Graphics g) {
      super.paintThumb(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(
          RenderingHints.KEY_ANTIALIASING,
          RenderingHints.VALUE_ANTIALIAS_ON);
      // draw the thumb image at the correct location
      Icon icon = new ImageIcon(getClass().getResource("/icons/others/slider_cursor.png"));
          
    }
  }
  private long length;
  private long current;
  private JSlider slider;
  private JFrame frame;
  private JPanel panel;
  public AudioPartitionSlider(long length, long current) {
    this.length = length;
    this.current = current;

    slider = new JSlider(0, (int) length);
    slider.setValue((int) current);
    slider.setMajorTickSpacing((int) length);
    slider.setMinorTickSpacing((int) length / 10);
    slider.setPaintTicks(true);
    slider.setPaintLabels(true);
    slider.setPaintTrack(true);
    slider.setUI(new PartitionUI(slider));

    

    panel = new JPanel();
    panel.add(slider);


    frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(new Dimension(500, 500));
    frame.setVisible(true);
    frame.add(panel);

    frame.setVisible(true);
    frame.pack();
  }



  public static void main(String... args) {
    new AudioPartitionSlider(1000, 30);
  }
}
