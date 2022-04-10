package project.components;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarculaLaf;

import project.constants.PreConfig;

public class ProcessesSchedule {
  public static void main(String... args) {
    if(PreConfig.USE_NATIVE_LAF) {
      try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
          | UnsupportedLookAndFeelException e) {
        e.printStackTrace();
      }
    } else {
      FlatDarculaLaf.setup();

    }
    UIManager.put("FileChooser.readOnly", true);
    System.setErr(null);
  }
}
