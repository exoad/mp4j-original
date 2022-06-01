package project.connection.resource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.image.BufferedImage;
import project.constants.ProjectManager;
import project.usables.DeImage;
import strict.RuntimeConstant;

/**
 * ResourceFolder is a general class that holds information about
 * the external resource folder.
 * 
 * The resource folder is named under the name "halcyon-mp4j" it is
 * a constant viewed in {@link project.constants.ProjectManager}
 * 
 * For example, it can read and write to the resource folder,
 * and read properties file using
 * {@link project.connection.resource.PropertiesManager}
 * 
 * @author Jack Meng
 * @since 2.1
 */
public class ResourceFolder {
  private ResourceFolder() {
  }

  /**
   * Represents the global instance of the PropertiesManager for the
   * user-modifiable "mp4j.properties" file
   * 
   * The program has one global instance to reduce overhead.
   */
  public static final PropertiesManager pm = new PropertiesManager(ProjectManager.getDefaultPropertiesCustomization(),
      ProjectManager.getAllowedPropertiesCustomization(),
      ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + "mp4j.properties");

  /**
   * An internal method used to retrieve a random string of letters
   * based on the parameterized length.
   * 
   * @param length The length of the random string
   * @return String The random string
   * @deprecated This method is planned to be moved to {@link project.Utils}
   */
  private static String getRandomString(int length) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append((char) (Math.random() * 26 + 'a'));
    }
    return sb.toString();
  }

  /**
   * Based on the folders needed in {@link project.constants.ProjectManager},
   * it checks if these subfolders exists from within the main resource folder.
   * 
   * If a folder does not exist, it will be created under the main resource
   * folder.
   */
  public static void checkResourceFolder() {
    File folder = new File(ProjectManager.EXT_RSC_FOLDER);
    if (!folder.isDirectory() || !folder.exists()) {
      if (folder.mkdir()) {
        System.out.println("LOG > Resource folder created.");
      } else {
        System.out.println("LOG > Resource folder creation failed.");
      }
    }
  }

  /**
   * This is a standard method to write a log file to the resource's log folder.
   * 
   * This can be used for anything, however is not a place for things that are
   * cached to be written to.
   * 
   * It will only write String based files to the files.
   * 
   * @param folderName The name of the folder to write to
   * @param f          The file to write
   */
  public static void writeLog(String folderName, String f) {
    if (!new File(ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + folderName).isDirectory()) {
      new File(ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + folderName).mkdir();
    }
    File logFile = new File(
        ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + folderName + RuntimeConstant.FILE_SLASH
            + System.currentTimeMillis() + "_log.mp4jlog");
    try {
      logFile.createNewFile();
      FileWriter writer = new FileWriter(logFile);
      writer.write(f);
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Writes a buffered-image to the resource folder.
   * 
   * Planned:
   * - Make it so that it will write to the "cache" folder
   * of the main resource folder.
   * 
   * @param bi The buffered image to write
   * @return String The name of the file that was written
   */
  public static String writeBufferedImageCacheFile(BufferedImage bi) {
    DeImage.write(bi, ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + getRandomString(10) + ".png");
    return ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + getRandomString(10) + ".png";
  }
}
