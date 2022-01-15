package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

public abstract class ArcDark implements Refresh {
  private ArcDark() {
  }

  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
