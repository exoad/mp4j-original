package project.components.sub_components;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSplitPane;

import java.awt.Color;
import java.awt.Dimension;

import project.constants.Size;

public class InfoView extends JSplitPane { 
  public InfoView(JComponent top, JComponent bottom) {
    super(JSplitPane.VERTICAL_SPLIT, top, bottom);
    setOpaque(true);
    setDividerLocation(Size.HEIGHT -100);
    setMaximumSize(new Dimension(Size.WIDTH, Size.HEIGHT - 300));
  }
  
}
