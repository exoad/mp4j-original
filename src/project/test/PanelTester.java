package project.test;

import javax.swing.JFrame;

import project.components.sub_components.infoview.SubVolumeView;

import java.awt.*;

public class PanelTester extends JFrame {
  public PanelTester() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(new Dimension(500, 500));
    add(new SubVolumeView());
    setVisible(true);
  }
}
