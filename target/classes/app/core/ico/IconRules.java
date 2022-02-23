package app.core.ico;

import javax.swing.*;
import javax.swing.filechooser.FileView;
import java.io.File;
import java.util.Hashtable;

/**
 * <p>
 * This class will handle all exception rules for the icons without the 
 * internal classes having to attempt for further URL resourcing.
 * </p>
 * @since 1.0.1
 * @author Jack Meng
 */
public class IconRules extends FileView {
  private Hashtable<String, ImageIcon> icons = new Hashtable<>();

  public IconRules() {
    icons.put(".mp3", new ImageIcon(getClass().getResource("/icons/audio-type/mp3-type.png")));
    icons.put(".wav", new ImageIcon(getClass().getResource("/icons/audio-type/wav-type.png")));
    icons.put(".aiff", new ImageIcon(getClass().getResource("/icons/audio-type/aiff-type.png")));
    icons.put(".aif", new ImageIcon(getClass().getResource("/icons/audio-type/aiff-type.png")));
    icons.put(".au", new ImageIcon(getClass().getResource("/icons/audio-type/au-type.png")));
    icons.put("null", new ImageIcon(getClass().getResource("/icons/audio-type/unknown-type.png")));
    icons.put("folder", new ImageIcon(getClass().getResource("/icons/others/file_select_folder_icon.png")));
  }

  /**
   * <p>Returns the value within the hashtable found with the given key</p>
   * @param token The token value to search for 
   * @return an ImageIcon of the original keys in the HashTable
   */
  public ImageIcon fetchByKey(String token) {
    return icons.get(token);
  }

  /**
   * @param file
   * @return The file view icon for the original file type in the fileviewer
   */
  @Override
  public Icon getIcon(File file) {
    String fileName = file.getName();
    if(file.isDirectory()) {
      return fetchByKey("folder");
    }
    if(fileName.endsWith(".mp3")) {
      return fetchByKey(".mp3");
    } else if(fileName.endsWith(".wav")) {
      return fetchByKey(".wav");
    } else if(fileName.endsWith(".aiff")) {
      return fetchByKey(".aiff");
    } else if(fileName.endsWith(".aif")) {
      return fetchByKey(".aif");
    } else if(fileName.endsWith(".au")) {
      return fetchByKey(".au");
    } else {
      return fetchByKey("null");
    }
  }


}
