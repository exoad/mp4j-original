package backend.audioutil;

import backend.audio.Music;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;

import java.io.File;
import java.io.IOException;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public class Player {
  private File f, origF;
  public Clip c;
  private long frame = 0L;
  public float vols;
  private String orig = "";
  private boolean alreadyOpen = false;
  private final Thread worker = new Thread();

  public Player(File f33, float volume) {
    if (f33.getAbsolutePath().endsWith(".mp3")) {
      this.f = Music.convert(f33);
    } else {
      this.f = f33;
    }
    origF = f33;
    orig = safeName(removeFileEnding(f33.getName()));
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
        //System.out.print(f.getAbsolutePath());
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

  private native void stellar(String s);

  public String prettyMetaData() {
    StringBuilder sb = new StringBuilder();
    if (!origF.getName().endsWith(".mp3")) {
      sb.append("<html><div style='text-align: center;'><p>");
      sb.append("<b><u>Track Name:</u></b> ").append(orig).append("<br>");
      sb.append("<b><u>Artist:</u></b> Unsupported<br>");
      sb.append("<b><u>Album:</u></b> Unsupported<br>");
      sb.append("<b><u>Genre:</u></b> Unsupported<br>");
      sb.append("<b><u>Year:</u></b> Unsupported<br>");
      sb.append("</p></div></html>");
    } else {
      try {
        AudioFile fr = AudioFileIO.read(origF);
        Tag tag = fr.getTag();
        sb.append("<html><div style='text-align: center;'><p>");
        sb.append("<b><u>Track Name:</u></b> ").append(orig).append("<br>");
        sb.append("<b><u>Artist:</u></b> ").append(tag.getFirst(org.jaudiotagger.tag.FieldKey.ALBUM_ARTIST).equals("") || tag.getFirst(org.jaudiotagger.tag.FieldKey.ALBUM_ARTIST) == null  ? "Unknown" : tag.getFirst(org.jaudiotagger.tag.FieldKey.ALBUM_ARTIST)).append("<br>");
        sb.append("<b><u>Album:</u></b> ").append(tag.getFirst(org.jaudiotagger.tag.FieldKey.ALBUM).equals("") || tag.getFirst(org.jaudiotagger.tag.FieldKey.ALBUM) == null  ? "Unknown" : tag.getFirst(org.jaudiotagger.tag.FieldKey.ALBUM)).append("<br>");
        sb.append("<b><u>Genre:</u></b> ").append(tag.getFirst(org.jaudiotagger.tag.FieldKey.GENRE).equals("") || tag.getFirst(org.jaudiotagger.tag.FieldKey.GENRE) == null ? "Unknown"
                : tag.getFirst(org.jaudiotagger.tag.FieldKey.GENRE)).append("<br>");
        sb.append("<b><u>Year:</u></b> ").append(tag.getFirst(org.jaudiotagger.tag.FieldKey.YEAR).equals("") || tag.getFirst(org.jaudiotagger.tag.FieldKey.YEAR) == null? "Unknown"
                : tag.getFirst(org.jaudiotagger.tag.FieldKey.YEAR)).append("<br>");
        sb.append("</p></div></html>");
      } catch (CannotReadException | IOException | TagException | ReadOnlyFileException
          | InvalidAudioFrameException | NullPointerException e) {
        //e.printStackTrace();
        sb.append("<html><div style='text-align: center;'><p>");
        sb.append("<b><u>Track Name:</u></b> ").append(orig).append("<br>");
        sb.append("<b><u>Artist:</u></b> Confounded<br>");
        sb.append("<b><u>Album:</u></b> Confounded<br>");
        sb.append("<b><u>Genre:</u></b> Confounded<br>");
        sb.append("<b><u>Year:</u></b> Confounded<br>");
        sb.append("</p></div></html>");
      }
    }
    return sb.toString();
  }

  public ImageIcon getCoverArt() {
    return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource("/icons/others/disk.png")));
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
