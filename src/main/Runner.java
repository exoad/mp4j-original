package main;

import java.io.File;
public class Runner implements Runnable {


  @Override
  public void run() {
    File apiCache = new File(Items.items[0]);
    if (!apiCache.isDirectory()) {
      apiCache.mkdir();
    }
    File mpSaves = new File(Items.items[1]);
    if (!mpSaves.isDirectory()) {
      mpSaves.mkdir();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    new Runner().run();
    new main.util.Splash(Items.SPLASH_SECONDS).run();
    new main.util.WelcomeWindow().run();
  }

}
