package project.components.sub_components.infoview;

import project.audio.Overseer;
import project.constants.Size;
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
import javax.swing.JPanel;
import javax.swing.JSlider;

import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;
import com.goxr3plus.streamplayer.stream.StreamPlayerListener;

public class BottomView extends JPanel implements StreamPlayerListener {
  private JButton playPauseButton;
  private JSlider progressSlider;
  private Overseer seer;
  public BottomView(Overseer jf) {
    super();
    this.seer = jf;
    setLayout(new FlowLayout(FlowLayout.LEFT, 20, 15));
    setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT - 500));
    setMaximumSize(new Dimension((int) getPreferredSize().getWidth(), Size.HEIGHT - 530));
    seer.addStreamPlayerListener(this);
    setBorder(BorderFactory.createLineBorder(ColorContent.BORDER, 1, true));
    setOpaque(true);
    add(jf.getPlayPauseButton());
    progressSlider = jf.getProgressSlider();
    progressSlider.setPreferredSize(new Dimension(300, 20));
    add(progressSlider);
    jf.topView.getMainP().add(new SubVolumeView(jf));
  }
  @Override
  public void opened(Object arg0, Map<String, Object> arg1) {
  }
  @Override
  public void progress(final int arg0, long arg1, byte[] arg2, Map<String, Object> arg3) {
    long totalTimeMCS = seer.getDurationInSeconds() * 1000000L;
    progressSlider.setValue((int) (arg1 / totalTimeMCS));
    System.out.println(arg1 / totalTimeMCS);
    progressSlider.setToolTipText("Progress: " + arg1 + " / " + totalTimeMCS);
  }
  @Override
  public void statusUpdated(StreamPlayerEvent arg0) {
  }
}
