package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatGruvboxDarkMediumIJTheme;

public abstract class Gruvbox implements Refresh {
  private Gruvbox() {
  }

  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatGruvboxDarkMediumIJTheme());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
