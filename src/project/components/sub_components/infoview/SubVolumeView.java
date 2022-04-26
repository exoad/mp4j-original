package project.components.sub_components.infoview;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import project.audio.Overseer;
import project.constants.ProjectManager;

import java.awt.Color;
import java.awt.ComponentOrientation;
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

    JLabel vl = new JLabel("<html>V<br>O<br>L<br>U<br>M<br>E</html>");
    JLabel pl = new JLabel("<html>P<br>A<br>N</html>");
    JLabel sfl = new JLabel("<html>S<br>P<br>E<br>E<br>D</html>");
    setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
    setOpaque(true);
    setPreferredSize(new Dimension(120, 200));
    if(ProjectManager.DEBUG_LAYOUT)
      setBackground(Color.BLUE);
    JSlider js = j.getVolumeSlider();
    js.setPreferredSize(new Dimension(20, 180));
    add(vl);
    add(js);
    JSlider pjs = j.getPanSlider();
    pjs.setPreferredSize(new Dimension(20, 180));
    add(pl);
    add(pjs);
    add(getRandomSlider());
  }
  
}
