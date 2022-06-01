package project.connection.resource;

import java.io.File;

import project.constants.ProjectManager;

/**
 * ResourceWriter is used to write things to the external
 * resource folder.
 * 
 * However, for now it could be moved to
 * {@link project.connection.resource.ResourceFolder}
 * 
 * @author Jack Meng
 * @since 2.1
 * @deprecated Moving to {@link project.connection.resource.ResourceFolder}
 */
@Deprecated(since = "2.1", forRemoval = true)
public class ResourceWriter {
  private ResourceWriter() {
  }

  /**
   * Creates a folder in the standard external resource folder.
   * 
   * @param name The name of the folder (can be a subdirectory)
   * @return File The folder that was created
   */
  public static boolean createFolder(String name) {
    File folder = new File(ProjectManager.EXT_RSC_FOLDER + "\\" + name);
    if (!folder.exists() || !folder.isDirectory()) {
      return folder.mkdir();
    }
    return false;
  }
}
