package app;

import static java.lang.System.*;

public abstract class CLI {
  private static final String consoleLikeDir = "\u001B[42m\u001B[30mMPlayer4J CLI $\u001B[0m ";
  
  private CLI() {}

  public static void print(Object j) {
    out.println(consoleLikeDir + j);
  }
}
