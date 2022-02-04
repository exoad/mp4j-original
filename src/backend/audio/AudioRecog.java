package backend.audio;

/**
 * <h1>Audio Stream Player</h1>
 * <p>
 * This class is used to play audio streams
 * <p>
 * 
 * @author Jack Meng
 */

import app.interfaces.dialog.FrameConfirmDialog;
import app.functions.Worker;
import javazoom.jl.decoder.*;
import javazoom.jl.player.JavaSoundAudioDevice;
import app.CLI;
import javazoom.jl.player.Player;

import java.io.File;

public class AudioRecog {
  private File f;
  private boolean playMP3 = false;
  private Player player;
  private JavaSoundAudioDevice audioDevice = new JavaSoundAudioDevice();
  public AudioRecog(File f) {
    this.f = f;
    if(f.getName().endsWith(".mp3")) {
      playMP3 = true;
    } else {
      playMP3 = false;
    }
  }
}
