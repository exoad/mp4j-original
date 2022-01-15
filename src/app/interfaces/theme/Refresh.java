package app.interfaces.theme;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

public interface Refresh {
  public void refresh(JFrame frame) throws UnsupportedLookAndFeelException;
}