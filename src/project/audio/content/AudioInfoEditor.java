package project.audio.content;

import java.util.Map;
import java.util.HashMap;

public class AudioInfoEditor {
  private Map<String, String> fullStyle;
  public static final String FILE_NAME_TOKEN = "fileName", ARTIST_TOKEN = "artist", TITLE_TOKEN = "title", GENRE_TOKEN = "genre", YEAR_TOKEN = "year", COMMENT_TOKEN = "comment", ALBUM_TOKEN = "album";
  public AudioInfoEditor(String fileName, String artist, String title, String year, String comments, String bitrate,
      String sampleRate, String channels, String genre, String album) {
    fullStyle = new HashMap<>();
    fullStyle.put("fileName", fileName == null ? "" : fileName);
    fullStyle.put("artist", artist == null ? "" : artist);
    fullStyle.put("title", title == null ? "" : title);
    fullStyle.put("year", year == null ? "" : year);
    fullStyle.put("comments", comments == null ? "" : comments);
    fullStyle.put("bitrate", bitrate == null ? "" : bitrate);
    fullStyle.put("sampleRate", sampleRate == null ? "" : sampleRate);
    fullStyle.put("channels", channels == null ? "" : channels);
    fullStyle.put("genre", genre == null ? "" : genre);
    fullStyle.put("album", album == null ? "" : album);
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

  public void setChannels(String arg0) {
    fullStyle.put("channels", arg0);
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
    sb.append("<b>Channels:</b> " + fullStyle.get("channels") + "<br>");
    sb.append("<b>Genre:</b> " + fullStyle.get("genre") + "<br>");
    sb.append("<b>Album:</b> " + fullStyle.get("album") + "<br>");
    sb.append("</p></body></html>");
    return sb.toString();
  }
}
