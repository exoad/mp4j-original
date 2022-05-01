package project.components.sub_components;

import project.Main;
import project.audio.Overseer;
import project.constants.ColorContent;
import project.constants.PreConfig;
import project.constants.Size;

import javax.swing.*;
import java.awt.*;

public class FileViewWrapper extends JPanel {
  private FileViewPanel fvp;
  private JButton bugButton, windowResize;

  public FileViewWrapper(Main e, FileViewPanel fvp, Overseer obj) {
    this.fvp = fvp;
    JButton approve = obj.getApproveButton();
    approve.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    approve.setAlignmentX(Component.CENTER_ALIGNMENT);
    windowResize = new JButton("Reset Window Size");
    windowResize.addActionListener(e);
    windowResize.setToolTipText("Resizes the window Size to the default size");
    windowResize.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    windowResize.setAlignmentX(Component.CENTER_ALIGNMENT);
    bugButton = new JButton();
    bugButton.setVisible(false);
    bugButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    setPreferredSize(new Dimension((int) fvp.getPreferredSize().getWidth(), Size.HEIGHT));
    setSize(new Dimension((int) fvp.getPreferredSize().getWidth(), Size.HEIGHT));
    setBorder(BorderFactory.createLineBorder(ColorContent.BORDER, 1, true));
    setLayout(new BorderLayout());
    add(fvp);
    if (PreConfig.USE_CONSTANT_VIEW)
      add(bugButton);
    JPanel someRandom = new JPanel(new GridLayout(0, 1, 0, 3));
    someRandom.add(approve);
    someRandom.add(windowResize);
    add(someRandom, BorderLayout.PAGE_END);
  }

  public FileViewPanel getFVP() {
    return fvp;
  }
}
