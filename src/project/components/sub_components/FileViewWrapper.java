package project.components.sub_components;

import javax.swing.JPanel;

import project.test_components.FilePaneNOthers.Size;
import project.audio.Overseer;
import project.constants.PreConfig;

import javax.swing.JButton;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
