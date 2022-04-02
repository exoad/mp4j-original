package backend.audio;

import it.sauronsoftware.jave.*;
import javazoom.jl.converter.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import backend.audioutil.PlayerProgress;

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

  private static File secondaryConvert(File mp3, File dest) {
    //assert mp3.getName().endsWith(".mp3");
    Converter c = new Converter();
    PlayerProgress s = new PlayerProgress();
    s.start();
    try {
      System.out.print(mp3.getAbsolutePath() + "\n" + dest.getAbsolutePath());
      c.convert(mp3.getAbsolutePath(), dest.getAbsolutePath(), s);
    } catch (Exception e) {
      e.printStackTrace();
    }
    s.f.dispose();
    return dest;
  }

  public static File externConvert(File f) {
    String fName = f.getName();
    fName = fName.replaceAll("[^a-zA-Z0-9\\s]", "");
    fName = fName.replace(" ", "");
    fName += ".mp3";   
    File wav = new File(GlobalVars.ITEM_DIR + "\\" + fName.replace(".mp3", "") + ".wav");
    File f2 = new File(GlobalVars.ITEM_DIR + "\\" + fName);
    if (wav.exists()) {
      System.out.println("WAV file already exists, skipping conversion" + wav.getAbsolutePath());
      return wav;
    } else {
      try {
        if (!f2.exists())
          Files.copy(f.toPath(), f2.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println(f2.getAbsolutePath());
    //System.out.println("Converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath());
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
    } catch (it.sauronsoftware.jave.InputFormatException e) {
      e.printStackTrace();
      return secondaryConvert(f2, wav);
    } catch (IllegalArgumentException | it.sauronsoftware.jave.EncoderException e) {
      e.printStackTrace();
    }

    return wav;
  }

  /**
   * Plans:
   * To copy the MP3 over to the temp folder, and then convert it to WAV.
   * Then keep a record of that conversion so we know.
   * 
   * @return
   *         \ pm.p.setProperty(f2.getAbsolutePath(), f.getName());
   *         pm.save();
   */
  public static File convert(File f) {
    String fName = f.getName();
    // remove all unicode characters
    fName = fName.replaceAll("[^a-zA-Z0-9\\s]", "");  
    fName = fName.replace(" ", "");
    fName += ".mp3"; 
    File wav = new File(GlobalVars.ITEM_DIR + "\\" + fName.replace(".mp3", "") + ".wav");
    File f2 = new File(GlobalVars.ITEM_DIR + "\\" + fName);
    if (wav.exists()) {
      System.out.println("WAV file already exists, skipping conversion" + wav.getAbsolutePath());
      return wav;
    } else {
      try {
        if (!f2.exists())
          Files.copy(f.toPath(), f2.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    System.out.println("Converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath());
    return secondaryConvert(f2, wav);
    /*
    AudioAttributes audio = new AudioAttributes();
    // dynanmically pick the codec to use to decode mp3 to wav
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
    } catch (it.sauronsoftware.jave.InputFormatException e) {
      e.printStackTrace();
      return secondaryConvert(f2, wav);
    } catch (IllegalArgumentException | it.sauronsoftware.jave.EncoderException e) {
      e.printStackTrace();
    }

    return wav;
    */
  }
}
