package backend.audio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import it.sauronsoftware.jave.*;

import app.CLI;
import app.global.cli.CliType;

import app.global.Pair;

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
    String holder = randomFileName();
    File f2;
    PropertiesMaintainer pm = new PropertiesMaintainer("convert.properties");
    if(pm.p.contains(f.getName())) {
      f2 = new File(pm.p.getProperty(f.getName()));
    } else {
      f2 = new File(GlobalVars.CODEC_FINAL + "/" + holder + ".mp3");
      // files copy
      try {
        Files.copy(f.toPath(), f2.toPath());
      } catch (IOException e) {
        e.printStackTrace();
      }
      pm.p.setProperty(f.getName(), f2.getName());
      pm.save();
    }

    File wav = new File(GlobalVars.ITEM_DIR + "/" + holder + ".wav");
    AudioAttributes audio = new AudioAttributes();
    // mp3 to wav
    audio.setCodec("libmp3lame");
    audio.setBitRate(320000);
    audio.setChannels(2);
    audio.setSamplingRate(44100);
    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setFormat("wav");
    attrs.setAudioAttributes(audio);
    Encoder encoder = new Encoder();
    try {
      encoder.encode(f2, wav, attrs);
    } catch (it.sauronsoftware.jave.EncoderException e) {
      e.printStackTrace();
    }

    return wav;
  }

  public static File convert(File f, CodecDecoders s, Pair<?, ?> typeNames) {
    File wav = new File(
        GlobalVars.ITEM_DIR + "\\" + f.getName().replace((String) typeNames.first, "") + (String) typeNames.second);
    if (wav.exists()) {
      CLI.print("File already exists, skipping conversion", CliType.WARNING);
      return wav;
    }
    CLI.print("Converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath());
    AudioAttributes audio = new AudioAttributes();
    audio.setCodec(s.get());
    audio.setBitRate(320000);
    audio.setChannels(2);
    audio.setSamplingRate(44100);
    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setFormat(((String) typeNames.second).replace(".", ""));
    attrs.setAudioAttributes(audio);
    Encoder encoder = new Encoder();
    try {
      encoder.encode(f, wav, attrs);
    } catch (IllegalArgumentException | it.sauronsoftware.jave.EncoderException e) {
      app.CLI.print("Error converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath(),
          app.global.cli.CliType.ERROR);
      app.CLI.print(e.getMessage(), app.global.cli.CliType.ERROR);
    }
    return wav;
  }
}
