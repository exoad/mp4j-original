package old.backend.audioutil;

import javazoom.jl.converter.Converter.ProgressListener;
import javazoom.jl.decoder.Header;
import javazoom.jl.decoder.Obuffer;

import javax.swing.*;
import java.awt.*;

public class PlayerProgress implements ProgressListener {
  public JFrame f;
  private JPanel e;
  private JLabel state;
  private JProgressBar bar;

  public PlayerProgress() {
    f = new JFrame();
    e = new JPanel();
    state = new JLabel("Parsing...");
    bar = new JProgressBar();
    bar.setStringPainted(true);
    bar.setString("");
    bar.setValue(0);
    bar.setMaximum(100);
    bar.setMinimum(0);
    bar.setPreferredSize(new Dimension(200, 20));
    e.setPreferredSize(new Dimension(300, 50));
    e.add(state);
    e.add(bar);
    f.add(e);
  }

  public void start() {
    f.setVisible(true);
    f.pack();
  }

  @Override
  public void converterUpdate(int updateID, int param1, int param2) {
    if (updateID == UPDATE_FRAME_COUNT) {
      bar.setValue(param1);
      bar.setString(param1 + "%");
    }
  }

  @Override
  public void parsedFrame(int frameNo, Header header) {
    
  }

  @Override
  public void readFrame(int frameNo, Header header) {
  }

  @Override
  public void decodedFrame(int frameNo, Header header, Obuffer o) {
  }

  @Override
  public boolean converterException(Throwable t) {
    return false;
  }
  
}
