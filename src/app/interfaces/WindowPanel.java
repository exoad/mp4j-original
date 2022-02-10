package app.interfaces;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.audioutil.*;

import app.interfaces.dialog.FrameConfirmDialog;
import app.interfaces.event.AudioDestroy;

import static java.lang.Math.*;

public class WindowPanel implements ActionListener, ChangeListener, Runnable {
  protected JPanel bp, mainPanel;
  protected URL[] waves = new URL[4];
  protected JButton play_btn, new_file, loop_btn;
  protected JLabel header_notice, status, wave_synth;
  protected JSlider volume_slider;
  protected static JFrame frame;
  protected static File musicFile;
  protected boolean loop = false;
  protected static boolean alreadyPlaying = false, toPause = false;
  protected static String music_path;
  protected URL pause_icon = getClass().getResource("/icons/others/pause_button.png");
  protected URL looped = getClass().getResource("/icons/others/loop_icon.png");
  protected Icon looped_icon = new ImageIcon(looped);
  protected Icon pause_button_ico;
  protected Player pl;
  protected Thread master = new Thread();
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
    pl = new Player(musicFile, 50f);

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
    frame.addWindowListener(new AudioDestroy(frame, pl));
    frame.setUndecorated(true);
    frame.addComponentListener(new app.interfaces.event.FrameOrganizer(frame));
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

  public void setPauseState() {
    play_btn.setIcon(play_button_ico);
    play_btn.setToolTipText("Play the current media");
    status.setText("<html><b>Currently Playing Nothing</b></html>");
    wave_synth.setIcon(new ImageIcon(waves[3]));
    frame.setIconImage(new ImageIcon(getClass().getResource("/icons/others/frame-paused.png")).getImage());
  }

  public void setPlayState() {
    play_btn.setIcon(pause_button_ico);
    play_btn.setToolTipText("Pause the current media");
    status.setText("<html><u><b>Currently Playing: </b></u><br>" + musicFile.getName() + "</html>");
    wave_synth.setIcon(new ImageIcon(waves[(int) random() * 3]));
    frame.setIconImage(new ImageIcon(getClass().getResource("/icons/others/frame-playing.png")).getImage());
  }

  private Thread volumeWorker = new Thread();

  public void updateVolume() {

    volumeWorker = new Thread(() -> {
      pl.vols = volume_slider.getValue();
      pl.setVolume();
      volume_slider.setToolTipText("Volume: " + volume_slider.getValue() + "%");
    });
    volumeWorker.start();
  }

  @Override
  public synchronized void actionPerformed(ActionEvent e) {
    if (e.getSource() == play_btn) {
      updateVolume();
      if (alreadyPlaying) {

        pl.pause();
        setPauseState();
        alreadyPlaying = false;
      } else {
        pl.play();

        setPlayState();
        alreadyPlaying = true;
      }
    } else if (e.getSource().equals(new_file)) {
      new FrameConfirmDialog("Are you sure you want to exit?", frame, new SelectFileWindow(music_path));
      pl.pause();
      setPauseState();
      alreadyPlaying = false;
    } else if (e.getSource().equals(loop_btn)) {

    } else if (e.getSource().equals(volume_slider)) {
      Thread t = new Thread(() -> {
        pl.vols = volume_slider.getValue();
        pl.setVolume();
        volume_slider.setToolTipText("Volume: " + volume_slider.getValue() + "%");
      });
      t.start();
    }
  }

  @Override
  public void run() {
    frame.pack();
    frame.setVisible(true);
  }

  /**
   * @param e
   */
  @Override
  public synchronized void stateChanged(ChangeEvent e) {
    // make this multi-threaded
    if (e.getSource().equals(volume_slider)) {
      Thread t = new Thread(() -> {
        pl.vols = volume_slider.getValue();
        pl.setVolume();
        volume_slider.setToolTipText("Volume: " + volume_slider.getValue() + "%");
      });
      t.start();
    }
  }
}
