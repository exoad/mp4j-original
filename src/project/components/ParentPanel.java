package project.components;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;

import project.constants.*;

import java.util.Map;

public class ParentPanel extends JPanel {
  public ParentPanel(Map<JPanel, String> panels) {
    setPreferredSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    setLayout(new BorderLayout());
    setOpaque(true);
    setBackground(Color.GRAY);
    for (Map.Entry<JPanel, String> entry : panels.entrySet()) {
      add(entry.getKey(), entry.getValue());
    }
  }
}
