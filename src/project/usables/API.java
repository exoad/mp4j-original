package project.usables;

public class API {
  public static void print(Object... args) {
    for(Object o : args) {
      System.out.print("API_CONSOLE@: " + o);
    }
  }
}
