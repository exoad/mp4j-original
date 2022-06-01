package project.usables.action;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import project.constants.ProjectManager;
import project.components.windows.SimpleDebugWindow;

import java.awt.event.ActionEvent;

import java.awt.Desktop;

/**
 * This class contains listeners which handles repetitive listeners
 * for the buttons in any class.
 * 
 * For each button action, a subclass is created.
 * 
 * @author Jack Meng
 */
public class ForeignButtonListener {
  private ForeignButtonListener() {
  }

  /**
   * This sub-class handles opening the user's default browser
   * to redirect to this project's GitHub page.
   * 
   * @author Jack Meng
   */
  public static class SourceCodeGitHub implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
      try {
        Desktop.getDesktop().browse(new URI(ProjectManager.GITHUB_PROJECT_URL));
      } catch (IOException | URISyntaxException e1) {
        e1.printStackTrace();
      }
    }
  }

  /**
   * Opens a debug window for the user to see the debug log.
   * 
   * @author Jack Meng
   * @since 2.1
   */
  public static class BasicDebugWindowLauncher implements ActionListener {
    boolean opened = false;

    @Override
    public void actionPerformed(ActionEvent e) {
      ((javax.swing.JButton) e.getSource()).setEnabled(false);
      if (!opened) {
        opened = true;
        new SimpleDebugWindow().run();
      }
    }
  }
}
