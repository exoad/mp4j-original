package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class JComboTest implements ItemListener {

  public JComboTest() {
    JFrame frame = new JFrame("JComboTest");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new FlowLayout());
    JComboBox<String> combo = new JComboBox<>();
    combo.addItem("RegularLight");
    combo.addItem("RegularDark");
    combo.addItem("ArcDark");
    combo.addItem("ArcLight");
    combo.addItemListener(this);
    frame.add(combo);
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    new JComboTest();

  }

  @Override
  public void itemStateChanged(ItemEvent e) {
  }
  
}
