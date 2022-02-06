package app.java;

public class CorruptionException extends Throwable {
  public CorruptionException(String message) {
    super(message);
    message.getClass();
  }

  public CorruptionException(String message, Throwable cause) {
    super(message, cause);
    cause.getClass();
  }

  @Override
  public void printStackTrace() {
    System.out.println("CorruptionException: " + new CorruptionException("").getMessage());
  }
}
