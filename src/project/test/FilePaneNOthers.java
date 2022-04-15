package project.test;

import javax.swing.*;
import javax.swing.plaf.SplitPaneUI;

import project.components.*;

import java.awt.*;

public class FilePaneNOthers {
  public static class Size {
    private Size(){}
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 700;
  }

  public static void someother(){
    JPanel jp2 = new JPanel();
    jp2.setPreferredSize(new Dimension(Size.WIDTH - 300, Size.HEIGHT));
    jp2.setBackground(Color.BLUE);
    JFileChooser jfc = new JFileChooser();
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    jfc.setBorder(BorderFactory.createEtchedBorder(Color.DARK_GRAY, Color.YELLOW));
    jfc.setApproveButtonText("Select");
    jfc.setPreferredSize(new Dimension(Size.WIDTH - 1000, Size.HEIGHT));
    jfc.setBackground(Color.GRAY);
    jfc.setMultiSelectionEnabled(false);
    jfc.setApproveButtonToolTipText("Select the file");
    JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jp2, jfc);
    jsp.setDividerLocation(Size.WIDTH - 100);

    JFrame jf = new JFrame();
    jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    jf.setPreferredSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    jf.setSize(new Dimension(Size.WIDTH, Size.HEIGHT));
    jf.setLocationRelativeTo(null);
    jf.getContentPane().add(jsp);
    jf.pack();
    jf.setVisible(true);

  }
  public static void main(String[] args) {
    ProcessesSchedule.main();
    someother();
  }
}
