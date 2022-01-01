package main.advisors;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import jlibxx.util.Encode;

import main.Items;

public class LifePreserver {
  private String data;

  public LifePreserver(String data) {
    this.data = data;
  }

  public void saveToPrevDir() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Items.items[1] + "/" + "LifePreserver.txt")));
    bw.write(new Encode(data).toHex());
    bw.flush();
    bw.close();
  }
}
