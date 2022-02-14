package backend.audioutil;

import backend.audio.Music;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class Player {
  private File f;
  public Clip c;
  private long frame = 0L;
  public float vols;
  private boolean alreadyOpen = false;
  private final Thread worker = new Thread();

  public Player(File f33, float volume) {
    if (f33.getAbsolutePath().endsWith(".mp3")) {
      this.f = Music.convert(f33);
    } else {
      this.f = f33;
    }
    this.vols = volume;
    try {
      c = AudioSystem.getClip();
    } catch (LineUnavailableException e1) {
      e1.printStackTrace();
    }

  }

  public Clip get() {
    return c;
  }

  public void setVolume() {
    if (c != null) {
      try {
        javax.sound.sampled.FloatControl gainControl = (javax.sound.sampled.FloatControl) c
            .getControl(javax.sound.sampled.FloatControl.Type.MASTER_GAIN);
        float range = gainControl.getMaximum() - gainControl.getMinimum();
        float gain = (vols / 100.0f) * range + gainControl.getMinimum();
        gainControl.setValue(gain);
      } catch (IllegalArgumentException e) {
        // DO NOTHING
      }
    }
  }

  public static String removeFileEnding(String s) {
    return s.substring(0, s.lastIndexOf("."));
  }

  public static String safeName(String s) {
    if (s.length() > 30) {
      return s.substring(0, 30) + "...";
    }
    return s;
  }

  public synchronized void play() {
    if (!alreadyOpen) {
      try {
        c.open(AudioSystem.getAudioInputStream(f));
        alreadyOpen = true;
      } catch (java.io.IOException | javax.sound.sampled.UnsupportedAudioFileException
          | javax.sound.sampled.LineUnavailableException e) {
        e.printStackTrace();
      }
    }
    c.setMicrosecondPosition(frame);
    c.start();
    setVolume();
  }

  public synchronized void pause() {
    worker.interrupt();
    if (c.isRunning()) {
      c.stop();
      frame = c.getMicrosecondPosition();
    }
  }

  public synchronized long getFrame() {
    if (c != null)
      return c.getMicrosecondPosition();
    return -1L;
  }

  public boolean isPlaying() {
    return c.isRunning();
  }

  public String getAuthor() {
    try {
      return (String) AudioSystem.getAudioFileFormat(f).properties().get("author") == null ? "Unknown"
          : (String) AudioSystem.getAudioFileFormat(f).properties().get("author");
    } catch (UnsupportedAudioFileException | IOException e) {
      // DO NOTHING
    }
    return "";
  }

  public String getTitle() {
    try {
      return (String) AudioSystem.getAudioFileFormat(f).properties().get("title") == null ? f.getName()
          : (String) AudioSystem.getAudioFileFormat(f).properties().get("title");
    } catch (UnsupportedAudioFileException | IOException e) {
      // DO NOTHING
    }
    return "";
  }

  public String getDate() {
    try {
      return AudioSystem.getAudioFileFormat(f).properties().get("date") == null ? "No Date"
          : AudioSystem
              .getAudioFileFormat(f).properties().get("date").toString();
    } catch (UnsupportedAudioFileException | IOException e) {
      // DO NOTHING
    }
    return "No Date";
  }

  public Long getLength() {
    return c.getMicrosecondLength();
  }

  public synchronized void setFrame(long frame) {
    this.frame = frame;
  }

  public synchronized void unload() {
    c.close();
  }

  public synchronized String getTrackName() {
    return f.getName();
  }

  public synchronized boolean loop() {
    if (c.isRunning()) {
      c.loop(Clip.LOOP_CONTINUOUSLY);
      return true;
    }
    return false;
  }
}
