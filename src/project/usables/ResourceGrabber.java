package project.usables;

import java.net.MalformedURLException;
import java.net.URL;

public class ResourceGrabber {
  private ResourceGrabber() {}
  public static URL getDefaultCoverArt() {
    try {
    return ResourceGrabber.class.getClass().getResource("resource/icons/others/disk.png");
    } catch (NullPointerException e) {
      try {
        return new URL("resource/icons/others/disk.png");
      } catch (MalformedURLException e1) {
        e1.printStackTrace();
      }
    }
    return null;
  }
}
