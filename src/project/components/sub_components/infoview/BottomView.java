package project.components.sub_components.infoview;

import project.audio.Overseer;
import project.constants.Size;
import project.constants.ColorContent;

import project.components.sub_components.infoview.SubVolumeView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomView extends JPanel {
  private JButton playPauseButton;
  private Overseer seer;
  public BottomView(Overseer jf) {
    super();
    this.seer = jf;
    setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT - 500));
    setBorder(BorderFactory.createLineBorder(ColorContent.BORDER, 1, false));
    setOpaque(true);
    add(jf.getPlayPauseButton());
    add(jf.getProgressSlider());
    jf.topView.getMainP().add(new SubVolumeView(jf));
  }
}
