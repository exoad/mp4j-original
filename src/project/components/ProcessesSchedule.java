package project.components;

import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarkLaf;

public class ProcessesSchedule {
  public static void main(String... args) {
    FlatDarkLaf.setup();
    UIManager.put("FileChooser.readOnly", true);
    System.setErr(null);
  }
}
