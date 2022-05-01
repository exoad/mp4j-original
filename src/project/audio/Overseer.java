package project.audio;

import com.goxr3plus.streamplayer.enums.Status;
import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;
import project.audio.content.AudioInfoEditor;
import project.audio.content.AudioUtil;
import project.audio.content.VolumeConversion;
import project.components.sub_components.FileViewPanel;
import project.components.sub_components.infoview.TopView;
import project.components.windows.ErrorWindow;
import project.constants.ColorContent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Map;

public class Overseer extends StreamPlayer
    implements ActionListener, WindowListener, ChangeListener, StreamPlayerListener {
  private File current;
  public JButton playPauseButton, approveButton;
  public FileViewPanel fvp;
  public TopView topView;
  private JSlider volumeSlider, progressSlider, panSlider;
  private boolean errorShown = false, isOpened = false;
  private long time = 0L;

  public Overseer(AudioUtil f, FileViewPanel fvp, TopView tv) {
    super();
    tv.setSeer(this);

    this.current = f;
    this.fvp = fvp;
    this.topView = tv;
    playPauseButton = new JButton();
    playPauseButton.setToolTipText("Play/Pause");
    playPauseButton.addActionListener(this);
    playPauseButton.setIcon(new ImageIcon("resource/newrsc/play.png"));
    playPauseButton.setOpaque(true);
    playPauseButton.setBackground(null);
    playPauseButton.setPreferredSize(new Dimension(25, 25));
    approveButton = new JButton("Select File");
    approveButton.addActionListener(this);
    volumeSlider = new JSlider(0, 100);
    volumeSlider.setValue(45);
    volumeSlider.addChangeListener(this);
    volumeSlider.setBackground(ColorContent.VOLUME_SLIDER_BG);
    volumeSlider.setForeground(ColorContent.VOLUME_SLIDER_NORMAL_FG);
    volumeSlider.setOrientation(SwingConstants.VERTICAL);

    progressSlider = new JSlider(0, 100);
    progressSlider.setPreferredSize(new Dimension(300, 15));
    progressSlider.setBackground(ColorContent.VOLUME_SLIDER_BG);
    progressSlider.setForeground(ColorContent.PROGRESS_SLIDER_NORMAL);
    progressSlider.setOrientation(SwingConstants.HORIZONTAL);
    progressSlider.setPreferredSize(new Dimension(350, 15));
    progressSlider.setValue(0);

    panSlider = new JSlider(0, 100);
    panSlider.setValue(50);
    panSlider.setMinorTickSpacing(5);
    panSlider.setPaintTicks(true);
    panSlider.setSnapToTicks(true);
    panSlider.addChangeListener(this);
    panSlider.setToolTipText("None " + VolumeConversion.convertPan(50));
    panSlider.setBackground(ColorContent.VOLUME_SLIDER_BG);
    panSlider.setForeground(ColorContent.VOLUME_SLIDER_NORMAL_FG);
    panSlider.setOrientation(SwingConstants.VERTICAL);

    setGain(VolumeConversion.convertVolume(volumeSlider.getValue()));
    addStreamPlayerListener(this);
    setPan(VolumeConversion.convertPan(panSlider.getValue()));

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

  public JSlider getProgressSlider() {
    return progressSlider;
  }

  public JSlider getPanSlider() {
    return panSlider;
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
      if (!isOpened) {
        open(current);
        isOpened = true;
      }
      play();
      setGain(VolumeConversion.convertVolume(volumeSlider.getValue()));
      setPan(VolumeConversion.convertPan(panSlider.getValue()));

    } catch (StreamPlayerException e) {
      e.printStackTrace();
    }
  }

  public void pokeFile(File f) {
    this.current = f;
  }

  public float getConversionFactorWave() {
    return VolumeConversion.convertVolume(volumeSlider.getValue());
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

  public void resumeTime() {
    long secTime = time / 1000000;
    try {
      seekTo((int) secTime);
      play();
      setGain(VolumeConversion.convertVolume(volumeSlider.getValue()));
      setPan(VolumeConversion.convertPan(panSlider.getValue()));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void assertSliderValues() {
    setGain(VolumeConversion.convertVolume(volumeSlider.getValue()));
    setPan(VolumeConversion.convertPan(panSlider.getValue()));
  }

  public void pauseState() {
    assertSliderValues();
    stop();
    playPauseButton.setText("Play");
    topView.stopSpinning();
  }

  public void playState() {
    assertSliderValues();
    playPauseButton.setEnabled(true);
    playPauseButton.setText("Pause");
    topView.startSpinning();
  }

  private boolean hasPlayed = false;

  /**
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(playPauseButton)) {
      if (current == null) {
        if (!errorShown) {
          new ErrorWindow("No File Selected", this);
          errorShown = true;
        } else {
          errorShown = false;
        }
      } else if (current.getAbsolutePath().endsWith("mp3")) {
        assertSliderValues();
        if (!isPlaying()) {
          if (!hasPlayed) {
            playFile();
            hasPlayed = true;
          } else {
            resumeTime();
          }
          playState();
        } else {
          stop();
          pauseState();
        }
      }
    } else if (e.getSource().equals(approveButton)) {
      if (isPlaying() || isOpened()) {
        pauseState();
        topView.av.pokeAndResetDrawing();
      }
      if (fvp.getSelectedFile() != null) {
        current = fvp.getSelectedFile();
        try {
          open(current);
          assertSliderValues();
        } catch (StreamPlayerException e1) {
          e1.printStackTrace();
        }
        hasPlayed = false;
        progressSlider.setValue(0);
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
    if (e.getSource().equals(volumeSlider)) {
      float volNow = VolumeConversion.convertVolume(volumeSlider.getValue());
      setGain(volNow);
      if (volumeSlider.getValue() >= 90) {
        volumeSlider.setForeground(ColorContent.VOLUME_SLIDER_HEARING_LOSS_FG);
      } else if (volumeSlider.getValue() >= 70 && volumeSlider.getValue() < 90) {
        volumeSlider.setForeground(ColorContent.VOLUME_SLIDER_WARNING_FG);
      } else if (volumeSlider.getValue() < 70 && volumeSlider.getValue() > 0) {
        volumeSlider.setForeground(ColorContent.VOLUME_SLIDER_NORMAL_FG);
      } else {
        volumeSlider.setForeground(ColorContent.VOLUME_SLIDER_MUTED_FG);
      }
    } else if (e.getSource().equals(panSlider)) {
      setPan(VolumeConversion.convertPan(panSlider.getValue()));
      panSlider.setToolTipText(String.valueOf(panSlider.getValue()));
      panSlider.setForeground(ColorContent.VOLUME_SLIDER_NORMAL_FG);
      if (panSlider.getValue() > 50) {
        panSlider.setToolTipText("Right " + VolumeConversion.convertPan(panSlider.getValue()));
      } else if (panSlider.getValue() < 50) {
        panSlider.setToolTipText("Left " + VolumeConversion.convertPan(panSlider.getValue()));
      } else {
        panSlider.setToolTipText("Center");
        panSlider.setForeground(ColorContent.VOLUME_SLIDER_WARNING_FG);
      }
    }
  }

  /**
   * @param e
   */

  @Override
  public void windowClosed(WindowEvent e) {
    errorShown = false;
  }

  @Override
  public void windowActivated(WindowEvent e) {
  }

  @Override
  public void windowClosing(WindowEvent e) {
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

  @Override
  public void opened(Object arg0, Map<String, Object> arg1) {
  }

  @Override
  public void progress(int arg0, long arg1, byte[] pcmData, Map<String, Object> arg3) {
    time = arg1;
    int[] temp = new int[pcmData.length / 2];
    for (int i = 0; i < pcmData.length / 2; i++) {
      temp[i] = (pcmData[i * 2] & 0xFF) | (pcmData[i * 2 + 1] << 8);
    }

    int[] bars = new int[topView.av.MAX_DRAW];
    for (int i = 0, j = 0; i < temp.length && j < bars.length; i++, j++) {
      bars[j] = Math.min(Math.max(temp[i] / 69, -200), 200);
      bars[i] *= VolumeConversion.convertVolume(volumeSlider.getValue()) * 2;
    }

    topView.av.pokeAndDraw(bars);
  }

  @Override
  public void statusUpdated(StreamPlayerEvent arg0) {
    if (arg0.getPlayerStatus().equals(Status.STOPPED)) {
      pauseState();
    }
  }

}
