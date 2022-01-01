package main.util;

import java.io.File;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatAtomOneDarkContrastIJTheme;
import main.advisors.Host;
import main.advisors.LifePreserver;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFileWindow extends JPanel implements Runnable, ActionListener {
  private String filePath;
  private static File file;
  private JFrame frame;
  private JButton button, openExplorer;
  private JTextField textField;
  private String lastDir = "";

  public SelectFileWindow(String lastFilePath) {
    lastDir = lastFilePath;
    FlatAtomOneDarkContrastIJTheme.setup();
    URL frameIcon = getClass().getResource("/file_select_folder_icon.png");
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
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    frame.getContentPane().setBackground(new java.awt.Color(0, 0, 0, 0));
    frame.setResizable(false);
    frame.add(this);
  }

  public String getFilePath() {
    return filePath;
  }

  public static File getFile() {
    return file;
  }

  @Override
  public void run() {
    frame.setVisible(true);
    frame.pack();
  }

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

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == button) {
      check(textField.getText());
    } else if (e.getSource() == openExplorer) {
      File f = null;
      new main.advisors.Host(lastDir);
      f = Host.openFileBrowser(this);

      if (f != null) {
        textField.setText(f.getAbsolutePath());
        check(f.getAbsolutePath());
        try {

          String lst = f.getParent();
          new LifePreserver(lst).saveToPrevDir();

        } catch (Exception ioException) {
          ioException.printStackTrace();
          new ErrorMessage(ioException.getStackTrace().toString());
        }
      }
    }

  }
}
