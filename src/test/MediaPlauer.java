package test;

import de.ralleytn.simple.audio.*;

public class MediaPlauer {
  public static void main(String[] args) throws AudioException {
    Audio o = new StreamedAudio("src/test/Flexxus - Skyline.mp3");
    o.addAudioListener(event -> {

      if (event.getType() == AudioEvent.Type.REACHED_END) {

        event.getAudio().close();
      }
    });
    o.open();
    o.play();
  }
}