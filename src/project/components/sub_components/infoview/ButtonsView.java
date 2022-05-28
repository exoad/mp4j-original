package project.components.sub_components.infoview;

import project.audio.Overseer;
import project.constants.ProjectManager;
import project.constants.ResourceDistributor;
import project.constants.Size;
import project.usables.DeImage;
import project.usables.action.ForeignButtonListener;
import strict.RuntimeConstant;

import javax.swing.*;
import java.awt.*;

/**
 * WORK IN PROGRESS, SEE THE FEATURES DOC!!!
 * 
 * @author Jack Meng
 */
public class ButtonsView extends JPanel {
  private JButton openSourceCodeLoc, debugWindow;

  public static JButton getUselessButton() {
    JButton useless = new JButton("     ");
    useless.setEnabled(false);
    useless.setToolTipText("Unused button");
    useless.setAlignmentX(Component.CENTER_ALIGNMENT);
    return useless;
  }

  /**
   * Constructs a ButtonsView object.
   * 
   * @param g The Dimension to be used.
   */
  public ButtonsView(Overseer g) {
    System.out.println(g.topView.getWidth() + " " + g.topView.getHeight());
    setLayout(new GridLayout(10, 1));
    setPreferredSize(new Dimension(125, 200));
    setMinimumSize(new Dimension(125, 200));
    setOpaque(true);

    openSourceCodeLoc = new JButton("GitHub");
    openSourceCodeLoc.setIcon(DeImage.resizeImage(RuntimeConstant.runtimeRD.getGitHubDark(),
        Size.BUTTONS_VIEW_ICONS_SIZE, Size.BUTTONS_VIEW_ICONS_SIZE));
    openSourceCodeLoc.setToolTipText("<html><p>Visit the GitHub page of this project<br>Redirects to: "
        + ProjectManager.GITHUB_PROJECT_URL + "<p></html>");
    openSourceCodeLoc.setBorder(BorderFactory.createEmptyBorder());
    openSourceCodeLoc.addActionListener(new ForeignButtonListener.SourceCodeGitHub());
    openSourceCodeLoc.setAlignmentX(Component.CENTER_ALIGNMENT);

    debugWindow = new JButton("Debug");
    debugWindow.setToolTipText("Open the debug window");
    debugWindow.addActionListener(new ForeignButtonListener.BasicDebugWindowLauncher());
    debugWindow.setAlignmentX(Component.CENTER_ALIGNMENT);

    if (ProjectManager.DEBUG_LAYOUT) {
      setOpaque(true);
      setBackground(Color.GREEN);
    }

    add(openSourceCodeLoc);
    add(debugWindow);
    add(getUselessButton());
  }
}
