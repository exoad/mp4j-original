package main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
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
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatDarkLaf;

public class WindowPanel implements ActionListener, ChangeListener {
  protected JPanel bp, mainPanel;
  protected JButton play_btn, pause_btn;
  protected JLabel header_notice, status;
  protected JSlider volume_slider;
  protected static JFrame frame;
  protected float volume_keeper;
  protected static File musicFile;
  protected static Clip clip;
  protected boolean loop = false;

  public WindowPanel() {
    FlatDarkLaf.setup();
    URL musicFile1 = getClass().getResource("assets/Elite.wav");
    assert musicFile1 != null;
    musicFile = new File(musicFile1.getFile());
    status = new JLabel("<html><b>Currently Playing: </b></html>" + musicFile.getName());
    status.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);

    URL frame_icon = getClass().getResource("assets/frame-icon.png/");
    URL play_icon = getClass().getResource("assets/play_button.png");
    URL pause_icon = getClass().getResource("assets/pause_button.png");

    assert pause_icon != null;
    Icon pause_button_ico = new ImageIcon(pause_icon);
    assert play_icon != null;
    Icon play_button_ico = new ImageIcon(play_icon);
    assert frame_icon != null;
    ImageIcon frame_ico = new ImageIcon(frame_icon);

    frame = new JFrame("Music Player - Jack Meng");
    frame.setIconImage(frame_ico.getImage());
    frame.setResizable(false);

    play_btn = new JButton(play_button_ico);
    play_btn.addActionListener(this);
    play_btn.setToolTipText("Play the current media");
    play_btn.setAlignmentX(Component.CENTER_ALIGNMENT);

    pause_btn = new JButton(pause_button_ico);
    pause_btn.addActionListener(this);
    pause_btn.setToolTipText("Pause the current media");
    pause_btn.setAlignmentX(Component.CENTER_ALIGNMENT);

    header_notice = new JLabel(
        "<html><body><strong><u>Supported File Types : .mp3 & .wav</u></strong><br><center>Place files in folder: <code>/musicML/</code></center></body></html><br>");

    header_notice.setFont(new Font("Courier", Font.PLAIN, 13));
    header_notice.setAlignmentX(Component.CENTER_ALIGNMENT);

    // initialize volume slider and add it to bp panel
    volume_slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
    volume_slider.setMajorTickSpacing(10);
    volume_slider.setMinorTickSpacing(1);
    volume_slider.setPaintTicks(true);
    volume_slider.setPaintLabels(true);
    volume_slider.setAlignmentX(Component.CENTER_ALIGNMENT);
    volume_slider.addChangeListener(this);
    volume_slider.setToolTipText("Change the volume. Current: " + volume_slider.getValue() + "%");

    bp = new JPanel();
    bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
    bp.add(status);
    bp.add(play_btn);
    bp.add(pause_btn);
    bp.add(volume_slider);

    bp.setPreferredSize(new Dimension(450, 200));

    mainPanel = new JPanel();
    mainPanel.add(bp);

    frame.add(mainPanel);

  }

  // method that uses the volume slider to control the volume of clip
  public void volumeControl() {
    int volume = volume_slider.getValue();
    volume_keeper = (float) (Math.log(volume / 100.0) / Math.log(12.0) * 30.0);
    FloatControl gc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    gc.setValue(volume_keeper);
    volume_slider.setToolTipText("Change the volume. Current: " + volume_slider.getValue() + "%");
    clip.start();
  }

  public void readAndPlayMusic() throws IOException {
    try {
      System.out.println("Loaded: " + musicFile.getName());
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
      clip = AudioSystem.getClip();
      if (clip.isRunning() || clip.isOpen() || clip.isActive()) {
        clip.stop();
        clip.close();
      } else {
        clip.open(audioInputStream);
        clip.start();
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }

  public static void readMusic() throws IOException {
    try {
      System.out.println("Loaded: " + musicFile.getName());
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (LineUnavailableException | UnsupportedAudioFileException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(WindowPanel::run);
    File f = new File("musicML");
    if (!f.exists())
      f.mkdir();
    WindowPanel.run();
  }

  // check if the current clip is playing if it is stop it else we play it
  public static void playOrStop() throws IOException {
    readMusic();
    if (clip.isRunning() && clip.isActive()) {
      clip.stop();
      clip.close();
    } else {
      clip.start();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == play_btn) {
      // dont play music if music is already playing
      try {
        playOrStop();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      status.setText("<html><b>Currently Playing: </b></html>" + musicFile.getName());
    } else if (e.getSource() == pause_btn) {
      System.out.println("Pause button clicked");
      clip.stop();
      status.setText("<html><b>Currently Playing: </b></html>" + musicFile.getName());
    } else if (e.getSource() == volume_slider) {
      volumeControl();
    }
  }

  public static void run() {
    new WindowPanel();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    if (e.getSource() == volume_slider) {
      volumeControl();
    }
  }
}
