package app.interfaces.theme.rules;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

import javax.swing.*;
import java.awt.*;

public class Nord implements Refresh {

  @Override
  public void refresh(java.awt.Window frame) {
    try {
      UIManager.setLookAndFeel(new FlatNordIJTheme());
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (javax.swing.UnsupportedLookAndFeelException | NullPointerException e ) {
      // do nothing
      e.addSuppressed(e);
    }
    frame.pack();
  }

  public String toString() {
    return "nord";
  }

  @Override
  public Color getBorderColor() {
    return new Color(236, 235, 241);
  }
}
