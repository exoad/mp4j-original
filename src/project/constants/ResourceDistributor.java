package project.constants;

import javax.swing.ImageIcon;

import project.usables.API;

public class ResourceDistributor {
  public static final String MAIN_RSC_DIR = "resource/newrsc";
  public static final String DISK_PLAYING = MAIN_RSC_DIR + "/disk.png";
  public static final String PLAY_PAUSE = MAIN_RSC_DIR + "/play.png";
  public static final String APP_LOGO = MAIN_RSC_DIR + "/app-logo.png";

  /**
   * @deprecated
   * @param rsc
   * @return
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
        API.print(e);
        return new ImageIcon(PLAY_PAUSE);
      }
    }
    return null;
  }

  public ImageIcon getDiskPNG() {
    try {
      return new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource((DISK_PLAYING)).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(DISK_PLAYING);
    }
  }

  public ImageIcon getAppLogo() {
    try {
      return new ImageIcon(
          java.util.Objects.requireNonNull(getClass().getResource(APP_LOGO).getFile()));
    } catch (NullPointerException e) {
      return new ImageIcon(APP_LOGO);
    }
  }
}
