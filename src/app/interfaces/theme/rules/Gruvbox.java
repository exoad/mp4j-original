package app.interfaces.theme.rules;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme;

public class Gruvbox implements Refresh {
  @Override
  public void refresh(java.awt.Window frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatGruvboxDarkMediumIJTheme());
    try {
      SwingUtilities.updateComponentTreeUI(frame);
    } catch (Exception e) {
      // do nothing
    }
    frame.pack();
  }

  public String toString() {
    return "gruvbox";
  }
}
