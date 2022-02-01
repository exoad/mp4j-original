package backend.cxx;

public enum Notions {
  GCC("gcc --version"),
  CLANG_PLUS_PLUS("clang --version"),
  G_PLUS_PLUS("g++ --version");

  private final String command;
  private Notions(String cmd) {
    command = cmd;
  }

  public String getCommand() {
    return command;
  }
}
