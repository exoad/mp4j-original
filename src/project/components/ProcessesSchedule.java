package project.components;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

import project.components.windows.LoggerWindow;

public class ProcessesSchedule {
  public static void main(String... args) {
    new LoggerWindow().run();
    ///FlatDarkLaf.setup();
    UIManager.put("FileChooser.readOnly", true);    
  }
}
