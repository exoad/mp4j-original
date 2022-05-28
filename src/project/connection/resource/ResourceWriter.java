package project.connection.resource;

import java.io.File;

import project.constants.ProjectManager;

public class ResourceWriter {
  private ResourceWriter() {}

  public static boolean createFolder(String name) {
    File folder = new File(ProjectManager.EXT_RSC_FOLDER + "\\" + name);
    if(!folder.exists() || !folder.isDirectory()) {
      return folder.mkdir();
    }
    return false;
  }
}
