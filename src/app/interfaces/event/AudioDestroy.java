package app.interfaces.event;

import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.JFrame;

import app.Runner;
import app.interfaces.WindowPanel;
import backend.audioutil.Player;

import java.awt.event.WindowEvent;

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
    m.get().stop();
    m.get().close();
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
