package app.interfaces.setup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JBRNotFound implements ActionListener {
  private final JFrame frame;
  private JButton openDownload, downloadInstaller;
  public JBRNotFound() {
    frame = new JFrame("A JBR installation is required");
    frame.setIconImage(new ImageIcon(java.util.Objects.requireNonNull(getClass().getResource("/icons/logos/jetbrains.jpg"))).getImage());
    frame.setSize(500, 300);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setResizable(false);

    javax.swing.JPanel panel = new javax.swing.JPanel();
    panel.setPreferredSize(new java.awt.Dimension(500, 300));
    panel.setLayout(null);

    javax.swing.JLabel label = new javax.swing.JLabel("<html><b>JetBrains Runtime was not found,<br> please install a valid JBR Runtime</b></html>");
    label.setHorizontalAlignment((int) Component.CENTER_ALIGNMENT);
    label.setBounds(0, 0, 500, 100);

    openDownload = new JButton("Open Download Page");
    openDownload.setToolTipText("Open the official JetBrains Runtime GitHub releases Page");
    openDownload.setBounds(100, 100, 300, 50);
    openDownload.addActionListener(this);

    downloadInstaller = new JButton("Download the Installer");
    downloadInstaller.setToolTipText("Download the JBR installer for this specific MP4J version");
    downloadInstaller.setBounds(100, 175, 300, 50);
    downloadInstaller.addActionListener(this);

    panel.add(label);
    panel.add(openDownload);
    panel.add(downloadInstaller);
    frame.add(panel);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    if(e.getSource().equals(openDownload)) {
      app.core.Host.openInBrowser("https://github.com/JetBrains/JetBrainsRuntime/releases");
      System.exit(-1);
    } else if(e.getSource().equals(downloadInstaller)) {
      app.core.Host.openInBrowser(app.global.Sources.JBRINSTALLER);
      System.exit(-1);
    }
  }

  public void show() {
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    JBRNotFound jbr = new JBRNotFound();
    jbr.frame.setVisible(true);
    jbr.frame.pack();
  }
}
