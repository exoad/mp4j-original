package app.core;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import app.global.Items;

public class LifePreserver {
  private final String data;

  public LifePreserver(String data) {
    this.data = data;
  }

  
  /** 
   * @throws IOException
   */
  public void saveToPrevDir() throws IOException {
    BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Items.items[1] + "/" + app.global.Sources.LIFEPRESERVER_PREVDIR)));
    bw.write(data);
    bw.flush();
    bw.close();
  }
}
