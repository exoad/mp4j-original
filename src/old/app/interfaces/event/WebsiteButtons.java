package app.interfaces.event;

import app.interfaces.dialog.ErrorMessage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;
/**
 * @author Jack Meng
 */
public class WebsiteButtons implements java.awt.event.ActionListener {
  private final String url;

  public WebsiteButtons(String url) {
    this.url = url;
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
