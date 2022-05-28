package project.components.sub_components.infoview;

import project.audio.Overseer;
import project.constants.ProjectManager;

import javax.swing.*;
import java.awt.*;

/**
 * Used in the TopView {@link project.components.TopView} class for sliders.
 * 
 * @author Jack Meng
 */
public class SubVolumeView extends JPanel {
  /**
   * Constructs a SubVolumeView object.
   * @param j The Overseer object to be passed around by the classes and objects.
   */
  public SubVolumeView(Overseer j) {
    super();

    JLabel vl = new JLabel("<html>V<br>O<br>L<br>U<br>M<br>E</html>");
    JLabel pl = new JLabel("<html>P<br>A<br>N</html>");
    JLabel al = new JLabel("<html>A<br>M<br>P<br>L<br>E</html>");
    setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
    setOpaque(true);
    setPreferredSize(new Dimension(130, 200));
    setBorder(BorderFactory.createLineBorder(Color.WHITE, 1, true));
    if (ProjectManager.DEBUG_LAYOUT)
      setBackground(Color.BLUE);
    JSlider js = j.getVolumeSlider();
    js.setPreferredSize(new Dimension(20, 180));
    add(vl);
    add(js);
    JSlider pjs = j.getPanSlider();
    pjs.setPreferredSize(new Dimension(20, 180));
    add(pl);
    add(pjs);
    JSlider ajs = j.getAmpSlider();
    ajs.setPreferredSize(new Dimension(20, 180));
    add(al);
    add(ajs);
  }

}
