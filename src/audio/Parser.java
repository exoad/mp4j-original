package audio;

import java.io.File;

public class Parser {
  private File file;
  public Parser(File f) {
    file = f;
  }

  public File getFile() {
    return file;
  }

  public File convertMp3ToWav() {
    if(!file.getAbsolutePath().endsWith(".wav")) {
      return file;
    }
    return new File(file.getAbsolutePath().replace(".wav", ".mp3"));

  }
}
