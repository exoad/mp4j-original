package app.interfaces.event;

import java.awt.event.ActionEvent;
import java.net.URI;

import app.interfaces.ErrorMessage;

import javax.swing.JButton;
import java.awt.Desktop;

public class WebsiteButtons implements java.awt.event.ActionListener {
  private final JButton button;
  private final String url;

  public WebsiteButtons(String url, JButton button) {
    this.url = url;
    this.button = button;
  }

  public WebsiteButtons(String url) {
    this.url = url;
    button = null;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      Desktop.getDesktop().browse(new URI(url));
    } catch (java.io.IOException | java.net.URISyntaxException e1) {
      e1.printStackTrace();
      new ErrorMessage(java.util.Arrays.toString(e1.getStackTrace()));
    }
    
  }
  
}
