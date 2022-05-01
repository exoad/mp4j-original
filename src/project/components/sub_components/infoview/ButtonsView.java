package project.components.sub_components.infoview;

import project.usables.action.ForeignButtonListener;

import javax.swing.*;
import java.awt.*;

public class ButtonsView extends JPanel {
  private JButton openSourceCodeLoc;
  public ButtonsView(Dimension g) {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setPreferredSize(g);

    openSourceCodeLoc = new JButton("Visit GitHub");
    openSourceCodeLoc.setToolTipText("Visit the GitHub page of this project");
    openSourceCodeLoc.addActionListener(new ForeignButtonListener.SourceCodeGitHub());

  }
}
