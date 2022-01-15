package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;

public abstract class OneDark implements Refresh {
  private OneDark() {
  }

  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatOneDarkIJTheme());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
