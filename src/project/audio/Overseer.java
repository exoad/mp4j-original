package project.audio;

import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import project.audio.content.AudioUtil;
import project.components.sub_components.FileViewPanel;
import project.components.sub_components.infoview.TopView;
import project.components.windows.ErrorWindow;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class Overseer extends StreamPlayer implements ActionListener {
  private File current;
  private JButton playPauseButton, approveButton;
  private FileViewPanel fvp;
  private TopView topView;

  public Overseer(AudioUtil f, FileViewPanel fvp, TopView tv) {
    tv.setSeer(this);
    this.current = f;
    this.fvp = fvp;
    this.topView = tv;
    playPauseButton = new JButton("Play");
    playPauseButton.addActionListener(this);
    playPauseButton.setEnabled(false);
    playPauseButton.setDoubleBuffered(true);
    playPauseButton.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
    approveButton = new JButton("Select File");
    approveButton.addActionListener(this);
  }

  public synchronized String getDir() {
    return fvp.getCurrentDirectory().getAbsolutePath();
  }

  public JButton getPlayPauseButton() {
    return playPauseButton;
  }

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

  public File getFile() {
    return current;
  }

  public synchronized void poke(AudioUtil s) {

  }

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
      if (fvp.getSelectedFile() != null)
        current = fvp.getSelectedFile();
      else {
        new ErrorWindow("No file selected!\nPlease select a valid Audio File of types:\n-mp3\n-wav");
      }
    }
  }
}
