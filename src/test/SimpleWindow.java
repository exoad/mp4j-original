package test;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

public class SimpleWindow {
  public SimpleWindow(EventListener listener) {
    
    JFrame frame = new JFrame("Simple Window");
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    
    JButton button = new JButton("Click Me!");
    button.addActionListener((ActionListener) listener);
    panel.add(button);
    frame.add(panel);

    frame.setVisible(true);
    frame.pack();
  }
}
