package project.usables.action;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import project.constants.ProjectManager;

import java.awt.event.ActionEvent;

import java.awt.Desktop;

public class ForeignButtonListener {
  private ForeignButtonListener() {}

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
}
