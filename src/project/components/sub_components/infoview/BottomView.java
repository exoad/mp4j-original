package project.components.sub_components.infoview;

import project.audio.Overseer;
import project.constants.Size;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

public class BottomView extends TopView {
  private JButton playPauseButton;
  public BottomView(Overseer jf) {
    super();
    setSeer(jf);
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT - 500));
    setOpaque(true);
    setBackground(Color.CYAN);
    add(jf.getPlayPauseButton());
  }
}
