package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatNordIJTheme;

public abstract class Nord implements Refresh {
  private Nord() {
  }

  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatNordIJTheme());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
