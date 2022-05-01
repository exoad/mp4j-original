package project.constants;

import javax.swing.ImageIcon;

public class ResourceDistributor {
  public static final String MAIN_RSC_DIR = "resource/newsrc";
  public static final String DISK_PLAYING = MAIN_RSC_DIR + "/disk.png";
  public static final String PLAY_PAUSE = MAIN_RSC_DIR + "/play.png";

  public ImageIcon getIconResource(ResourceConst rsc) {
    if(rsc.equals(ResourceConst.DISK_PLAYING_SPIN_ICON)) {
      try {
        return new ImageIcon(getClass().getResource(DISK_PLAYING).getFile());
      } catch (Exception e) {
        return new ImageIcon(DISK_PLAYING);
      }
    } else if (rsc.equals(ResourceConst.PLAY_PAUSE_BUTTON)) {
      try {
        return new ImageIcon(getClass().getResource(PLAY_PAUSE).getFile());
      } catch (Exception e) {
        return new ImageIcon(PLAY_PAUSE);
      }
    }
    return null;
  }
}
