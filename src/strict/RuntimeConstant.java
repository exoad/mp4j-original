package strict;

import project.audio.content.AudioInfoEditor;
import project.constants.ResourceDistributor;

public final class RuntimeConstant {
  private RuntimeConstant() {}
  public static final ResourceDistributor runtimeRD = new ResourceDistributor();
  public static final String FILE_SLASH = System.getProperty("file.separator");

  public static final AudioInfoEditor DEFAULT_AIE = new AudioInfoEditor();
}
