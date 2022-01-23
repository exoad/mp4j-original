package app.global.cli;

public class CliException extends Exception {
  public CliException(String message) {
    super(message);
  }

  public CliException(String message, Throwable cause) {
    super(message, cause);
  }

  public CliException(Throwable cause) {
    super(cause);
  }
}
