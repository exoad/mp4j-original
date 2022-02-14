package backend.audio;

import it.sauronsoftware.jave.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Music {
  private Music() {
  }

  private static String randomFileName() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 24; i++) {
      int n = (int) (Math.random() * 62);
      if (n < 10) {
        sb.append(n);
      } else if (n < 36) {
        sb.append((char) ('a' + n - 10));
      } else {
        sb.append((char) ('A' + n - 36));
      }
    }
    return sb.toString();
  }

  /**
   * Plans:
   * To copy the MP3 over to the temp folder, and then convert it to WAV.
   * Then keep a record of that conversion so we know.
   * @param f
   * @return
   * \    pm.p.setProperty(f2.getAbsolutePath(), f.getName());
    pm.save();
   */
  public static File convert(File f) {
    String fName = f.getName();
    // remove all unicode characters
    fName = fName.replaceAll("[^a-zA-Z0-9\\s]", "");
    fName = fName.replaceAll("\\s+", "");
    File wav = new File(GlobalVars.ITEM_DIR + "\\" + fName.replace(".mp3", "") + ".wav");
    File f2 = new File(GlobalVars.ITEM_DIR + "\\" + randomFileName() + ".mp3");
    if (wav.exists()) {
      System.out.println("WAV file already exists, skipping conversion");
      return wav;
    } else {
      try {
        Files.copy(f.toPath(), f2.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        // DO NOTHING
      }
    }
    System.out.println("Converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath());
    AudioAttributes audio = new AudioAttributes();
    audio.setCodec("pcm_s16le");
    audio.setBitRate(320000);
    audio.setChannels(2);
    audio.setSamplingRate(44100);
    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setFormat("wav");
    attrs.setAudioAttributes(audio);
    Encoder encoder = new Encoder();
    try {
      encoder.encode(f2, wav, attrs);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InputFormatException e) {
      e.printStackTrace();
    } catch (EncoderException e) {
      e.printStackTrace();
    }
    return wav;
  }
}
