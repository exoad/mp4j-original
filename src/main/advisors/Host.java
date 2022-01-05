package main.advisors;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.Dimension;
import javax.swing.JFileChooser;


public class Host {
  private static String lastDir = "";

  public Host(String lastDir) {
    Host.lastDir = lastDir;
  }

  
  /** 
   * @param i
   * @throws IOException
   */
  public static void extendedFileSaver(File i) throws IOException {
    LifePreserver lp = new LifePreserver(i.getAbsolutePath());
    lp.saveToPrevDir();
  }

  
  /** 
   * @param parent
   * @return File
   */
  public static File openFileBrowser(java.awt.Component parent) {
    JFileChooser fileChooser = null;
    try {
      
      fileChooser = new JFileChooser();
      fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
      fileChooser.setDialogTitle("Select File using File Explorer");
      fileChooser.setApproveButtonText("Select");
      fileChooser.setApproveButtonToolTipText("Select the file");
      if (lastDir != null || lastDir.length() == 0) {
        fileChooser.setCurrentDirectory(new File(lastDir));
      } else {
        fileChooser.setCurrentDirectory(new File("."));
      }
      fileChooser.setPreferredSize(new Dimension(700, 700));

      fileChooser.showOpenDialog(parent);
      return fileChooser.getSelectedFile();
    } catch (Exception e) {
      e.printStackTrace();
      new main.gui.ErrorMessage(e.getStackTrace().toString());
    }
    return null;

  }

  
  /** 
   * @param runtime
   * @param ch
   * @return String
   * @throws IOException
   */
  public static String runProcess(Runtime runtime, CharSequence ch) throws IOException {
    Runtime rt = runtime;
    String[] commands = { ch.toString(), "-get t" };
    Process proc = rt.exec(commands);
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));

    String s = null;
    while ((s = stdInput.readLine()) != null) {
      return s;
    }
    return null;
  }

}
