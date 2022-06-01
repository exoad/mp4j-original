package project.components;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import project.connection.resource.ResourceFolder;
import project.constants.ProjectManager;

/**
 * Prepares the program especially any GUI
 * related tasks before hand
 * 
 * @author Jack Meng
 * @since 2.0
 */
public class ProcessesSchedule {

  private ProcessesSchedule() {
  }

  /**
   * The main method sets things related to the GUI up.
   * 
   * @param args No arguments are allowed to be taken
   */
  public static void main(String... args) {
    UIManager.put("FileChooser.readOnly", true);
    try {
      if (ResourceFolder.pm.get(ProjectManager.KEY_APPLICATION_TOTAL_LAF_MODE).equals("1")) {
        UIManager.setLookAndFeel(new FlatLightLaf());
      } else if (ResourceFolder.pm.get(ProjectManager.KEY_APPLICATION_TOTAL_LAF_MODE).equals("0")) {
        UIManager.setLookAndFeel(new FlatDarkLaf());
      }
    } catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

  }
}
