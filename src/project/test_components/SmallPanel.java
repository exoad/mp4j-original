package project.test_components;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class SmallPanel extends JPanel {
  public SmallPanel() {
    setPreferredSize(new Dimension(500, 500));
    setBackground(Color.BLACK);
  }
}
