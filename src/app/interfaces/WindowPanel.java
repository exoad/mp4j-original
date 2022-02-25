package app.interfaces;

import app.functions.Time;
import app.interfaces.dialog.FrameConfirmDialog;
import app.interfaces.event.AudioDestroy;
import app.interfaces.modifier.WindowPaneSize;
import app.interfaces.theme.LAFCommitter;
import backend.audioutil.Player;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Random;

public class WindowPanel implements ActionListener, ChangeListener, Runnable {
  protected JPanel bp, mainPanel, sliderPanel, framePanel;
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

    status = new JLabel(pl.prettyMetaData(), SwingConstants.CENTER);
    status.setSize(new Dimension(80, (int) WindowPaneSize.FINALSIZE.getHeight()));
    status.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);

    waves[0] = getClass().getResource("/icons/animated/waves/wave_1.gif");
    waves[1] = getClass().getResource("/icons/animated/waves/wave_2.gif");
    waves[2] = getClass().getResource("/icons/animated/waves/wave_3.gif");
    waves[3] = getClass().getResource("/icons/animated/waves/paused/waves0.png");

    assert waves[3] != null;
    wave_synth = new JLabel(pl.getCoverArt());
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
    play_btn.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);

    play_btn.setAlignmentY(java.awt.Component.CENTER_ALIGNMENT);

    URL new_file_icon = getClass().getResource("/icons/others/file_select_folder_icon.png");
    assert new_file_icon != null;
    Icon new_file_ico = new ImageIcon(new_file_icon);

    new_file = new JButton(new_file_ico);
    new_file.addActionListener(this);
    new_file.setToolTipText("Select a new media file");
    new_file.setAlignmentX(Component.CENTER_ALIGNMENT);

    volume_slider = new JSlider(0, 100, 50);
    volume_slider.setMinimum(0);
    volume_slider.setMaximum(100);
    volume_slider.setAlignmentX(Component.CENTER_ALIGNMENT);
    volume_slider.addChangeListener(this);
    volume_slider.setOrientation(SwingConstants.VERTICAL);
    volume_slider.setToolTipText("Volume: " + volume_slider.getValue() + "%");

    JSlider[] stubs = new JSlider[2];
    stubs[0] = new JSlider(0, 100, 50);
    stubs[0].setAlignmentX(Component.CENTER_ALIGNMENT);
    stubs[0].setOrientation(SwingConstants.VERTICAL);
    stubs[0].setEnabled(false);
    stubs[1] = new JSlider(0, 100, 50);
    stubs[1].setAlignmentX(Component.CENTER_ALIGNMENT);
    stubs[1].setEnabled(false);
    stubs[1].setOrientation(SwingConstants.VERTICAL);

    frameSlider = new JSlider(0, 100, 0);
    frameSlider.setMinimum(0);
    frameSlider.setPaintTrack(true);
    frameSlider.setAlignmentX(Component.CENTER_ALIGNMENT);
    frameSlider.setAlignmentY(Component.CENTER_ALIGNMENT);
    frameSlider.setForeground(new Color(97, 175, 239));
    frameSlider.setOpaque(true);
    frameSlider.setSize(20, 100);
    frameSlider.setAutoscrolls(true);
    frameSlider.setToolTipText("Time: " + Time.msToHHMMSS(pl.getFrame()) + "%");

    Color border = new LAFCommitter().setBorderColor();

    framePanel = new JPanel();
    framePanel.setBorder(BorderFactory.createLineBorder(border));
    framePanel.setPreferredSize(new Dimension(320, (WindowPaneSize.FINALSIZE.height / 3)));

    bp = new JPanel();
    bp.setBorder(BorderFactory.createLineBorder(border));
    bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
    bp.add(wave_synth);
    bp.add(status);

    bp.setPreferredSize(new Dimension(320, (int) WindowPaneSize.FINALSIZE.getHeight() / 3));
    volumeText = new JLabel("<html><div style='text-align: center;'>Volume: " + volume_slider.getValue()
        + "%</div></html>", SwingConstants.CENTER);
    volumeText.setFont(new Font("Courier", Font.BOLD, 13));

    frameText = new JLabel("<html><div style='text-align: center;'>Frame: " + Time.msToHHMMSS(pl.getFrame())
        + "</div></html>", SwingConstants.CENTER);
    frameText.setFont(new Font("Courier", Font.BOLD, 13));

    framePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 10));
    framePanel.setBorder(BorderFactory.createLineBorder(border));
    framePanel.add(play_btn);
    framePanel.add(new_file);
    framePanel.add(loop_btn);
    framePanel.add(frameSlider);

    sliderPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
    sliderPanel.setBorder(BorderFactory.createLineBorder(border));
    sliderPanel.add(volume_slider);
    sliderPanel.add(stubs[0]);
    sliderPanel.add(stubs[1]);
    sliders.add(sliderPanel);

    mainPanel = new JPanel();
    mainPanel.setLayout(new BorderLayout());
    mainPanel.add(framePanel, BorderLayout.SOUTH);
    mainPanel.add(bp, BorderLayout.CENTER);
    mainPanel.add(sliderPanel, BorderLayout.EAST);

    frame.add(mainPanel);

    currentFrame = 0;
    watchEnd();
  }

  private Thread timeKeeper;

  public void setPauseState() {
    play_btn.setIcon(play_button_ico);
    play_btn.setToolTipText("Play the current media");
    frame.setIconImage(
        new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource("/icons/others/frame-paused.png")))
            .getImage());
    if (timeKeeper != null)
      timeKeeper.interrupt();
    frameText.setText(
        "<html><div style='text-align: center;'>Frame: " + Time.msToHHMMSS(pl.getFrame()) + "</div></html>");

  }

  public void watchEnd() {
    Thread endWatcher = new Thread(() -> {
      try {
        Thread.sleep(600);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      if (pl.getFrame() >= pl.getLength()) {
        pl.setFrame(0);
        frameSlider.setValue(0);
        frameSlider.setToolTipText(
            "<html><div style='text-align: center;'>Frame: " + Time.msToHHMMSS(pl.getFrame()) + "</div></html>");
        frameText.setText(
            "<html><div style='text-align: center;'>Frame: " + Time.msToHHMMSS(pl.getFrame()) + "</div></html>");
        setPauseState();
      }
    });
    endWatcher.start();
  }

  public void setPlayState() {
    play_btn.setIcon(pause_button_ico);
    play_btn.setToolTipText("Pause the current media");
    frame.setIconImage(
        new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource("/icons/others/frame-playing.png")))
            .getImage());
    timeKeeper = new Thread(() -> {
      while (true) {
        if (pl.isPlaying()) {
          frameSlider.setValue((int) (((double) pl.getFrame() / (double) pl.getLength()) * 100));
          frameSlider.setToolTipText(
              "<html><div style='text-align: center;'>Frame: " + Time.msToHHMMSS(pl.getFrame()) + "</div></html>");
          frameText.setText(
              "<html><div style='text-align: center;'>Frame: " + Time.msToHHMMSS(pl.getFrame()) + "</div></html>");
        }
      }
    });
    timeKeeper.start();
  }

  public void updateVolume() {
    Thread volumeWorker = new Thread(() -> {
      pl.vols = volume_slider.getValue();
      pl.setVolume();
      volume_slider.setToolTipText(
          "<html><div style='text-align: center;'>Volume: " + volume_slider.getValue() + "%</div></html>");
      volumeText.setText(
          "<html><div style='text-align: center;'>Volume: " + volume_slider.getValue() + "%</div></html>");
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
        volume_slider.setToolTipText(
            "<html><div style='text-align: center;'>Volume: " + volume_slider.getValue() + "%</div></html>");
        volumeText.setText(
            "<html><div style='text-align: center;'>Volume: " + volume_slider.getValue() + "%</div></html>");
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
