package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.FlatLightLaf;

public abstract class RegularLight implements Refresh {
  private RegularLight() {
  }

  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatLightLaf());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
