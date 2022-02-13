package app.interfaces.theme.rules;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

import java.awt.Color;

import javax.swing.*;

public class OneDark implements Refresh {

  @Override
  public void refresh(java.awt.Window frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
    try {
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (NullPointerException e) {
      // do nothing
      e.addSuppressed(e);
    }
    frame.pack();
  }

  public String toString() {
    return "onedark";
  }

  @Override
  public Color getBorderColor() {
    return new Color(220, 223, 228);
  }
}
