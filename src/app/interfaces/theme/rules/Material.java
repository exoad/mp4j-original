package app.interfaces.theme.rules;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialDarkerIJTheme;

public class Material implements Refresh {

  @Override
  public void refresh(java.awt.Window frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatMaterialDarkerIJTheme());
    try {
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (NullPointerException e) {
      // do nothing
      e.addSuppressed(e);
    }
    frame.pack();
  }

  public String toString() {
    return "material";
  }
}
