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
   * @return JSlider returns a random slider for testing
   */
  private static JSlider getRandomSlider() {
    JSlider slider = new JSlider(SwingConstants.VERTICAL, 0, 100, 50);
    slider.setEnabled(false);
    slider.setPreferredSize(new Dimension(20, 180));
    return slider;
  }

  /**
   * Constructs a SubVolumeView object.
   * @param j The Overseer object to be passed around by the classes and objects.
   */
  public SubVolumeView(Overseer j) {
    super();

    JLabel vl = new JLabel("<html>V<br>O<br>L<br>U<br>M<br>E</html>");
    JLabel pl = new JLabel("<html>P<br>A<br>N</html>");
    setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
    setOpaque(true);
    setPreferredSize(new Dimension(120, 200));
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
    add(getRandomSlider());
  }

}
