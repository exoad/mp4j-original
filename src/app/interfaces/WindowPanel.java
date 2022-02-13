package app.interfaces;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import backend.audioutil.*;

import app.interfaces.dialog.FrameConfirmDialog;
import app.interfaces.event.AudioDestroy;
import app.interfaces.modifier.WindowPaneSize;
import app.CLI;
import app.functions.Time;

import static java.lang.Math.*;

public class WindowPanel implements ActionListener, ChangeListener, Runnable {
  protected JPanel bp, mainPanel, sliderPanel;
  protected JScrollPane sliders;
  protected URL[] waves = new URL[4];
  protected JButton play_btn, new_file, loop_btn;
  protected JLabel header_notice, status, wave_synth, frameText, volumeText;
  protected JSlider volume_slider, frameSlider;
  protected static JFrame frame;
  protected static File musicFile;
  protected boolean loop = false;
  protected static boolean alreadyPlaying = false, toPause = false;
  protected static String musicPath;
  protected URL pause_icon = getClass().getResource("/icons/others/pause_button.png");
  protected URL looped = getClass().getResource("/icons/others/loop_icon.png");
  protected Icon looped_icon = new ImageIcon(looped);
  protected Icon pause_button_ico;
  protected Player pl;
  protected Thread master = new Thread();
  protected Thread loopWatcher = new Thread();
  protected String fileLength = "";
  protected URL play_icon = getClass().getResource("/icons/others/play_button.png");
  protected Icon play_button_ico;

  public int currentFrame = 0;

  public WindowPanel(String resource) {
    musicPath = resource;

    musicFile = SelectFileWindow.getFile();

    assert pause_icon != null;
    pause_button_ico = new javax.swing.ImageIcon(pause_icon);

    assert play_icon != null;
    play_button_ico = new javax.swing.ImageIcon(play_icon);

    pl = new Player(musicFile, 50f);
    sliderPanel = new JPanel();
    sliderPanel.setPreferredSize(new Dimension(180, (int) WindowPaneSize.FINALSIZE.getHeight()));
    sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
    sliders = new JScrollPane(sliderPanel);
    sliders.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    sliders.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

    String html = "<html><div style='text-align: center;'><p>Track: <b>"
        + Player.safeName(Player.removeFileEnding(musicFile.getName()))
        + "<b></p></div></html>";

    status = new JLabel(html, SwingConstants.CENTER);
    status.setSize(new Dimension(80, (int) WindowPaneSize.FINALSIZE.getHeight()));
    status.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);

    waves[0] = getClass().getResource("/icons/animated/waves/wave_1.gif");
    waves[1] = getClass().getResource("/icons/animated/waves/wave_2.gif");
    waves[2] = getClass().getResource("/icons/animated/waves/wave_3.gif");
    waves[3] = getClass().getResource("/icons/animated/waves/paused/waves0.png");

    assert waves[3] != null;
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

    volume_slider = new JSlider(0, 100, 50);
    volume_slider.setMinimum(0);
    volume_slider.setMaximum(100);
    volume_slider.setAlignmentX(Component.CENTER_ALIGNMENT);
    volume_slider.setAlignmentY(Component.CENTER_ALIGNMENT);
    volume_slider.addChangeListener(this);
    volume_slider.setToolTipText("Change the volume. Current: " + volume_slider.getValue() + "%");

    frameSlider = new JSlider(0, 100, 0);
    frameSlider.setMinimum(0);
    frameSlider.setPaintTrack(true);
    frameSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
    frameSlider.setAlignmentY(Component.CENTER_ALIGNMENT);
    frameSlider.setForeground(new Color(97, 175, 239));
    frameSlider.setOpaque(true);
    frameSlider.setAutoscrolls(true);
    frameSlider.setToolTipText("Current Time: " + Time.msToHHMMSS(pl.getFrame()));

    bp = new JPanel();
    bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
    bp.add(wave_synth);
    bp.add(status);
    bp.add(play_btn);
    bp.add(new_file);
    bp.add(loop_btn);

    bp.setPreferredSize(new Dimension(320, (int) WindowPaneSize.FINALSIZE.getHeight()));
    volumeText = new JLabel("Volume", SwingConstants.CENTER);
    volumeText.setFont(new Font("Courier", Font.BOLD, 13));

    frameText = new JLabel("Position: " + Time.msToHHMMSS(pl.getFrame()), SwingConstants.CENTER);
    frameText.setFont(new Font("Courier", Font.BOLD, 13));

    sliderPanel.add(volumeText);
    sliderPanel.add(volume_slider);
    sliderPanel.add(frameText);
    sliderPanel.add(frameSlider);
    sliderPanel.add(new JSlider());
    sliders.add(sliderPanel);

    mainPanel = new JPanel();
    mainPanel.add(bp);
    mainPanel.add(sliderPanel);

    frame.add(mainPanel);

    currentFrame = 0;
    watchEnd();
  }

  private Thread timeKeeper;

  public void setPauseState() {
    play_btn.setIcon(play_button_ico);
    play_btn.setToolTipText("Play the current media");
    wave_synth.setIcon(new ImageIcon(waves[3]));
    frame.setIconImage(
        new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource("/icons/others/frame-paused.png")))
            .getImage());
    frameText.setText("Position: " + Time.msToHHMMSS(pl.getFrame()));
    if(timeKeeper != null)
      timeKeeper.interrupt();
  }

  public void watchEnd() {
    Thread endWatcher = new Thread(() -> {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (pl.getFrame() >= pl.getLength()) {
        pl.setFrame(0);
        frameSlider.setValue(0);
        frameSlider.setToolTipText("Current Time: " + Time.msToHHMMSS(pl.getFrame()));
        frameText.setText("Position: " + Time.msToHHMMSS(pl.getFrame()));
        setPauseState();
      }
    });
    endWatcher.start();
  }

  public void setPlayState() {
    play_btn.setIcon(pause_button_ico);
    play_btn.setToolTipText("Pause the current media");
    wave_synth.setIcon(new ImageIcon(waves[new Random().nextInt(3)]));
    frame.setIconImage(
        new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource("/icons/others/frame-playing.png")))
            .getImage());
    timeKeeper = new Thread(() -> {
      while (true) {
        if (pl.isPlaying()) {
          frameSlider.setValue((int) (((double) pl.getFrame() / (double) pl.getLength()) * 100));
          frameSlider.setToolTipText("Current Time: " + Time.msToHHMMSS(pl.getFrame()));
          frameText.setText("Position: " + Time.msToHHMMSS(pl.getFrame()));
        }
      }
    });
    timeKeeper.start();
  }

  public void updateVolume() {
    Thread volumeWorker = new Thread(() -> {
      pl.vols = volume_slider.getValue();
      pl.setVolume();
      volume_slider.setToolTipText("Volume: " + volume_slider.getValue() + "%");
      volumeText.setText("Volume: " + volume_slider.getValue() + "%");
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
      new FrameConfirmDialog("Are you sure you want to exit?", frame, new SelectFileWindow(musicPath));
      pl.pause();
      setPauseState();
      alreadyPlaying = false;
    } else if (e.getSource().equals(loop_btn)) {
      // DO NOTHING
    } else if (e.getSource().equals(volume_slider)) {
      Thread t = new Thread(() -> {
        pl.vols = volume_slider.getValue();
        pl.setVolume();
        volume_slider.setToolTipText("Volume: " + volume_slider.getValue() + "%");
        volumeText.setText("Volume: " + volume_slider.getValue() + "%");
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
        volumeText.setText("Volume: " + volume_slider.getValue() + "%");
      });
      t.start();
    }
  }

  public static void main(String[] args) {
    new WindowPanel("D:\\zip\\projects\\MusicPlayer\\audio\\mp3\\Flexxus - Skyline.mp3").run();
  }
}
