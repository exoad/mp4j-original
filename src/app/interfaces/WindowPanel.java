package app.interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import app.interfaces.dialog.FrameConfirmDialog;
import app.functions.Worker;
import backend.audio.*;
import app.CLI;

import static java.lang.Math.*;

public class WindowPanel implements ActionListener, ChangeListener {
  protected JPanel bp, mainPanel;
  protected URL[] waves = new URL[4];
  protected JButton play_btn, new_file, loop_btn;
  protected JLabel header_notice, status, wave_synth;
  protected JSlider volume_slider;
  protected static JFrame frame;
  protected static File musicFile;
  protected static Clip clip;
  protected boolean loop = false;
  protected static boolean alreadyPlaying = false, toPause = false, playAsMp3 = false;
  protected static String music_path;
  protected URL pause_icon = getClass().getResource("/icons/others/pause_button.png");
  protected URL looped = getClass().getResource("/icons/others/loop_icon.png");
  protected Icon looped_icon = new ImageIcon(looped);
  protected Icon pause_button_ico;
  protected Worker master = new Worker();
  protected Thread loopWatcher = new Thread();

  {
    assert pause_icon != null;
    pause_button_ico = new javax.swing.ImageIcon(pause_icon);
  }

  protected URL play_icon = getClass().getResource("/icons/others/play_button.png");
  protected Icon play_button_ico;

  {
    assert play_icon != null;
    play_button_ico = new javax.swing.ImageIcon(play_icon);
  }

  public int currentFrame = 0;

  public WindowPanel(String resource) {
    music_path = resource;

    musicFile = SelectFileWindow.getFile();
    status = new JLabel("<html><b>Currently Playing: </b></html>" + musicFile.getName());
    status.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);

    waves[0] = getClass().getResource("/icons/animated/waves/wave_1.gif");
    waves[1] = getClass().getResource("/icons/animated/waves/wave_2.gif");
    waves[2] = getClass().getResource("/icons/animated/waves/wave_3.gif");
    waves[3] = getClass().getResource("/icons/animated/waves/paused/waves0.png");

    wave_synth = new JLabel(new ImageIcon(waves[3]));
    wave_synth.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);

    Icon loop_button_ico = new javax.swing.ImageIcon(looped);

    loop_btn = new JButton(loop_button_ico);
    loop_btn.setToolTipText("Loop");
    loop_btn.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);
    loop_btn.addActionListener(this);

    URL frame_icon = getClass().getResource("/icons/others/frame-icon.png");

    assert pause_icon != null;

    assert play_icon != null;

    assert frame_icon != null;
    ImageIcon frame_ico = new ImageIcon(frame_icon);

    frame = new JFrame("Music Player - Jack Meng");
    frame.setIconImage(frame_ico.getImage());
    frame.setUndecorated(true);
    frame.addComponentListener(new app.interfaces.event.FrameOrganizer(frame));
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);

    play_btn = new JButton(play_button_ico);
    play_btn.addActionListener(this);
    play_btn.setToolTipText("Play the current media");
    play_btn.setAlignmentX(Component.CENTER_ALIGNMENT);

    URL new_file_icon = getClass().getResource("/icons/others/file_select_folder_icon.png");
    assert new_file_icon != null;
    Icon new_file_ico = new ImageIcon(new_file_icon);

    new_file = new JButton(new_file_ico);
    new_file.addActionListener(this);
    new_file.setToolTipText("Select a new media file");
    new_file.setAlignmentX(Component.CENTER_ALIGNMENT);

    header_notice = new JLabel(
        "<html><body><strong><u>Supported File Types : .mp3 & .wav</u></strong><br><center>Place files in folder: <code>/musicML/</code></center></body></html><br>");

    header_notice.setFont(new Font("Courier", Font.PLAIN, 13));
    header_notice.setAlignmentX(Component.CENTER_ALIGNMENT);

    volume_slider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 50);
    volume_slider.setMajorTickSpacing(10);
    volume_slider.setMinorTickSpacing(1);
    volume_slider.setMinimum(0);
    volume_slider.setMaximum(100);
    volume_slider.setPaintTicks(true);
    volume_slider.setPaintLabels(true);
    volume_slider.setAlignmentX(Component.CENTER_ALIGNMENT);
    volume_slider.addChangeListener(this);
    volume_slider.setToolTipText("Change the volume. Current: " + volume_slider.getValue() + "%");

    bp = new JPanel();
    bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
    bp.add(wave_synth);
    bp.add(status);
    bp.add(play_btn);
    bp.add(new_file);
    bp.add(loop_btn);
    bp.add(volume_slider);

    bp.setPreferredSize(new Dimension(500, 200));

    mainPanel = new JPanel();
    mainPanel.add(bp);

    frame.add(mainPanel);

    currentFrame = 0;
  }

  public void volumeControl() {
    if (clip != null) {
      new Thread(() -> {
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      float range = gainControl.getMaximum() - gainControl.getMinimum();
      float gain = (volume_slider.getValue() / 100.0f) * range + gainControl.getMinimum();
      gainControl.setValue(gain);
      volume_slider.setToolTipText("Current Volume: " + volume_slider.getValue() + "%");
      }).start();
    }
  }

  private Thread worker = new Thread();

  public void playMusic() {
    if(musicFile.getAbsolutePath().endsWith(".mp3")) {
      try {
        musicFile = Music.convert(musicFile);
      } catch (AudioConversionException e) {
        e.printStackTrace();
      }
    }
    javax.sound.sampled.AudioInputStream audioInputStream;
    try {
      audioInputStream = javax.sound.sampled.AudioSystem
          .getAudioInputStream(musicFile);
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (UnsupportedAudioFileException | IOException e1) {
      e1.printStackTrace();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }

    clip.setMicrosecondPosition(currentFrame);
    clip.start();

    playAsMp3 = false;
    volumeControl();
    new Thread(() -> {
      // if the music is finished playing, update the button icon to be play
      while (clip.isActive() && clip != null) {
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          CLI.print(e, app.global.cli.CliType.ERROR);
        }
      }
      play_btn.setIcon(play_button_ico);
      wave_synth.setIcon(new ImageIcon(waves[3]));
    }).start();
  }

  public void pauseMusic() {
    if (clip != null) {
      currentFrame = (int) clip.getMicrosecondPosition();
      clip.stop();
    }
  }


  /**
   * @param args
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(WindowPanel::run);
    File f = new File("musicML");
    if (!f.exists())
      f.mkdir();
    WindowPanel.run();
  }

  public void setPauseState() {
    play_btn.setIcon(play_button_ico);
    play_btn.setToolTipText("Play the current media");
    status.setText("<html><b>Currently Playing Nothing</b></html>");
    wave_synth.setIcon(new ImageIcon(waves[3]));
  }

  public void setPlayState() {
    play_btn.setIcon(pause_button_ico);
    play_btn.setToolTipText("Pause the current media");
    status.setText("<html><u><b>Currently Playing: </b></u><br>" + musicFile.getName() + "</html>");
    wave_synth.setIcon(new ImageIcon(waves[(int) random() * 3]));
  }

  /**
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    CLI.print(e.getSource());
    CLI.print("Current: " + (clip != null ? clip.getMicrosecondPosition() : 0));
    if (e.getSource().equals(play_btn)) {
      if (worker != null)
        worker.interrupt();
      if (play_btn.getIcon() == play_button_ico) {
        if (worker != null) {
          worker.interrupt();
        }
        if (clip != null) {
          playMusic();
        }
        playMusic();
        setPlayState();
      } else if (play_btn.getIcon() == pause_button_ico) {
        if (worker != null)
          worker.interrupt();
        pauseMusic();
        setPauseState();
      }
    } else if (e.getSource().equals(volume_slider)) {
        volumeControl();
    } else if (e.getSource().equals(new_file)) {
      new FrameConfirmDialog("Are you sure you want to exit?", frame, new SelectFileWindow(music_path));
      pauseMusic();
      setPauseState();
    } else if (e.getSource().equals(loop_btn)) {
      URL clicked = getClass().getResource("/icons/others/loop_clicked_icon.png");
      if (loop_btn.getIcon() == looped_icon) {
        loop_btn.setIcon(new ImageIcon(clicked));
        loop_btn.setToolTipText("Looping is now on");
        loop = true;

        if (clip != null && (clip.isRunning() || clip.isActive())) {

          clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
      } else {
        loop_btn.setIcon(looped_icon);
        loop_btn.setToolTipText("Looping is now off");
        loop = false;
        if (clip.isRunning() || clip.isActive())
          clip.loop(0);
      }
    }
  }

  public static void run() {
    new WindowPanel(music_path);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * @param e
   */
  @Override
  public void stateChanged(ChangeEvent e) {
    if (e.getSource().equals(volume_slider)) {
        volumeControl();
    }
  }
}
