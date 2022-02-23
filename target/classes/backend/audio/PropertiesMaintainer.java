package backend.audio;

import app.global.Items;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesMaintainer {
  public Properties p = new Properties();
  private File f;

  public PropertiesMaintainer(String url) {
    f = new File(Items.items[7] + "/" + url);
    try {
      if (!f.exists())
        f.createNewFile();
      p.load(new FileReader(f));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void save() {
    try {
      p.store(new FileOutputStream(f), "DO NOT DELETE");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  

}
