package project.test;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

import java.util.List;
import java.util.ArrayList;

public class AlterableSineWave extends JPanel implements Runnable {
  private JFrame f;
  private transient Thread worker;
  private ArrayList<Integer> pointHeights;
  private final int WIDTH = 800, HEIGHT = 500;
  
  public AlterableSineWave() {
    setPreferredSize(new Dimension(WIDTH, HEIGHT));

    f = new JFrame("AlterableSineWave");
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    f.getContentPane().add(this);

    pointHeights = new ArrayList<>();

    worker = new Thread(() -> {
      while (true) {
        generate();
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        repaint();
      }
    });
    worker.start();
  }

  private static List<Integer> normalize(List<Integer> e) {
    List<Integer> temp = new ArrayList<>();
    for (int i = 0; i < e.size(); i++) {
      int curr = e.get(i);
      if (curr > 250) {
        curr += 500;
      } else {
        curr += 250;
      }
      temp.add(curr);
    }
    return temp;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(Color.BLUE);
    
    List<Integer>x = normalize(pointHeights);

    // using a quadcurve2d make a sine wave with variable amplitude
    QuadCurve2D.Double curve = new QuadCurve2D.Double();
    curve.setCurve(0, HEIGHT / 2, 0, HEIGHT / 2, WIDTH, HEIGHT);
    g2.draw(curve);
    for (int i = 1; i < x.size(); i++) {
      curve.setCurve(i - 1, x.get(i - 1), i, x.get(i), i, x.get(i));
      g2.draw(curve);
    }
    // close the curve

    
  }

  private void generate() {
    pointHeights = new ArrayList<>();
    for(int i = 0; i < WIDTH / 2; i++) {
      // generate a number from 100 to -100 using math.random
      int y = (int) (Math.random() * 200 - 100);
      pointHeights.add(y);
    }
  }

  @Override
  public void run() {
    f.pack();
    f.setVisible(true);
  }

  public static void main(String... args) {
    new AlterableSineWave().run();
  }
}
