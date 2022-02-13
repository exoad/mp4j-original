package app.telemetry;

import app.CLI;
import app.global.cli.CliType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class FileIntegrity {
  private static URL fileList;

  public FileIntegrity() {
    fileList = getClass().getResource("/files.txt");
  }

  public String isGood() throws IOException {
    Scanner sc = new Scanner(fileList.openStream());
    while (sc.hasNextLine()) {
      String str = sc.nextLine();
      if (!new File("./resource/" + sc.nextLine()).exists()) {
        CLI.print("NOT FOUND: " + new File("./resource/" + str).getName(), CliType.ERROR);
        sc.close();
        return str;
      }
      CLI.print("FOUND: " + new File("./resource/" + str).getName(), CliType.SUCCESS);
    }
    sc.close();
    return "0";
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
