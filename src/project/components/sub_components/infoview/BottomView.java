package project.components.sub_components.infoview;

import project.audio.Overseer;
import project.constants.Size;
import project.usables.TimeTool;
import project.constants.ColorContent;

import project.components.sub_components.infoview.SubVolumeView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;

public class BottomView extends JPanel implements StreamPlayerListener {
  private JSlider progressSlider;
  private JLabel timeLabel;
  private transient Overseer seer;
  public BottomView(Overseer jf) {
    super();
    this.seer = jf;
    setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT - 250));
    seer.addStreamPlayerListener(this);
    setBorder(BorderFactory.createLineBorder(ColorContent.BORDER, 1, true));
    setOpaque(true);
    add(jf.getPlayPauseButton());

    timeLabel = new JLabel("0:00:00 / 0:00:00");
    add(timeLabel);

    progressSlider = jf.getProgressSlider();
    progressSlider.setPreferredSize(new Dimension(300, 20));
    add(progressSlider);
    jf.topView.getMainP().add(new SubVolumeView(jf));
  }
  @Override
  public void opened(Object arg0, Map<String, Object> arg1) {
  }
  
  @Override
  public void progress(final int arg0, final long arg1, byte[] arg2, Map<String, Object> arg3) {
    progressSlider.setValue((int) (((arg1 / 1000000d) / seer.getDurationInSeconds()) * 100));
    progressSlider.setToolTipText(TimeTool.getTimeFromMS(arg1 / 1000L) + " / " + TimeTool.getTimeFromMS(seer.getDurationInSeconds() * 1000L));
    timeLabel.setText(TimeTool.getTimeFromMS(arg1 / 1000L) + " / " + TimeTool.getTimeFromMS(seer.getDurationInSeconds() * 1000L));
  }
  @Override
  public void statusUpdated(StreamPlayerEvent arg0) {
  }
}
