package project.audio.content;

import project.Utils;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class AudioType extends FileFilter {
  String MP3_EXTENSION = "mp3", WAV_EXTENSION = "wav";

  public AudioType() {
  }

  @Override
  public boolean accept(File f) {
    if (f.isDirectory())
      return true;
    String extension = Utils.getExtension(f);

    if (extension != null) {
      if (extension.equals(MP3_EXTENSION) || extension.equals(WAV_EXTENSION)) {
        return true;
      }
    }
    return false;

  }

  @Override
  public String getDescription() {
    return "MP3 & WAV Formats";
  }

}
