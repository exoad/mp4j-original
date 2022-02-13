package app.interfaces;

import app.interfaces.dialog.ErrorMessage;

import javax.swing.*;
import java.net.URL;

public class Splash {
  private final float seconds;

  public Splash(float seconds) {
    this.seconds = seconds;
  }

  public void run() {
    URL splash_screen = getClass().getResource("/icons/splash/splash_screen.png");
    JWindow window = new JWindow();
    assert splash_screen != null;
    window.getContentPane().add(
        new JLabel("", new ImageIcon(splash_screen),
            SwingConstants.CENTER));
    window.setSize(800, 600);
    window.setBounds(500, 150, 300, 200);
    window.setLocationRelativeTo(null);
    window.setVisible(true);
    try {
      Thread.sleep((int) (Math.floor(seconds)));
    } catch (InterruptedException e) {
      e.printStackTrace();
      new ErrorMessage(java.util.Arrays.toString(e.getStackTrace()));
    }
    window.setVisible(false);
    window.dispose();
  }

}
