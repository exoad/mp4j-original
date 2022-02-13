package test;

import javax.swing.*;
import java.awt.*;


public class FramePanelTester {
  private Dimension d = new Dimension(500, 250);
  public FramePanelTester() {
    JFrame f = new JFrame();
    f.setResizable(false);
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    JPanel bp = new JPanel();
    bp.setOpaque(true);
    bp.setPreferredSize(new Dimension(320, d.height / 3));
    bp.setBackground(Color.black);

    JPanel framePanel = new JPanel();
    framePanel.setBackground(Color.RED);
    framePanel.setOpaque(true);
    framePanel.setPreferredSize(new Dimension(320, (d.height /3)));
    JPanel sliderPanel = new JPanel();
    sliderPanel.setBackground(Color.BLUE);
    sliderPanel.setOpaque(true);
    sliderPanel.setPreferredSize(new Dimension(180, (int) d.getHeight()));

    JPanel bigPanel = new JPanel();
    // make bp on top of framePanel and sliderPanel to the side
    bigPanel.setLayout(new BorderLayout());

    bigPanel.add(framePanel, BorderLayout.SOUTH);
    bigPanel.add(bp, BorderLayout.CENTER);
    bigPanel.add(sliderPanel, BorderLayout.EAST);


    f.add(bigPanel);

    f.setVisible(true);
    f.pack();
  }

  public static void main(String[] args) {
    new FramePanelTester();
  }
}
