package app.interfaces.theme.rules;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;

import javax.swing.*;
import java.awt.*;

public class Vuesion implements Refresh {

  @Override
  public void refresh(java.awt.Window frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatVuesionIJTheme());

    try {
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (NullPointerException e) {
      // do nothing
      e.addSuppressed(e);
    }
    frame.pack();
    frame.revalidate();
  }

  public String toString() {
    return "vuesion";
  }

  @Override
  public Color getBorderColor() {
    return new Color(0, 202, 114);
  }
}
