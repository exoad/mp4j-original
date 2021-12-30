package main.util;

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
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.formdev.flatlaf.FlatDarkLaf;

public class WindowPanel implements ActionListener, ChangeListener {
  protected JPanel bp, mainPanel;
  protected JButton play_btn, new_file;
  protected JLabel header_notice, status;
  protected JSlider volume_slider;
  protected static JFrame frame;
  protected float volume_keeper;
  protected static File musicFile;
  protected static Clip clip;
  protected boolean loop = false;
  protected static boolean alreadyPlaying = false, toPause = false;
  protected static String music_path;
  protected URL pause_icon = getClass().getResource("/pause_button.png");
  protected Icon pause_button_ico = new ImageIcon(pause_icon);
  protected URL play_icon = getClass().getResource("/play_button.png");
  protected Icon play_button_ico = new ImageIcon(play_icon);

  public WindowPanel(String resource) {
    music_path = resource;
    FlatDarkLaf.setup();
    musicFile = SelectFileWindow.getFile();
    status = new JLabel("<html><b>Currently Playing: </b></html>" + musicFile.getName());
    status.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);

    URL frame_icon = getClass().getResource("/frame-icon.png");
    

    assert pause_icon != null;

    assert play_icon != null;
    
    assert frame_icon != null;
    ImageIcon frame_ico = new ImageIcon(frame_icon);

    frame = new JFrame("Music Player - Jack Meng");
    frame.setIconImage(frame_ico.getImage());
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);

    play_btn = new JButton(play_button_ico);
    play_btn.addActionListener(this);
    play_btn.setToolTipText("Play the current media");
    play_btn.setAlignmentX(Component.CENTER_ALIGNMENT);

    URL new_file_icon = getClass().getResource("/file_select_folder_icon.png");
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
    bp.add(status);
    bp.add(play_btn);
    bp.add(volume_slider);

    bp.setPreferredSize(new Dimension(450, 200));

    mainPanel = new JPanel();
    mainPanel.add(bp);

    frame.add(mainPanel);
  }

  public void volumeControl() {
    if (clip != null) {
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
      float range = gainControl.getMaximum() - gainControl.getMinimum();
      float gain = (volume_slider.getValue() / 100.0f) * range + gainControl.getMinimum();
      gainControl.setValue(gain);
    }
  }

  public void readAndPlayMusic() throws IOException {
    try {
      System.out.println("Loaded: " + musicFile.getName());
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
      clip = AudioSystem.getClip();
      if (clip.isRunning() || clip.isOpen() || clip.isActive() || alreadyPlaying) {
        clip.stop();
        clip.close();
        alreadyPlaying = false;
      } else {
        clip.open(audioInputStream);
        clip.start();
        alreadyPlaying = true;
      }
    } catch (LineUnavailableException | UnsupportedAudioFileException e) {
      e.printStackTrace();
      new ErrorMessage(e.getMessage());
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
      new ErrorMessage(e.getMessage());
    }
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(WindowPanel::run);
    File f = new File("musicML");
    if (!f.exists())
      f.mkdir();
    WindowPanel.run();
  }

  public static void playOrStop() throws IOException {
    readMusic();
    clip.start();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == play_btn) {
      if(!toPause) {
        play_btn.setIcon(pause_button_ico);
        toPause = true;
      } else {
        play_btn.setIcon(play_button_ico);
        toPause = false;
      }
      try {
        if (!alreadyPlaying)
          playOrStop();
      } catch (IOException e1) {
        e1.printStackTrace();
      }
      status.setText("<html><b>Currently Playing: </b></html>" + musicFile.getName());
    } else if (e.getSource() == volume_slider) {
      volumeControl();
    }
  }

  public static void run() {
    new WindowPanel(music_path);
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
