package backend.cxx;

public enum NotionsMake {
  GCC("gcc"),
  CLANG_PLUS_PLUS("clang"),
  G_PLUS_PLUS("g++");

  private final String command;
  private NotionsMake(String cmd) {
    command = cmd;
  }

  public String getCommand() {
    return command;
  }
}
