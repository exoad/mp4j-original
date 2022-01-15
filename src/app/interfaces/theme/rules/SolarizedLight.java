package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;
import com.formdev.flatlaf.intellijthemes.FlatSolarizedLightIJTheme;

public abstract class SolarizedLight implements Refresh {
  private SolarizedLight() {
  }

  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatSolarizedLightIJTheme());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
