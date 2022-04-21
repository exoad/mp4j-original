package project.audio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;

import project.audio.content.AudioInfoEditor;
import project.audio.content.AudioUtil;
import project.components.sub_components.FileViewPanel;
import project.components.sub_components.infoview.TopView;
import project.components.windows.ErrorWindow;

public class Overseer extends StreamPlayer implements ActionListener, WindowListener, ChangeListener {
  private File current;
  public JButton playPauseButton, approveButton;
  private FileViewPanel fvp;
  private TopView topView;
  private JSlider volumeSlider;
  private boolean errorShown = false;

  public Overseer(AudioUtil f, FileViewPanel fvp, TopView tv) {
    tv.setSeer(this);
    this.current = f;
    this.fvp = fvp;
    this.topView = tv;
    playPauseButton = new JButton("Play");
    playPauseButton.addActionListener(this);
    playPauseButton.setEnabled(false);
    approveButton = new JButton("Select File");
    approveButton.addActionListener(this);

  }

  
  /** 
   * @return String
   */
  public synchronized String getDir() {
    return fvp.getCurrentDirectory().getAbsolutePath();
  }
  
  /** 
   * @return JButton
   */
  public JButton getPlayPauseButton() {
    return playPauseButton;
  }

  public JSlider getVolumeSlider() {
    return volumeSlider;
  }
  
  /** 
   * @return JButton
   */
  public JButton getApproveButton() {
    return approveButton;
  }

  public void playFile() {
    try {
      open(current);
    } catch (StreamPlayerException e) {
      e.printStackTrace();
    }
  }

  
  /** 
   * @return File
   */
  public File getFile() {
    return current;
  }

  
  /** 
   * @param s
   */
  public synchronized void poke(AudioUtil s) {
    this.current = s;
  }

  
  /** 
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(playPauseButton)) {
      if (current.getAbsolutePath().endsWith("mp3")) {
        if (isPlaying()) {
          playPauseButton.setText("Play");
        } else {
          playPauseButton.setText("Pause");
        }
      }
    } else if (e.getSource().equals(approveButton)) {
      if (fvp.getSelectedFile() != null) {
        current = fvp.getSelectedFile();
        topView.setAie(new AudioInfoEditor(current));
      } else {
        if (!errorShown) {
          new ErrorWindow(
              "No file selected!\nPlease select a valid Audio File of types:\n-mp3\n-wav", this);
          errorShown = true;
        }
      }
    }
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    System.out.println(e.getSource());
  }
  
  /** 
   * @param e
   */
  /* Junk Methods */
  @Override
  public void windowClosed(WindowEvent e) {
    errorShown = false;
  }

  @Override
  public void windowActivated(WindowEvent e) {}

  @Override
  public void windowClosing(WindowEvent e) {}

  @Override
  public void windowDeactivated(WindowEvent e) {}

  @Override
  public void windowDeiconified(WindowEvent e) {}

  @Override
  public void windowIconified(WindowEvent e) {}

  @Override
  public void windowOpened(WindowEvent e) {}



}
