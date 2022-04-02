package app.interfaces.event;

import backend.audioutil.Player;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class AudioDestroy implements WindowListener {
  private Player m;
  private JFrame f;
  public AudioDestroy(JFrame f, Player m) {
    this.m = m;
    this.f = f;
  }

  @Override
  public void windowActivated(WindowEvent e) {
    
  }

  @Override
  public void windowClosed(WindowEvent e) {
  }

  @Override
  public synchronized  void windowClosing(WindowEvent e) {
    m.pause();
    f.dispose();
  }

  @Override
  public void windowDeactivated(WindowEvent e) {
    
  }

  @Override
  public void windowDeiconified(WindowEvent e) {
    
  }

  @Override
  public void windowIconified(WindowEvent e) {
    
  }

  @Override
  public void windowOpened(WindowEvent e) {
    
  }
  
}
