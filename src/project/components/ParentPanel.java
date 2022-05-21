package project.components;

import project.constants.Size;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ParentPanel extends JPanel {
  public ParentPanel(Map<JComponent, String> panels) {
    setPreferredSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    setLayout(new BorderLayout());
    setOpaque(true);
    setBackground(Color.GRAY);
    for (Map.Entry<JComponent, String> entry : panels.entrySet()) {
      add(entry.getKey(), entry.getValue());
    }

  }
}
