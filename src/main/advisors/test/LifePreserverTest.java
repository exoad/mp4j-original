package main.advisors.test;

import main.advisors.LifePreserver;

public class LifePreserverTest {
  public static void main(String[] args) throws java.io.IOException {
    LifePreserver lp = new LifePreserver("hi");
    lp.saveToPrevDir();
  }
}
