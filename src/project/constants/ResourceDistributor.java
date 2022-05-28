package project.constants;

import javax.swing.ImageIcon;

/**
 * Resource attributes read from the default resource file for default resources
 * packed with the program.
 */
public class ResourceDistributor {
  public static final String MAIN_RSC_DIR = "resource/newrsc";
  public static final String OUTSIDE_RSC_DIR = "resource/external";
  public static final String DISK_PLAYING = MAIN_RSC_DIR + "/disk.png";
  public static final String PLAY_PAUSE = MAIN_RSC_DIR + "/play.png";
  public static final String PAUSE_ICO = MAIN_RSC_DIR + "/play.png";
  public static final String APP_LOGO = MAIN_RSC_DIR + "/logo.png";
  public static final String OK_BOX = MAIN_RSC_DIR + "/ok_box.png";
  public static final String LOOP_BOX = MAIN_RSC_DIR + "/loop_box.png";
  public static final String LOOP_BOX_HOVERED = MAIN_RSC_DIR + "/loop_box_hovered.png";
  public static final String GITHUB_DARK = OUTSIDE_RSC_DIR + "/github_dark.png";
  public static final String GITHUB_LIGHT = OUTSIDE_RSC_DIR + "/github_light.png";
  public static final String UNKNOWN_BOX = MAIN_RSC_DIR + "/unknown_box.png";
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
   * @return Retrieves an empty box
   */
  public ImageIcon getEmptyBox() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((UNKNOWN_BOX)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(UNKNOWN_BOX);
    }
  }

  /**
   * @return a box with a checkmark
   */
  public ImageIcon getOkBox() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((OK_BOX)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(OK_BOX);
    }
  }

  /**
   * @return a box with a loop icon
   */
  public ImageIcon getLoopBoxHovered() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((LOOP_BOX_HOVERED)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(LOOP_BOX_HOVERED);
    }
  }

  /**
   * @return a box with a loop icon
   */
  public ImageIcon getLoopBox() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((LOOP_BOX)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(LOOP_BOX);
    }
  }

  /**
   * @return a box with a github logo
   */
  public ImageIcon getGitHubLight() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((GITHUB_LIGHT)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(GITHUB_LIGHT);
    }
  }

  /**
   * @return a box with a github logo
   */
  public ImageIcon getGitHubDark() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((GITHUB_DARK)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(GITHUB_DARK);
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
