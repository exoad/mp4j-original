package project.test;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

import static java.lang.Math.*;

public class VerticalBarMovement extends JPanel {
  private int[] bars = { 20, 20, 20 };
  private JFrame f;
  private Random r = new Random();

  public VerticalBarMovement() {
    f = new JFrame("Vertical Bar Movement");

    setPreferredSize(new Dimension(500, 500));

    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setPreferredSize(new Dimension(500, 500));

    f.getContentPane().add(this);
  }

  public void generateNew() {
    bars = new int[3];
    for (int i = 0; i < 3; i++) {
      bars[i] = r.nextInt(400);
    }

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHints(rh);
    g2.setColor(Color.GREEN);
    generateNew();
    int widthPerBar = (int) getPreferredSize().getWidth() / bars.length;
    for (int i = 0, x = 0; i < bars.length && x < 400; i++, x += 100) {
      System.out.println(500 / bars[i]);
      g.fillRoundRect(x, 450 - bars[i], 80, bars[i], 0, 50);
    }
  }

  public synchronized void run() {
    f.pack();
    f.setVisible(true);
    new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(300);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        repaint();

      }
    }).start();
  }

  public static void main(String[] args) {
    VerticalBarMovement vbm = new VerticalBarMovement();
    vbm.run();
  }

}
