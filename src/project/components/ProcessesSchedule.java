package project.components;

import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.*;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import project.constants.ProjectManager;

public class ProcessesSchedule {
  
  /** 
   * @param args
   */
  public static void main(String... args) {
    UIManager.put("FileChooser.readOnly", true);
    if (ProjectManager.PRODUCTION_STYLE) {
      FlatLightLaf.setup();
    } else {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
          | UnsupportedLookAndFeelException e) {
        e.printStackTrace();
      }
    }
  }
}
