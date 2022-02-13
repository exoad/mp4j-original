package app.interfaces.theme.rules;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.FlatDarkLaf;

import java.awt.Color;

import javax.swing.*;

public class RegularDark implements Refresh {
  @Override
  public void refresh(java.awt.Window frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatDarkLaf());
    try {
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (NullPointerException e) {
      // do nothing
      e.addSuppressed(e);
    }
    frame.pack();
  }

  public String toString() {
    return "regulardark";
  }

  @Override
  public Color getBorderColor() {
    return new Color(137, 133, 112);
  }
}
