package project.audio.content;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.mpatric.mp3agic.*;

import project.Utils;
import project.usables.ResourceGrabber;

public class AudioUtil extends File {
  public AudioUtil(String pathname) {
    super(pathname);
  }

  public String getAudioType() {
    return Utils.getExtension(this);
  }

  private synchronized boolean checkEmptiness(String s) {
    return s == null || s.isEmpty();
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

  public synchronized File getAlbumCoverArt() {
    try {
      Mp3File mp3File = new Mp3File(this);
      if(mp3File.hasId3v2Tag()) {
        ID3v2 tag = mp3File.getId3v2Tag();
        byte[] img = tag.getAlbumImage();
        if(img != null) {          RandomAccessFile file = new RandomAccessFile(getFileName() + "__ALBPIC", "rw");
          file.write(img);
          file.close();
        }
      }
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return new File(ResourceGrabber.getDefaultCoverArt().getFile());
    }
    return new File(ResourceGrabber.getDefaultCoverArt().getFile());
  }

  public synchronized String getYear() {
    try {
      Mp3File mp3file = new Mp3File(this);
      ID3v2 id3v2tag = mp3file.getId3v2Tag();
      String year = id3v2tag.getYear();
      if (checkEmptiness(year)) {
        return "";
      }
      return year;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public synchronized String getComments() {
    try {
      Mp3File mp3file = new Mp3File(this);
      ID3v2 id3v2tag = mp3file.getId3v2Tag();
      String comments = id3v2tag.getComment();
      if (checkEmptiness(comments)) {
        return "";
      }
      return comments;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public synchronized String getBitrate() {
    try {
      Mp3File mp3file = new Mp3File(this);
      String bitrate = "" + mp3file.getBitrate();
      if (checkEmptiness(bitrate)) {
        return "";
      }
      return bitrate;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public synchronized String getSampleRate() {
    try {
      Mp3File mp3file = new Mp3File(this);
      String sampleRate = "" + mp3file.getSampleRate();
      if (checkEmptiness(sampleRate)) {
        return "";
      }
      return sampleRate;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public synchronized String getComposer() {
    try {
      Mp3File mp3file = new Mp3File(this);
      ID3v2 id3v2tag = mp3file.getId3v2Tag();
      String composer = id3v2tag.getComposer();
      if (checkEmptiness(composer)) {
        return "";
      }
      return composer;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public synchronized String getGenre() {
    try {
      Mp3File mp3file = new Mp3File(this);
      ID3v2 id3v2tag = mp3file.getId3v2Tag();
      String genre = id3v2tag.getGenreDescription();
      if (checkEmptiness(genre)) {
        return "";
      }
      return genre;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public synchronized String getAlbum() {
    try {
      Mp3File mp3file = new Mp3File(this);
      ID3v2 id3v2tag = mp3file.getId3v2Tag();
      String album = id3v2tag.getAlbum();
      if (checkEmptiness(album)) {
        return "";
      }
      return album;
    } catch (IOException | UnsupportedTagException | InvalidDataException e) {
      e.printStackTrace();
      return "";
    }
  }

  public boolean isMP3() {
    try {
      Mp3File m = new Mp3File(this);
      if (!checkEmptiness(m.getVersion()) || !checkEmptiness(m.getModeExtension())
          || m.getBitrate() != 0)
        return true;
    } catch (UnsupportedTagException | InvalidDataException | IOException e) {
      return false;
    }
    return false;
  }
}
