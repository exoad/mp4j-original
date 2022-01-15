package app.functions;

import java.util.function.Consumer;

public abstract class Parser {
  public static <T> void foreach(T[] arr, Consumer<T> consumer) {
    for (T t : arr) {
      consumer.accept(t);
    }
  }
}
