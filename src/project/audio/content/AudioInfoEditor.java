package project.audio.content;

import java.util.Map;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class AudioInfoEditor {
  private Map<String, String> fullStyle;
  public static final String FILE_NAME_TOKEN = "fileName", ARTIST_TOKEN = "artist", TITLE_TOKEN = "title",
      GENRE_TOKEN = "genre", YEAR_TOKEN = "year", COMMENT_TOKEN = "comment", ALBUM_TOKEN = "album",
      COMPOSER_TOKEN = "compsoer";
  protected static final int MAX_LEN = 40;
  @Deprecated
  /**
   * @deprecated Use the other provided constructors that take in a specific file
   *             instead of pure arguments
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

  private String getEmptyTokens(String len) {
    int tokens = MAX_LEN - len.length();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < tokens; i++) {
      sb.append(" ");
    }
    return sb.toString();
  }

  public static String getBlank() {
    StringBuilder sb = new StringBuilder();
    sb.append(
        "<html><body style=\"font-family: 'Arial', Arial, monospace; font-size: 10px;\">");
    sb.append("<p><b>File Name:</b> <br>");
    sb.append("<b>Artist:</b> <br>");
    sb.append("<b>Title:</b> <br>");
    sb.append("<b>Year:</b> <br>");
    sb.append("<b>Comments:</b> <br>");
    sb.append("<b>Bitrate:</b> <br>");
    sb.append("<b>Sample Rate:</b> <br>");
    sb.append("<b>Composer:</b> <br>");
    sb.append("<b>Genre:</b> <br>");
    sb.append("<b>Album:</b> <br>");
    sb.append("</p></body></html>");
    return sb.toString();
  }
  


  private synchronized String checkSize(String str) {
    try {
      System.out.println(str);
      return str.length() >= MAX_LEN ? str.substring(0, 8) + "..." + str.substring(str.length() - 5, str.length()) : str;
    } catch (NullPointerException e) {
      return "";
    }
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(
        "<html><body style=\"font-family: 'Arial', Arial, monospace; font-size: 10px;\">");
    sb.append("<p><b>File Name:</b> " + checkSize(fullStyle.get("fileName")) + "<br>");
    sb.append("<b>Artist:</b> " + checkSize(fullStyle.get("artist")) + "<br>");
    sb.append("<b>Title:</b> " + checkSize(fullStyle.get("title")) + "<br>");
    sb.append("<b>Year:</b> " + checkSize(fullStyle.get("year")) + "<br>");
    sb.append("<b>Comments:</b> " + checkSize(fullStyle.get("comments")) + "<br>");
    sb.append("<b>Bitrate:</b> " + checkSize(fullStyle.get("bitrate")) + "<br>");
    sb.append("<b>Sample Rate:</b> " + checkSize(fullStyle.get("sampleRate")) + "<br>");
    sb.append("<b>Composer:</b> " + checkSize(fullStyle.get("Composer")) + "<br>");
    sb.append("<b>Genre:</b> " + checkSize(fullStyle.get("genre")) + "<br>");
    sb.append("<b>Album:</b> " + checkSize(fullStyle.get("album")) + "<br>");
    sb.append("</p></body></html>");
    return sb.toString();
  }
}
