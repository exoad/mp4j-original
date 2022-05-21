package project.components.sub_components.infoview;

import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;
import project.audio.Overseer;
import project.constants.ColorContent;
import project.constants.Size;
import project.usables.TimeTool;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Represents the bottom view of the player. It is the view that contains things
 * like the time slider and play/pause buttons.
 * 
 * @author Jack Meng
 */
public class BottomView extends JPanel implements StreamPlayerListener {
  private JSlider progressSlider;
  private JLabel timeLabel;
  private ButtonsView bv;
  private transient Overseer seer;

  /**
   * Creates a BottomView Object (constructor)
   * 
   * @param jf The Overseer object to be passed around by the classes and objects.
   */
  public BottomView(Overseer jf) {
    super();
    this.seer = jf;
    setSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT / 6));
    setMinimumSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT / 5));
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT / 6));
    setMaximumSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT / 6));
    setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

    seer.addStreamPlayerListener(this);
    setBorder(BorderFactory.createLineBorder(ColorContent.BORDER, 1, true));
    setOpaque(true);
    add(jf.getPlayPauseButton());

    Dimension dimTemp = new Dimension(jf.topView.getPreferredSize().width, jf.topView.getPreferredSize().height);
    bv = new ButtonsView(dimTemp);
    timeLabel = new JLabel("<html><p><strong>0:00:00 / 0:00:00</strong></p></html>");
    add(timeLabel);

    progressSlider = jf.getProgressSlider();
    progressSlider.setPreferredSize(new Dimension(300, 20));
    add(progressSlider);
    jf.topView.getMainP().add(new SubVolumeView(jf));
    jf.topView.getMainP().add(bv);
  }

  /**
   * @param arg0 IGNORED
   * @param arg1 IGNORED
   */
  @Override
  public void opened(Object arg0, Map<String, Object> arg1) {
  }

  /**
   * Handles the time slider
   * @param arg0
   * @param arg1
   * @param arg2
   * @param arg3
   */
  @Override
  public void progress(final int arg0, final long arg1, byte[] arg2, Map<String, Object> arg3) {
    progressSlider.setValue((int) (((arg1 / 1000000d) / seer.getDurationInSeconds()) * 100));
    progressSlider.setToolTipText(
        TimeTool.getTimeFromMS(arg1 / 1000L) + " / " + TimeTool.getTimeFromMS(seer.getDurationInSeconds() * 1000L));
    timeLabel.setText("<html><p><strong>" + TimeTool.getTimeFromMS(arg1 / 1000L) + " / "
        + TimeTool.getTimeFromMS(seer.getDurationInSeconds() * 1000L) + "</strong></p></html>");
  }

  /**
   * @param arg0
   */
  @Override
  public void statusUpdated(StreamPlayerEvent arg0) {
  }
}
