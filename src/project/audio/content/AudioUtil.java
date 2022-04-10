package project.audio.content;

import java.io.File;
import java.io.IOException;

import project.Utils;

import com.mpatric.mp3agic.*;

public class AudioUtil extends File {
  public AudioUtil(String pathname) {
    super(pathname);
  }

  public String getAudioType() {
    return Utils.getExtension(this);
  }

  private boolean checkEmptiness(String s) {
    return s.isEmpty() || s == null || "".equals(s) || " ".equals(s);
  }

  public synchronized String getFileName() {
    String name = getName();
    if (checkEmptiness(name)) {
      return "";
    }
    return name;
  }

  public synchronized String getArtist() {
    try {
      Mp3File mp3file = new Mp3File(this);
      ID3v2 id3v2tag = mp3file.getId3v2Tag();
      String artist = id3v2tag.getArtist();
      if (checkEmptiness(artist)) {
        return "";
      }
      return artist;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public synchronized String getTitle() {
    try {
      Mp3File mp3file = new Mp3File(this);
      ID3v2 id3v2tag = mp3file.getId3v2Tag();
      String title = id3v2tag.getTitle();
      if (checkEmptiness(title)) {
        return "";
      }
      return title;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public synchronized String getTrack() {
    try {
      Mp3File mp3file = new Mp3File(this);
      ID3v2 id3v2tag = mp3file.getId3v2Tag();
      String track = id3v2tag.getTrack();
      if (checkEmptiness(track)) {
        return "";
      }
      return track;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public boolean isMP3() {
    try {
      Mp3File m = new Mp3File(this);
      if(!checkEmptiness(m.getVersion()) || !checkEmptiness(m.getModeExtension()) || m.getBitrate() != 0) return true;
    } catch (UnsupportedTagException | InvalidDataException | IOException e) {
      return false;
    }
    return false;
  }
}
