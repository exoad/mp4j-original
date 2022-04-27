package project.components;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.FlatDarkLaf;
public class ProcessesSchedule {
  public static void main(String... args) {

    UIManager.put("FileChooser.readOnly", true);
    System.setErr(null);
  }
}
