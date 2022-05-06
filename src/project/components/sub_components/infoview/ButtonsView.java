package project.components.sub_components.infoview;

import project.constants.ProjectManager;
import project.usables.action.ForeignButtonListener;

import javax.swing.*;
import java.awt.*;

public class ButtonsView extends JPanel {
  private JButton openSourceCodeLoc;
  public ButtonsView(Dimension g) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setPreferredSize(new Dimension((int) g.getWidth(), (int) g.getHeight()));

    openSourceCodeLoc = new JButton("GitHub");
    openSourceCodeLoc.setToolTipText("Visit the GitHub page of this project");
    openSourceCodeLoc.addActionListener(new ForeignButtonListener.SourceCodeGitHub());

    if(ProjectManager.DEBUG_LAYOUT) {
      setOpaque(true);
      setBackground(Color.GREEN);
    }

    add(openSourceCodeLoc);
  }
}
