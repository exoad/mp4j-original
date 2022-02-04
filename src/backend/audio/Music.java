package backend.audio;

import java.io.File;

import it.sauronsoftware.jave.*;

import app.CLI;
import app.global.cli.CliType;

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
    File wav = new File(GlobalVars.ITEM_DIR + "\\" + randomFileName() + ".wav");
    if (wav.exists()) {
      CLI.print("WAV file already exists, skipping conversion", CliType.ERROR);
      return null;
    }
    CLI.print("Converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath());
    AudioAttributes audio = new AudioAttributes();
    audio.setCodec("pcm_s16le");
    audio.setBitRate(128000);
    audio.setChannels(1);
    audio.setSamplingRate(44100);
    EncodingAttributes attrs = new EncodingAttributes();
    attrs.setFormat("wav");
    attrs.setAudioAttributes(audio);
    Encoder encoder = new Encoder();
    try {
      encoder.encode(f, wav, attrs);
    } catch (IllegalArgumentException e) {
      CLI.print("Error converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath(), CliType.ERROR);
      CLI.print(e.getMessage(), CliType.ERROR);
    } catch (InputFormatException e) {
      CLI.print("Error converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath(), CliType.ERROR);
      CLI.print(e.getMessage(), CliType.ERROR);
    } catch (EncoderException e) {
      CLI.print("Error converting " + f.getAbsolutePath() + " to " + wav.getAbsolutePath(), CliType.ERROR);
      CLI.print(e.getMessage(), CliType.ERROR);
    }
    return wav;
  }
}
