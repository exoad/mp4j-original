package project.components.sub_components;

import project.constants.Size;

import javax.swing.*;
import java.awt.*;

public class InfoView extends JSplitPane { 
  public InfoView(JComponent top, JComponent bottom) {
    super(JSplitPane.VERTICAL_SPLIT, top, bottom);
    setOpaque(true);
    setDividerLocation(Size.HEIGHT -100);
    setMaximumSize(new Dimension(Size.WIDTH, Size.HEIGHT - 300));
  }
  
}
