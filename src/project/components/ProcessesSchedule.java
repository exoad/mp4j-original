package project.components;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import project.constants.PreConfig;

public class ProcessesSchedule {
  public static void main(String... args) {
    FlatDarkLaf.setup();
    UIManager.put("FileChooser.readOnly", true);
    System.setErr(null);
  }
}
