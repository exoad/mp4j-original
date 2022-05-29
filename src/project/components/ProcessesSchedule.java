package project.components;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import project.connection.resource.ResourceFolder;
import project.constants.ProjectManager;

public class ProcessesSchedule {

  /**
   * @param args
   */
  public static void main(String... args) {
    UIManager.put("FileChooser.readOnly", true);
    try {
      if (ResourceFolder.pm.get(ProjectManager.KEY_APPLICATION_TOTAL_LAF_MODE).equals("1")) {
        UIManager.setLookAndFeel(new FlatLightLaf());
      } else if (ResourceFolder.pm.get(ProjectManager.KEY_APPLICATION_TOTAL_LAF_MODE).equals("0")){
        UIManager.setLookAndFeel(new FlatDarkLaf());
      }
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

  }
}
