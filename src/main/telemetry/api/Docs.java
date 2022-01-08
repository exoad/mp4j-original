package main.telemetry.api;

import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

import java.net.URL;

import main.global.Items;
import main.telemetry.API;

public class Docs implements API {

  private final String docs = "> Properties Documentation <\n\nProperty Name | Description\ngui.defaultTheme | Changes the LAF of the MusicPlayer\nAvaliable Confs: regulardark, materia, onedark, arcdark, dracula, nord, gruvbox, vuesion, regularlight, solarized\n\nexplorer.defaultDir | Default Spawn Directory for the File Explorer:\n\".\", \"~\", \"/\"\n\nrunner.disableCache | Disable Caching\ntrue, false\n\ngui.defaultBoxSize | Change Button Spacing\n1 to 64";

  @Override
  public String run() throws IOException {
    if(!new File(Items.items[4]).exists())
      new File(Items.items[4]).createNewFile();
    BufferedWriter bw = new BufferedWriter(new FileWriter(returnLink().getFile()));
    bw.write(docs);
    bw.close();
    return docs;
  }

  @Override
  public URL returnLink() throws IOException {
    return new URL(Items.items[4]);
  }
  
}
