package project.audio.content;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import project.connection.resource.ResourceFolder;
import project.constants.ColorContent;
import project.constants.ProjectManager;
import project.usables.DeImage;
import strict.RuntimeConstant;

import java.awt.image.BufferedImage;

public class AudioInfoEditor {
  private AudioUtil directedFile;
  private Map<String, String> fullStyle;
  private BufferedImage coverart;
  public static final String FILE_NAME_TOKEN = "fileName", ARTIST_TOKEN = "artist", TITLE_TOKEN = "title",
      GENRE_TOKEN = "genre", YEAR_TOKEN = "year", COMMENT_TOKEN = "comments", ALBUM_TOKEN = "album",
      COMPOSER_TOKEN = "composer";
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

  public AudioInfoEditor() {
    this(null, null, null, null, null, null, null, null, null, null);
    coverart = DeImage.imageIconToBI(RuntimeConstant.runtimeRD.getDiskPNG());
  }

  public AudioInfoEditor(AudioUtil e) {
    fullStyle = new HashMap<>();
    directedFile = e;
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
      coverart = e.getAlbumCoverArt();
    } catch (Exception e1) {
      // DO NOTHING
    }
  }

  public AudioInfoEditor(File ex) {
    fullStyle = new HashMap<>();
    AudioUtil e = new AudioUtil(ex.getAbsolutePath());
    directedFile = e;
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
      coverart = e.getAlbumCoverArt();
    } catch (Exception e1) {
      // DO NOTHING
    }
  }

  /**
   * @return AudioUtil
   */
  public AudioUtil getUtilFile() {
    return directedFile;
  }

  /**
   * @param arg0
   */
  public void setFileName(String arg0) {
    fullStyle.put("fileName", arg0);
  }

  /**
   * @param arg0
   */
  public void setArtist(String arg0) {
    fullStyle.put("artist", arg0);
  }

  /**
   * @param arg0
   */
  public void setTitle(String arg0) {
    fullStyle.put("title", arg0);
  }

  /**
   * @param arg0
   */
  public void setYear(String arg0) {
    fullStyle.put("year", arg0);
  }

  /**
   * @param arg0
   */
  public void setComments(String arg0) {
    fullStyle.put("comments", arg0);
  }

  /**
   * @param arg0
   */
  public void setBitrate(String arg0) {
    fullStyle.put("bitrate", arg0);
  }

  /**
   * @param arg0
   */
  public void setSampleRate(String arg0) {
    fullStyle.put("sampleRate", arg0);
  }

  /**
   * @param arg0
   */
  public void setComposer(String arg0) {
    fullStyle.put("Composer", arg0);
  }

  /**
   * @param arg0
   */
  public void setGenre(String arg0) {
    fullStyle.put("genre", arg0);
  }

  /**
   * @param arg0
   */
  public void setAlbum(String arg0) {
    fullStyle.put("album", arg0);
  }

  /**
   * @return Map<String, String>
   */
  public Map<String, String> getFullStyle() {
    return fullStyle;
  }

  public BufferedImage getCoverArtBI() {
    return coverart;
  }

  private static String getMajorFontTag(String content) {
    return "<font color=\"" + ResourceFolder.pm.get(ProjectManager.KEY_AIE_MAJOR_TAG_HEX_COLOR) + "\">" + content
        + "</font>";
  }

  private static String getMinorFontTag(String content) {
    return "<font color=\"" + ResourceFolder.pm.get(ProjectManager.KEY_AIE_MINOR_TAG_HEX_COLOR) + "\">" + content
        + "</font>";
  }

  /**
   * @return String
   */
  public static String getBlank() {
    StringBuilder sb = new StringBuilder();
    sb.append(
        "<html><body style=\"font-size: 10px;\"><p>");
    sb.append(getMajorFontTag("<b>File Name:</b><br>"));
    sb.append(getMajorFontTag("<b>Artist:</b> <br>"));
    sb.append(getMajorFontTag("<b>Title:</b> <br>"));
    sb.append(getMajorFontTag("<b>Year:</b> <br>"));
    sb.append(getMajorFontTag("<b>Comments:</b> <br>"));
    sb.append(getMajorFontTag("<b>Bitrate:</b> <br>"));
    sb.append(getMajorFontTag("<b>Sample Rate:</b> <br>"));
    sb.append(getMajorFontTag("<b>Composer:</b> <br>"));
    sb.append(getMajorFontTag("<b>Genre:</b> <br>"));
    sb.append(getMajorFontTag("<b>Album:</b> <br>"));
    sb.append(getMajorFontTag("</p></body></html>"));
    return sb.toString();
  }

  /**
   * @param str
   * @return String
   */
  public synchronized String checkSize(String str) {
    /**
     * try {
     * return str.length() >= MAX_LEN ? str.substring(0, 18) + "..." +
     * str.substring(str.length() - 5, str.length())
     * : str;
     * } catch (NullPointerException e) {
     * return "";
     * }
     */
    return str;
  }

  public String noCheck() {
    StringBuilder sb = new StringBuilder();
    sb.append(
        "<html><body style=\"font-size: 10px;\"><p>");
    sb.append(
        getMajorFontTag("<b>File Name:</b> ") + getMinorFontTag(fullStyle.get("fileName")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Artist:</b> ") + getMinorFontTag(fullStyle.get("artist")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Title:</b> ") + getMinorFontTag(fullStyle.get("title")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Year:</b> ") + getMinorFontTag(fullStyle.get("year")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Comments:</b> ") + getMinorFontTag(fullStyle.get("comments")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Bitrate:</b> ") + getMinorFontTag(fullStyle.get("bitrate")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Sample Rate:</b> ") + getMinorFontTag(fullStyle.get("sampleRate")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Composer:</b> ") + getMinorFontTag(fullStyle.get("composer")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Genre:</b> ") + getMinorFontTag(fullStyle.get("genre")) + "<br>");
    sb.append(
        getMajorFontTag("<b>Album:</b> ") + getMinorFontTag(fullStyle.get("album")) + "<br>");
    sb.append("</p></body></html>");
    return sb.toString();
  }

  /**
   * @return String
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(
        "<html><body style=\"font-size: 10px;\"><p>");
    sb.append(
        getMajorFontTag("<b>File Name:</b> ") + getMinorFontTag(checkSize(fullStyle.get("fileName"))) + "<br>");
    sb.append(
        getMajorFontTag("<b>Artist:</b> ") + getMinorFontTag(checkSize(fullStyle.get("artist"))) + "<br>");
    sb.append(
        getMajorFontTag("<b>Title:</b> ") + getMinorFontTag(checkSize(fullStyle.get("title"))) + "<br>");
    sb.append(
        getMajorFontTag("<b>Year:</b> ") + getMinorFontTag(checkSize(fullStyle.get("year"))) + "<br>");
    sb.append(
        getMajorFontTag("<b>Comments:</b> ") + getMinorFontTag(checkSize(fullStyle.get("comments"))) + "<br>");
    sb.append(
        getMajorFontTag("<b>Bitrate:</b> ") + getMinorFontTag(checkSize(fullStyle.get("bitrate"))) + "<br>");
    sb.append(
        getMajorFontTag("<b>Sample Rate:</b> ") + getMinorFontTag(checkSize(fullStyle.get("sampleRate")))
            + "<br>");
    sb.append(
        getMajorFontTag("<b>Composer:</b> ") + getMinorFontTag(checkSize(fullStyle.get("composer"))) + "<br>");
    sb.append(
        getMajorFontTag("<b>Genre:</b> ") + getMinorFontTag(checkSize(fullStyle.get("genre"))) + "<br>");
    sb.append(
        getMajorFontTag("<b>Album:</b> ") + getMinorFontTag(checkSize(fullStyle.get("album"))) + "<br>");
    sb.append("</p></body></html>");

    return sb.toString();
  }
}
