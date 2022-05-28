package project.test;

import javax.swing.*;
import java.awt.*;

public class ColoredText {
  private JFrame f;
  private JPanel p;
  private JEditorPane jep;

  public ColoredText() {
    f = new JFrame("colored text");
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setPreferredSize(new Dimension(400, 400));

    p = new JPanel();
    p.setPreferredSize(new Dimension(f.getPreferredSize()));

    jep = new JEditorPane();
    jep.setPreferredSize(new Dimension(f.getPreferredSize()));
    jep.setContentType("text/html");
    jep.setText("<html><body><b><font color=\"#FF0000\">Hello</font></b> <font color=\"#00FF00\">World</font></body></html>");

    p.add(jep);
    f.getContentPane().add(p);
    f.pack();
    f.setVisible(true);
  }

  public static void main(String[] args) {
    new ColoredText();
  }
}
