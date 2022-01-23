package audio;

import java.io.File;

public class PreFetcher {
  private final File file;
  public PreFetcher(File file) {
    this.file = file;
  }

  public File getFile() {
    return file;
  }

  public File getConvertted() {
    return new Parser(file).convertMp3ToWav();
  }
}
