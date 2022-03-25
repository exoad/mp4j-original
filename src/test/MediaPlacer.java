package test;

import com.goxr3plus.streamplayer.stream.StreamPlayer;
import com.goxr3plus.streamplayer.stream.StreamPlayerException;

import java.io.File;

public class MediaPlacer {
  public static void main(String[] args) throws StreamPlayerException {
    StreamPlayer sp = new StreamPlayer();
    sp.open(new File("D:\\zip\\projects\\MusicPlayer\\src\\test\\Flexxus - Skyline.mp3"));

    sp.play();
    // set the volume using setGain to 10%
    sp.setGain(0.01f);
  }
}