package app.interfaces.theme.rules;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

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
}
