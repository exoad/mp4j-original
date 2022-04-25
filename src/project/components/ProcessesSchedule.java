package project.components;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class ProcessesSchedule {
  public static void main(String... args) {

    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        try {
          UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarkLaf");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
            | UnsupportedLookAndFeelException e) {
          e.printStackTrace();
        }
      }
    });
    UIManager.put("FileChooser.readOnly", true);
    System.setErr(null);
  }
}
