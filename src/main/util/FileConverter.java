package main.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.UnsupportedAudioFileException;

import main.sipgate.Converter;

public interface FileConverter {
  public static File convert(InputStream mp3File, OutputStream wavFile)
      throws IOException, UnsupportedAudioFileException {
    Converter converter = Converter.convertFrom(mp3File);
    converter.withTargetFormat(new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false));
    converter.to(wavFile);
    return new File(wavFile.toString());
  }

}
