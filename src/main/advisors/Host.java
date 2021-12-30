package main.advisors;

import java.io.File;
import java.awt.Dimension;
import javax.swing.JFileChooser;
import com.formdev.flatlaf.*;

public class Host {
  public static File openFileBrowser(java.awt.Component parent) {
    JFileChooser fileChooser = null;
    try {
      FlatDarkLaf.setup();
      fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setDialogTitle("Select File using File Explorer");
      fileChooser.setApproveButtonText("Select");
      fileChooser.setApproveButtonToolTipText("Select the file");
      fileChooser.setCurrentDirectory(new File("C:\\"));
      fileChooser.setPreferredSize(new Dimension(700, 700));

      fileChooser.showOpenDialog(parent);
    return fileChooser.getSelectedFile();
    } catch (Exception e) {
      e.printStackTrace();
      new main.util.ErrorMessage(e.getMessage());
    }
    return null;

  }
}
