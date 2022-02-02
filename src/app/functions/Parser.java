package app.functions;

import java.util.function.Consumer;
/**
 * @author Jack Meng
 */
public abstract class Parser {
  public static <T> void foreach(T[] arr, Consumer<T> consumer) {
    for (T t : arr) {
      consumer.accept(t);
    }
  }
}
