package test;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class AudioWave extends JPanel {
  private JFrame f;
  private ArrayList<Float> data;
  private transient Clip c;
  private Thread t;

  public AudioWave() {
    setPreferredSize(new Dimension(500, 500));
    // read in the frequency data of each of the blocks (for 250) from the audio file
    data = new ArrayList<>();
    for(int i = 0 ; i < 250 ; i++) {
      // add the frequency data to the arraylist of the audio
      
    }
    try {
      c = AudioSystem.getClip();
    } catch (LineUnavailableException e1) {
      e1.printStackTrace();
    }
    try {
      c.open(AudioSystem.getAudioInputStream(
          new File("D:/zip/projects/MusicPlayer/audio/wav/Fall Out Boy - Centuries (Lyrics).wav")));
    } catch (Exception e) {
      e.printStackTrace();
    }

    f = new JFrame();
    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    f.setSize(new Dimension(500, 500));
    f.setVisible(true);
    f.add(this);
    runner();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);


  }

  public void runner() {
    try {
      c.open();
    } catch (LineUnavailableException e) {
      e.printStackTrace();
    }
    new Thread(() -> c.start()).start();
    // constantly update the frequency spectrum
    while (true) {
      repaint();
      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    new AudioWave().runner();

  }
}
