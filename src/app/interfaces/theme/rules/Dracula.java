package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatDraculaIJTheme;

public abstract class Dracula implements Refresh {
  private Dracula() {
  }

  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatDraculaIJTheme());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
