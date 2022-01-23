package test.parserTest;

import java.io.File;
import java.io.FileNotFoundException;

import audio.Parser;
import java.util.Scanner;

import app.CLI;

public class Audio {
  public static void main(String[] args) throws FileNotFoundException {
    Scanner sc = new Scanner(new File("src/test/parserTest/.loader"));
    String path = sc.nextLine();
    CLI.print(path);
    assert path.endsWith(".wav");

    File f = new File(path);
    CLI.print(new Parser(f).convertMp3ToWav().getName());
  }
}
