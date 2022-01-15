package app.interfaces.theme.rules;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.theme.Refresh;

import com.formdev.flatlaf.FlatDarkLaf;

public abstract class RegularDark implements Refresh {
  private RegularDark() {}
  @Override
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException {
    UIManager.setLookAndFeel(new FlatDarkLaf());
    SwingUtilities.updateComponentTreeUI(frame);
    frame.pack();
  }
}
