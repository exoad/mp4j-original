package app.java;

public class CorruptionException extends Throwable {
  private Class<? extends Throwable> cause;
  private Class<? extends String> message;
  public CorruptionException(String message) {
    super(message);
    this.message = message.getClass();
  }

  public CorruptionException(String message, Throwable cause) {
    super(message, cause);
    this.cause = cause.getClass();
  }

  @Override
  public void printStackTrace() {
    System.out.println("CorruptionException: " + new CorruptionException("").getMessage());
  }
}
