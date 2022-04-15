package project.audio.content;

import java.util.Map;
import java.io.File;
import java.util.HashMap;

public class AudioInfoEditor {
  private Map<String, String> fullStyle;
  public static final String FILE_NAME_TOKEN = "fileName", ARTIST_TOKEN = "artist", TITLE_TOKEN = "title",
      GENRE_TOKEN = "genre", YEAR_TOKEN = "year", COMMENT_TOKEN = "comment", ALBUM_TOKEN = "album",
      COMPOSER_TOKEN = "compsoer";

  @Deprecated
  /**
   * @deprecated Use the other provided constructors that take in a specific file instead of pure arguments
   * @param fileName
   * @param artist
   * @param title
   * @param year
   * @param comments
   * @param bitrate
   * @param sampleRate
   * @param Composer
   * @param genre
   * @param album
   */
  public AudioInfoEditor(String fileName, String artist, String title, String year, String comments, String bitrate,
      String sampleRate, String Composer, String genre, String album) {
    fullStyle = new HashMap<>();
    fullStyle.put("fileName", fileName == null ? "" : fileName);
    fullStyle.put("artist", artist == null ? "" : artist);
    fullStyle.put("title", title == null ? "" : title);
    fullStyle.put("year", year == null ? "" : year);
    fullStyle.put("comments", comments == null ? "" : comments);
    fullStyle.put("bitrate", bitrate == null ? "" : bitrate);
    fullStyle.put("sampleRate", sampleRate == null ? "" : sampleRate);
    fullStyle.put("composer", Composer == null ? "" : Composer);
    fullStyle.put("genre", genre == null ? "" : genre);
    fullStyle.put("album", album == null ? "" : album);
  }

  public AudioInfoEditor(AudioUtil e) {
    fullStyle = new HashMap<>();
    try {
      fullStyle.put("fileName", e.getFileName() == null ? "" : e.getFileName());
      fullStyle.put("artist", e.getArtist() == null ? "" : e.getArtist());
      fullStyle.put("title", e.getTitle() == null ? "" : e.getTitle());
      fullStyle.put("year", e.getYear() == null ? "" : e.getYear());
      fullStyle.put("comments", e.getComments() == null ? "" : e.getComments());
      fullStyle.put("bitrate", e.getBitrate() == null ? "" : e.getBitrate());
      fullStyle.put("sampleRate", e.getSampleRate() == null ? "" : e.getSampleRate());
      fullStyle.put("composer", e.getComposer() == null ? "" : e.getComposer());
      fullStyle.put("genre", e.getGenre() == null ? "" : e.getGenre());
      fullStyle.put("album", e.getAlbum() == null ? "" : e.getAlbum());
    } catch (Exception e1) {
      System.out.println("Bruhhed");
    }
  }

  public AudioInfoEditor(File ex) {
    fullStyle = new HashMap<>();
    AudioUtil e = new AudioUtil(ex.getAbsolutePath());
    try {
      fullStyle.put("fileName", e.getFileName() == null ? "" : e.getFileName());
      fullStyle.put("artist", e.getArtist() == null ? "" : e.getArtist());
      fullStyle.put("title", e.getTitle() == null ? "" : e.getTitle());
      fullStyle.put("year", e.getYear() == null ? "" : e.getYear());
      fullStyle.put("comments", e.getComments() == null ? "" : e.getComments());
      fullStyle.put("bitrate", e.getBitrate() == null ? "" : e.getBitrate());
      fullStyle.put("sampleRate", e.getSampleRate() == null ? "" : e.getSampleRate());
      fullStyle.put("composer", e.getComposer() == null ? "" : e.getComposer());
      fullStyle.put("genre", e.getGenre() == null ? "" : e.getGenre());
      fullStyle.put("album", e.getAlbum() == null ? "" : e.getAlbum());
    } catch (Exception e1) {
      System.out.println("Bruhhed");
    }
    System.out.println(fullStyle.toString());
  }

  public void setFileName(String arg0) {
    fullStyle.put("fileName", arg0);
  }

  public void setArtist(String arg0) {
    fullStyle.put("artist", arg0);
  }

  public void setTitle(String arg0) {
    fullStyle.put("title", arg0);
  }

  public void setYear(String arg0) {
    fullStyle.put("year", arg0);
  }

  public void setComments(String arg0) {
    fullStyle.put("comments", arg0);
  }

  public void setBitrate(String arg0) {
    fullStyle.put("bitrate", arg0);
  }

  public void setSampleRate(String arg0) {
    fullStyle.put("sampleRate", arg0);
  }

  public void setComposer(String arg0) {
    fullStyle.put("Composer", arg0);
  }

  public void setGenre(String arg0) {
    fullStyle.put("genre", arg0);
  }

  public void setAlbum(String arg0) {
    fullStyle.put("album", arg0);
  }

  public Map<String, String> getFullStyle() {
    return fullStyle;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("<html><body style=\"font-family: 'Courier New', Courier, monospace; font-size: 12px;\">");
    sb.append("<p><b>File Name:</b> " + fullStyle.get("fileName") + "<br>");
    sb.append("<b>Artist:</b> " + fullStyle.get("artist") + "<br>");
    sb.append("<b>Title:</b> " + fullStyle.get("title") + "<br>");
    sb.append("<b>Year:</b> " + fullStyle.get("year") + "<br>");
    sb.append("<b>Comments:</b> " + fullStyle.get("comments") + "<br>");
    sb.append("<b>Bitrate:</b> " + fullStyle.get("bitrate") + "<br>");
    sb.append("<b>Sample Rate:</b> " + fullStyle.get("sampleRate") + "<br>");
    sb.append("<b>Composer:</b> " + fullStyle.get("Composer") + "<br>");
    sb.append("<b>Genre:</b> " + fullStyle.get("genre") + "<br>");
    sb.append("<b>Album:</b> " + fullStyle.get("album") + "<br>");
    sb.append("</p></body></html>");
    return sb.toString();
  }
}
