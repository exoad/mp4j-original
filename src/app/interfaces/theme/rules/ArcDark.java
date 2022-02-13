package app.interfaces.theme.rules;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

import java.awt.Color;

import javax.swing.*;
/**
 * @author Jack Meng
 */
public class ArcDark implements Refresh {

  @Override
  public void refresh(java.awt.Window frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
    try {
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (NullPointerException e) {
      // do nothing
      e.addSuppressed(e);
    }
    frame.pack();
  }

  public String toString() {
    return "arcdark";
  }

  @Override
  public Color getBorderColor() {
    return new Color(68, 147, 212);
  }
}
