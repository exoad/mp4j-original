package project.connection.resource;

import java.io.File;
import java.io.FileWriter;

import project.constants.ProjectManager;

public class ResourceFolder {
  private ResourceFolder() {
  }
  public static final PropertiesManager pm = new PropertiesManager(ProjectManager.getDefaultPropertiesCustomization(),
      ProjectManager.getAllowedPropertiesCustomization(), ProjectManager.EXT_RSC_FOLDER + "/mp4j.properties");
  static {
    pm.open();
  }

  public static void checkResourceFolder() {
    File folder = new File(ProjectManager.EXT_RSC_FOLDER);
    if (!folder.isDirectory()) {
      if (!folder.mkdir()) {
        System.out.println("LOG > Resource folder creation failed.");
      } else {
        System.out.println("LOG > Resource folder created.");
      }
    }
  }
}
