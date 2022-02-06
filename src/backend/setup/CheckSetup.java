package backend.setup;

import java.io.File;
import java.io.IOException;

import app.core.Host;
import app.global.Items;

public class CheckSetup {
  private CheckSetup() {
  }
  public static void checkNativeDirs() {
    File apiCache = new File(Items.items[0]);
    if (!apiCache.isDirectory()) {
      apiCache.mkdir();
    }
    File mpSaves = new File(Items.items[1]);
    if (!mpSaves.isDirectory()) {
      mpSaves.mkdir();
    }

    File customs = new File(Items.items[5]);
    if (!customs.isDirectory()) {
      customs.mkdir();
    }

    File mpLogs = new File(Items.items[2]);
    if (!mpLogs.isDirectory()) {
      mpLogs.mkdir();
    }
  }

  public static void checkJBR() {
    try {
      if(Host.returnJava().contains("JBR")) {
        new app.interfaces.setup.JBRNotFound().show();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    
  }
} 
