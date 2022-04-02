package backend.audioutil;

import backend.audio.Music;
import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Player {
  private File f, origF;
  private long frame = 0L;
  public float vols;
  private String orig = "";
  private boolean alreadyOpen = false;
  private final Thread worker = new Thread();
  public StreamPlayer sp;

  public Player(File f33, float volume) {
    if (f33.getAbsolutePath().endsWith(".mp3")) {
      this.f = Music.convert(f33);
    } else {
      this.f = f33;
    }
    sp = new StreamPlayer();
  }

  private float convert(float f) {
    return (f - 0.01f) / 3.99f;
  }

  public void open() {
    try {
      if (!alreadyOpen) {
        sp.open(f.getAbsolutePath());
        alreadyOpen = true;
      }
    } catch (StreamPlayerException e) {
      // DO NOTHING
    }
  }

  public synchronized void play() {
    if (!alreadyOpen) {
      open();
    }
    try {
      sp.play();
    } catch (StreamPlayerException e) {
      // DO NOTHING
    }
  }

  public synchronized void pause() {
    if (!alreadyOpen) {
      open();
    }
    sp.pause();
  }

  public synchronized void setVolume(float vol) {
    this.vols = convert(vol);
    sp.setGain(vols);
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

}
