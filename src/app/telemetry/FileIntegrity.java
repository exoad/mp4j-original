package app.telemetry;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class FileIntegrity {
  private static URL fileList;

  public FileIntegrity() {
    fileList = getClass().getResource("/files.txt");
  }

  public boolean isGood() throws IOException {
    Scanner sc = new Scanner(fileList.openStream());
    while (sc.hasNextLine()) {
      if (!new File("./resource/" + sc.nextLine()).exists()) {
        sc.close();
        return false;
      }
    }
    sc.close();
    return true;
  }

  public static String getAllFiles() throws IOException {
    Scanner sc = new Scanner(fileList.openStream());
    StringBuilder sb = new StringBuilder();
    while (sc.hasNextLine()) {
      sb.append(sc.nextLine()).append("\n");
    }
    sc.close();
    return sb.toString();
  }
}
