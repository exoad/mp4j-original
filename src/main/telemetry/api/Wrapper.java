package main.telemetry.api;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

import main.telemetry.API;

public class Wrapper implements API {


  @Override
  public String run() throws IOException {  
    Scanner sc = new Scanner(returnLink().openStream());

    StringBuffer sb = new StringBuffer();
    while (sc.hasNextLine()) {
      sb.append(sc.nextLine()).append("\n");
    }
    sc.close();
    return sb.toString();
  }

  @Override
  public URL returnLink() throws IOException {
    return new URL("https://exoad.github.io/MusicPlayer/api/version");
  }
  
}
