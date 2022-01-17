package app.interfaces;

import java.io.File;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import app.core.Host;
import app.core.LifePreserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFileWindow extends JPanel implements Runnable, ActionListener {
  private String filePath;
  private static File file;
  private final JFrame frame;
  private final JButton button;
  private final JButton openExplorer;
  private final JTextField textField;
  private final String lastDir;

  public SelectFileWindow(String lastFilePath) {
    lastDir = lastFilePath;

    URL frameIcon = getClass().getResource("/icons/others/file_select_folder_icon.png");
    assert frameIcon != null;
    ImageIcon frameImageIcon = new ImageIcon(frameIcon);
    button = new JButton("Select File");
    button.addActionListener(this);

    textField = new JTextField(20);
    textField.setEditable(true);
    textField.setToolTipText("Enter the file path here");

    openExplorer = new JButton("Open Explorer");
    openExplorer.addActionListener(this);
    openExplorer.setIcon(frameImageIcon);

    add(button);
    add(textField);
    add(openExplorer);

    frame = new JFrame("Select File");
    frame.setIconImage(frameImageIcon.getImage());
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.getContentPane().setBackground(new java.awt.Color(0, 0, 0, 0));
    frame.setResizable(false);
    frame.add(this);
  }


  /**
   * @return File
   */
  public static File getFile() {
    return file;
  }

  @Override
  public void run() {
    frame.setVisible(true);
    frame.pack();
  }


  /** 
   * @param field
   */
  public void check(String field) {
    filePath = field;
    if (filePath == null || filePath.equals("") || !new File(filePath).exists()) {
      new ErrorMessage("Invalid file path");
    }
    file = new File(filePath);
    if (file.getName().endsWith(".wav") || file.getName().endsWith(".mp3")) {
      frame.setVisible(false);
      frame.dispose();
      WindowPanel.run();
    } else {
      new ErrorMessage("Invalid file type");
    }
  }


  /** 
   * @param e
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(button)) {
      check(textField.getText());
    } else if (e.getSource().equals(openExplorer)) {
      File f;
      new app.core.Host(lastDir);
      f = Host.openFileBrowser(this);

      if (f != null) {
        textField.setText(f.getAbsolutePath());
        check(f.getAbsolutePath());
        try {

          String lst = f.getParent();
          new LifePreserver(lst).saveToPrevDir();

        } catch (Exception ioException) {
          ioException.printStackTrace();
          new ErrorMessage(java.util.Arrays.toString(ioException.getStackTrace()));
        }
      }
    }

  }
}