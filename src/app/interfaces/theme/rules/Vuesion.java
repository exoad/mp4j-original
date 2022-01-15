package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatVuesionIJTheme;

public abstract class Vuesion implements Refresh {
  private Vuesion() {
  }

  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatVuesionIJTheme());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
