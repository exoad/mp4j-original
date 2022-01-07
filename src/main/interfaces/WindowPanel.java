package main.interfaces;

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

import javazoom.jl.decoder.JavaLayerException;
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
  protected static boolean alreadyPlaying = false, toPause = false, playAsMp3 = false;
  protected static String music_path;
  protected URL pause_icon = getClass().getResource("/pause_button.png");
  protected Icon pause_button_ico;

  {
    assert pause_icon != null;
    pause_button_ico = new javax.swing.ImageIcon(pause_icon);
  }

  protected URL play_icon = getClass().getResource("/play_button.png");
  protected Icon play_button_ico;

  {
    assert play_icon != null;
    play_button_ico = new javax.swing.ImageIcon(play_icon);
  }

  public long currentFrame = 0;

  public WindowPanel(String resource) {
    music_path = resource;
    
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
      volume_slider.setToolTipText("Current Volume: " + volume_slider.getValue() + "%");
    }
  }

  
  /** 
   * @throws JavaLayerException
   */
  public void playMusic() throws JavaLayerException {
    try {
      if (!musicFile.getName().endsWith(".mp3")) {
        javax.sound.sampled.AudioInputStream audioInputStream = javax.sound.sampled.AudioSystem
            .getAudioInputStream(musicFile);
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.setMicrosecondPosition(currentFrame);
        clip.start();
        playAsMp3 = false;
        volumeControl();
      } else {
        playAsMp3 = true;
        javazoom.jl.player.Player mp3Player = new javazoom.jl.player.Player(new java.io.FileInputStream(musicFile));
        mp3Player.play();
      }

    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  public void pauseMusic() {
    if (clip != null) {
      currentFrame = clip.getMicrosecondPosition();
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
  }

  public void setPlayState() {
    play_btn.setIcon(pause_button_ico);
    play_btn.setToolTipText("Pause the current media");
    status.setText("<html><b>Currently Playing: </b><br>" + musicFile.getName() + "</html>");
  }
  
  /** 
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(play_btn)) {
      if (play_btn.getIcon() == play_button_ico) {
        try {
          playMusic();
        } catch (JavaLayerException e1) {
          e1.printStackTrace();
        }
        setPlayState();
      } else if (play_btn.getIcon() == pause_button_ico) {
        pauseMusic();
        setPauseState();
      }
    } else if (e.getSource().equals(volume_slider)) {
      if (playAsMp3 || musicFile.getName().endsWith(".mp3")) {
        volume_slider.setEnabled(false);
        volume_slider.setToolTipText("MP3 is only semi supported for now");
      } else {
        volume_slider.setEnabled(true);
        volume_slider.setToolTipText("Current Volume: " + volume_slider.getValue() + "%");
      }
      volumeControl();
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
