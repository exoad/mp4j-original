package project.components.sub_components;

import project.Main;
import project.audio.Overseer;
import project.constants.ColorContent;
import project.constants.PreConfig;
import project.constants.ResourceDistributor;
import project.constants.Size;
import project.usables.DeImage;
import strict.RuntimeConstant;

import java.awt.event.*;

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
    windowResize.setBorder(BorderFactory.createEmptyBorder());
    windowResize.setIcon(DeImage.resizeImage(RuntimeConstant.runtimeRD.getEmptyBox(), Size.OK_BOX_SIZE, Size.OK_BOX_SIZE));
    windowResize.setToolTipText("Resizes the window Size to the default size");
    windowResize.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    windowResize.setAlignmentX(Component.CENTER_ALIGNMENT);
    windowResize.setBackground(null);
    bugButton = new JButton();
    bugButton.setVisible(false);
    bugButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    setPreferredSize(new Dimension((int) fvp.getPreferredSize().getWidth(), Size.HEIGHT));
    setMaximumSize(new Dimension(Size.WIDTH - 650, Size.HEIGHT));
    setMinimumSize(new Dimension(Size.WIDTH - 650, Size.HEIGHT));
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

  
  /** 
   * @return FileViewPanel
   */
  public FileViewPanel getFVP() {
    return fvp;
  }
}
