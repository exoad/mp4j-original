package test;

import javax.swing.*;
import java.awt.*;

public class ProgessBarTester {
  public JFrame f;
  public JPanel e;
  public JLabel state;
  public JProgressBar bar;
  public ProgessBarTester() {
    f = new JFrame();
    e = new JPanel();
    state = new JLabel("Parsing...");
    bar = new JProgressBar();
    bar.setStringPainted(true);
    bar.setString("");
    bar.setValue(0);
    bar.setMaximum(100);
    bar.setMinimum(0);
    bar.setPreferredSize(new Dimension(200, 20));
    e.setPreferredSize(new Dimension(300, 50));
    e.add(state);
    e.add(bar);
    f.add(e);

    f.setVisible(true);
    f.pack();
  }

  public static void main(String... args) {
    new ProgessBarTester();
  }
}
