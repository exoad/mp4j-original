package main.advisors;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import main.Items;

public class LifePreserver {
  private final String data;

  public LifePreserver(String data) {
    this.data = data;
  }

  public void saveToPrevDir() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Items.items[1] + "/" + main.Sources.LIFEPRESERVER_PREVDIR)));
    bw.write(data);
    bw.flush();
    bw.close();
  }
}
