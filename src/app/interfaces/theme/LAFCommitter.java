package app.interfaces.theme;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import app.interfaces.ErrorMessage;

public class LAFCommitter {
  private static JFrame frame;

  public LAFCommitter(JFrame inst) {
    frame = inst;
  }

  public void setTheme(Refresh theme) {
    try {
      if(!(theme instanceof Refresh)) {
        new ErrorMessage("Unable to process the specified Refresh theme.");
        return;
      }
      theme.refresh(frame);
    } catch (UnsupportedLookAndFeelException ex) {
      new ErrorMessage("Unable to set look and feel \n" + ex.getMessage());
    }
  }

}
