package app.interfaces;

import java.io.File;
import java.net.URL;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import app.CLI;
import app.core.Host;
import app.core.LifePreserver;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import app.interfaces.event.WebsiteButtons;
import app.interfaces.dialog.ErrorMessage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectFileWindow extends JPanel implements Runnable, ActionListener {
  private String filePath;
  private static File file;
  private final JFrame frame;
  private final JButton button, openYouTube, openSoundCloud, openSpotify;
  private final JButton openExplorer;
  private final JTextField textField;
  private final String lastDir;
  private final JToolBar toolBar;

  public SelectFileWindow(String lastFilePath) {
    CLI.print(lastFilePath);
    lastDir = lastFilePath;

    URL youtube = getClass().getResource("/icons/logos/youtube.png");
    URL soundcloud = getClass().getResource("/icons/logos/soundcloud.png");
    URL spotify = getClass().getResource("/icons/logos/spotify.png");

    ImageIcon youtubeICO = new ImageIcon(youtube);
    ImageIcon soundcloudICO = new ImageIcon(soundcloud);
    ImageIcon spotifyICO = new ImageIcon(spotify);

    openYouTube = new JButton("YouTube");
    openYouTube.setIcon(youtubeICO);
    openYouTube.addActionListener(this);
    openYouTube.setToolTipText("Open YouTube");
    openYouTube.setBorderPainted(false);
    openYouTube.addActionListener(new WebsiteButtons("https://www.youtube.com/"));

    openSoundCloud = new JButton("SoundCloud");
    openSoundCloud.setIcon(soundcloudICO);
    openSoundCloud.addActionListener(this);
    openSoundCloud.setToolTipText("Open SoundCloud");
    openSoundCloud.setBorderPainted(false);
    openSoundCloud.addActionListener(new WebsiteButtons("https://soundcloud.com/"));

    openSpotify = new JButton("Spotify");
    openSpotify.setIcon(spotifyICO);
    openSpotify.addActionListener(this);
    openSpotify.setToolTipText("Open Spotify");
    openSpotify.setBorderPainted(false);
    openSpotify.addActionListener(new WebsiteButtons("https://open.spotify.com/"));

    toolBar = new JToolBar(SwingConstants.HORIZONTAL);
    toolBar.setFloatable(false);
    toolBar.add(openYouTube);
    toolBar.add(openSoundCloud);
    toolBar.add(openSpotify);

    URL frameIcon = getClass().getResource("/icons/others/file_select_folder_icon.png");
    assert frameIcon != null;
    ImageIcon frameImageIcon = new ImageIcon(frameIcon);
    button = new JButton("Select File");
    button.addActionListener(this);

    textField = new JTextField();
    textField.setEditable(true);
    textField.setToolTipText("Enter the file path here");

    openExplorer = new JButton("Open Explorer");
    openExplorer.addActionListener(this);
    openExplorer.setIcon(frameImageIcon);

    setLayout(new BorderLayout());
    add(toolBar, BorderLayout.NORTH);
    add(button, BorderLayout.CENTER);
    add(textField, BorderLayout.CENTER);
    add(openExplorer, BorderLayout.SOUTH);
    setPreferredSize(new Dimension(300, 80));
    frame = new JFrame("Select File");
    frame.setIconImage(frameImageIcon.getImage());
    frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    frame.setSize(300, 100);
    frame.setLocationRelativeTo(null);
    frame.getContentPane().setBackground(new java.awt.Color(0, 0, 0, 0));
    frame.setResizable(false);
    frame.setUndecorated(true);
    frame.addComponentListener(new app.interfaces.event.FrameOrganizer(frame));
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
    CLI.print(field);
    filePath = field;
    if (filePath == null || filePath.equals("") || !new File(filePath).exists()) {
      new ErrorMessage("Invalid file path");
    }
    file = new File(filePath);
    if (file.getName().endsWith(".wav") || file.getName().endsWith(".mp3")) {
      frame.setVisible(false);
      frame.dispose();
      new WindowPanel(lastDir);
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
    CLI.print(e.getActionCommand());
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