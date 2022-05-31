package project.connection.resource;

import java.io.File;

import project.constants.ProjectManager;
import strict.RuntimeConstant;

public class ResourceWriter {
  private ResourceWriter() {}

  public static boolean createFolder(String name) {
    File folder = new File(ProjectManager.EXT_RSC_FOLDER + RuntimeConstant.FILE_SLASH + name);
    if(!folder.exists() || !folder.isDirectory()) {
      return folder.mkdir();
    }
    return false;
  }
}
