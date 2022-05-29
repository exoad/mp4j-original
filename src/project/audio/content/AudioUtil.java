package project.audio.content;

import com.goxr3plus.streamplayer.stream.StreamPlayer;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;

import java.awt.image.BufferedImage;

import project.Utils;
import project.usables.DeImage;
import strict.RuntimeConstant;

import java.io.File;
import java.io.IOException;


public class AudioUtil extends File {
  protected static final int MAX_LEN = 80;
  private transient Tag t;
  private transient AudioHeader h;

  public AudioUtil(String pathname) {
    super(pathname);
    AudioFile afio = null;
    try {
      afio = AudioFileIO.read(new File(pathname));
    } catch (CannotReadException | IOException | TagException | ReadOnlyFileException | InvalidAudioFrameException e) {
      e.printStackTrace();
    }
    if (afio != null) {
      t = afio.getTag();
      h = afio.getAudioHeader();
    }
  }

  /**
   * @return String
   */
  public String getAudioType() {
    return Utils.getExtension(this);
  }

  /**
   * @param s
   * @return boolean
   */
  private synchronized boolean checkEmptiness(String s) {
    return s == null || s.isEmpty();
  }

  /**
   * @return String
   */
  public synchronized String getFileName() {
    String name = getName();
    if (checkEmptiness(name)) {
      return "";
    }
    return name;
  }

  /**
   * @param str
   * @return String
   */
  public static synchronized String sized(String str) {
    try {
      return str.length() >= MAX_LEN ? str.substring(0, 8) + "..." + str.substring(str.length() - 5, str.length())
          : str;
    } catch (NullPointerException e) {
      return "";
    }
  }

  /**
   * @return String
   */
  public synchronized String getArtist() {
    String artist = t.getFirst(FieldKey.ARTIST);
    if (checkEmptiness(artist)) {
      return "";
    }
    return sized(artist);
  }

  /**
   * @return String
   */
  public synchronized String getTitle() {
    String title = t.getFirst(FieldKey.TITLE);
    if (checkEmptiness(title)) {
      return "";
    }
    return sized(title);
  }

  /**
   * @return String
   */
  public synchronized String getTrack() {
    String track = t.getFirst(FieldKey.TRACK);
    if (checkEmptiness(track)) {
      return "";
    }
    return sized(track);
  }

  /**
   * @return File
   */
  public synchronized BufferedImage getAlbumCoverArt() {
    BufferedImage img = getDefaultAlbumCoverArt();
    if (getAbsolutePath().endsWith(".mp3")) {
      MP3File mp = null;
      try {
        mp = new MP3File(this);
      } catch (IOException | TagException | ReadOnlyFileException | CannotReadException
          | InvalidAudioFrameException e1) {
        e1.printStackTrace();
      }
      if (mp.getTag().getFirstArtwork() != null) {
        try {
          img = (BufferedImage) mp.getTag().getFirstArtwork().getImage();
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        return img;
      }
    }
    return img;
  }

  public static synchronized BufferedImage getDefaultAlbumCoverArt() {
    return DeImage.imageIconToBI(RuntimeConstant.runtimeRD.getDiskPNG());
  }

  /**
   * @return String
   */
  public synchronized String getYear() {
    String year = t.getFirst(FieldKey.YEAR);
    if (checkEmptiness(year)) {
      return "";
    }
    return sized(year);
  }

  /**
   * @return String
   */
  public synchronized String getComments() {
    String comments = t.getFirst(FieldKey.COMMENT);
    if (checkEmptiness(comments)) {
      return "";
    }
    return sized(comments);
  }

  /**
   * @return String
   */
  public synchronized String getBitrate() {
    String bitrate = h.getBitRate() + "";
    if (checkEmptiness(bitrate)) {
      return "";
    }
    return sized(bitrate);
  }

  /**
   * @return String
   */
  public synchronized String getSampleRate() {
    String sampleRate = h.getSampleRate() + "";
    if (checkEmptiness(sampleRate)) {
      return "";
    }
    return sized(sampleRate);
  }

  /**
   * @return String
   */
  public synchronized String getComposer() {
    String composer = t.getFirst(FieldKey.COMPOSER);
    if (checkEmptiness(composer)) {
      return "";
    }
    return sized(composer);
  }

  /**
   * @param sp
   * @param currVol
   */
  public static void fadeOut(StreamPlayer sp, long currVol) {
    long old = currVol;
    new Thread(() -> {
      long ll = currVol;
      while (currVol > 0) {
        ll -= 2.5;
        sp.setGain(VolumeConversion.convertVolume(ll));
        try {
          Thread.sleep(50);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        if (ll > 0) {
          break;
        }
      }
      sp.stop();
      sp.setGain(VolumeConversion.convertVolume(old));
      System.out.println(VolumeConversion.convertVolume(currVol));
    }).start();
  }

  /**
   * @return String
   */
  public synchronized String getGenre() {
    String genre = t.getFirst(FieldKey.GENRE);
    if (checkEmptiness(genre)) {
      return "";
    }
    return sized(genre);
  }

  /**
   * @return String
   */
  public synchronized String getAlbum() {
    String album = t.getFirst(FieldKey.ALBUM);
    if (checkEmptiness(album)) {
      return "";
    }
    return sized(album);
  }

  /**
   * @return boolean
   */
  public boolean isMP3() {
    return getAudioType().equals("mp3");
  }

}
