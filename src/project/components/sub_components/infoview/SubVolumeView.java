package project.components.sub_components.infoview;

import javax.swing.JPanel;
import javax.swing.JSlider;

import project.audio.Overseer;
import project.constants.ProjectManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

public class SubVolumeView extends JPanel {
  private static JSlider getRandomSlider() {
    JSlider slider = new JSlider(JSlider.VERTICAL, 0, 100, 50);
    slider.setEnabled(false);
    slider.setPreferredSize(new Dimension(20, 180));
    return slider;
  }
  public SubVolumeView(Overseer j) {
    super();
    System.out.println(j.getVolumeSlider());
    setLayout(new FlowLayout(FlowLayout.LEFT, 20, 0));
    setOpaque(true);
    setPreferredSize(new Dimension(170, 200));
    if(ProjectManager.DEBUG_LAYOUT)
      setBackground(Color.BLUE);
    JSlider js = j.getVolumeSlider();
    js.setPreferredSize(new Dimension(20, 180));
    add(js);
    add(getRandomSlider());
    add(getRandomSlider());
  }
  
}
