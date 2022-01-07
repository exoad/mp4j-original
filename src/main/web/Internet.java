package main.web;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

import main.Items;

public abstract class Internet {
  private Internet() {
  }

  public static boolean internet() {
    try (BufferedReader br = new BufferedReader(new FileReader(new File(Items.items[6])))) {
      String line = br.readLine();
      while (line != null) {
        if (line.contains("1")) {
          return true;
        }
        line = br.readLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }
}
