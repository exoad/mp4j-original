package backend.audio;

import java.io.File;

import it.sauronsoftware.jave.*;

import app.CLI;
import app.global.cli.CliType;

import app.global.Pair;

public class Music {
  private Music() {}

  private static String randomFileName() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 16; i++) {
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

  public static File convert(File f) throws AudioConversionException {
    File wav = new File(GlobalVars.ITEM_DIR + "\\" + f.getName().replace(".mp3", "") + ".wav");
    if (wav.exists()) {
      CLI.print("WAV file already exists, skipping conversion", CliType.WARNING);
      return wav;
    }
    CLI.print("Converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath());
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
      encoder.encode(f, wav, attrs);
    } catch (IllegalArgumentException | it.sauronsoftware.jave.EncoderException e) {
      app.CLI.print("Error converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath(), app.global.cli.CliType.ERROR);
      app.CLI.print(e.getMessage(), app.global.cli.CliType.ERROR);
    }
    return wav;
  }

  public static File convert(File f, CodecDecoders s, Pair<?, ?> typeNames) {
    File wav = new File(GlobalVars.ITEM_DIR + "\\" + f.getName().replace((String) typeNames.first, "") + (String) typeNames.second);
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
    attrs.setFormat(((String)typeNames.second).replace(".", ""));
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
