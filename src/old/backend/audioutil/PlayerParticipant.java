package old.backend.audioutil;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;

import java.io.File;
import java.net.URL;

public class PlayerParticipant {
  public static class StopWatch {
    private long curr = 0;

    public StopWatch() {}

    public void start() {
      curr = System.currentTimeMillis();
    }

    public synchronized long getElapsed() {
      return System.currentTimeMillis() - curr;
    }

    public synchronized void stop() {
      curr = System.currentTimeMillis();
    }

    public String toString() {
      return getElapsed() + " ms";
    }
  }
  private File orig;
  private StreamPlayer participant;
  private final float MAX_VOL = 0.5f, MIN_VOL = 0.01f;
  private StopWatch sp = new StopWatch();
  private String curr = "";
  private transient Thread slave = new Thread();

  public PlayerParticipant(URL j) {
    orig = new File(j.getFile());
    participant = new StreamPlayer();
  }

  public PlayerParticipant(File f) {
    orig = f;
  }

  private synchronized void endWatcher() {
    slave = new Thread(() -> {
      while(true) {
        if(!participant.isPlaying()) {
          sp.stop();
        } else {
          curr = sp.toString();
        }
        try {
          Thread.sleep(600);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });
    slave.start();
  }

  public void open() {
    endWatcher();
    try {
      participant.open(orig);
    } catch (StreamPlayerException e) {
      e.printStackTrace();
    }
  }

  public synchronized void play() {
    try {
      participant.play();
    } catch (StreamPlayerException e) {
      e.printStackTrace();
    }
  }

  public synchronized float setVol(float percent) {
    float vol = (percent / 100.0f) * (MAX_VOL - MIN_VOL) + MIN_VOL;
    participant.setGain(vol);
    return vol;
  }

  public synchronized void stop() {
    participant.stop();
  }

  public synchronized String getTotal() {
    int s = participant.getDurationInSeconds();
    int h = s / 3600;
    s = s % 3600;
    int m = s / 60;
    s = s % 60;
    return String.format("%02d:%02d:%02d", h, m, s);
  }
}
