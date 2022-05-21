package project.constants;

import javax.swing.ImageIcon;

/**
 * Resource attributes read from the default resource file for default resources
 * packed with the program.
 */
public class ResourceDistributor {
  public static final String MAIN_RSC_DIR = "resource/newrsc";
  public static final String DISK_PLAYING = MAIN_RSC_DIR + "/disk.png";
  public static final String PLAY_PAUSE = MAIN_RSC_DIR + "/play_button_activated.png";
  public static final String PAUSE_ICO = MAIN_RSC_DIR + "/paused_button_activated.png";
  public static final String APP_LOGO = MAIN_RSC_DIR + "/app-logo.png";
  public static final String PLAY_PAUSE_HOVERED = MAIN_RSC_DIR + "/play_button_hovered.png";

  /**
   * @deprecated This method can only handle ImageIcons and can throw invalid
   *             exceptions if the resource const is not an image.
   * @param rsc The resource to be read.
   * @return The resource returned as an ImageIcon
   */
  @Deprecated
  public ImageIcon getIconResource(ResourceConst rsc) {
    if (rsc.equals(ResourceConst.DISK_PLAYING_SPIN_ICON)) {
      try {
        return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource(DISK_PLAYING).getFile()));
      } catch (NullPointerException e) {
        return new ImageIcon(DISK_PLAYING);
      }
    } else if (rsc.equals(ResourceConst.PLAY_PAUSE_BUTTON)) {
      try {
        return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource(PLAY_PAUSE).getFile()));
      } catch (Exception e) {
        return new ImageIcon(PLAY_PAUSE);
      }
    }
    return null;
  }

  /**
   * @return ImageIcon The Play Button icon from the resource stream (PNG)
   */
  public ImageIcon getPlayButton() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((PLAY_PAUSE)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(PLAY_PAUSE);
    }
  }

  /**
   * @return ImageIcon The Pause Button icon from the resource stream (PNG)
   */
  public ImageIcon getPauseButton() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((PAUSE_ICO)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(PAUSE_ICO);
    }
  }

  /**
   * @return ImageIcon The Play Button icon from the resource stream (PNG)
   */
  public ImageIcon getPlayButtonHovered() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((PLAY_PAUSE_HOVERED)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(PLAY_PAUSE_HOVERED);
    }
  }

  /**
   * @return ImageIcon The disk image that shows as a spinning icon.
   */
  public ImageIcon getDiskPNG() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((DISK_PLAYING)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(DISK_PLAYING);
    }
  }

  /**
   * @return ImageIcon The app logo image.
   */
  public ImageIcon getAppLogo() {
    try {
      return new ImageIcon(
          java.util.Objects.requireNonNull(getClass().getResource(APP_LOGO).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(APP_LOGO);
    }
  }
}
