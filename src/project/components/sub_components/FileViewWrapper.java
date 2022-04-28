package project.components.sub_components;

import project.audio.Overseer;
import project.constants.ColorContent;
import project.constants.PreConfig;
import project.constants.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileViewWrapper extends JPanel implements ActionListener {
  private FileViewPanel fvp;
  private JButton approve, bugButton, exitButton;
  private Overseer obj;

  public FileViewWrapper(FileViewPanel fvp, Overseer obj) {
    this.fvp = fvp;
    this.obj = obj;
    approve = obj.getApproveButton();
    approve.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    approve.setAlignmentX(Component.CENTER_ALIGNMENT);
    exitButton = new JButton("Exit Program");
    exitButton.addActionListener(this);
    exitButton.setAlignmentY(Component.BOTTOM_ALIGNMENT);
    exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
    JPanel someRandom = new JPanel(new GridLayout(0,1,0,3));
    someRandom.add(approve);
    someRandom.add(exitButton);
    add(someRandom, BorderLayout.PAGE_END);
  }

  public FileViewPanel getFVP() {
    return fvp;
  }

  public JButton getApButton() {
    return approve;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource().equals(exitButton)) {
      System.exit(0);
    }
  }

}
