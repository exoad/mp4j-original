package backend.cxx.test;

import java.io.IOException;

import backend.cxx.Commands;
import backend.cxx.Maker;
import backend.cxx.NotionsMake;

public class Tester {
  public static void main(String[] args) throws IOException {
    Maker m = new Maker(Commands.GCC, "/src/backend/cxx/Tester.cxx");
    System.out.println(m.compileNRun(NotionsMake.GCC));

  }
}
