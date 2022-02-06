package test.CLI;

import app.CLI;
import app.global.cli.CliType;

public class Print {
  public static void main(String[] args) {
    CLI.print("Hello World!");
    CLI.print("Hello World!", CliType.ERROR);
    CLI.print("Hello World!", CliType.WARNING);
    CLI.print("Hello World!", CliType.INFO);
    CLI.print("Hello World!", CliType.SUCCESS);
    CLI.print("Hello World!", CliType.CHARM);
  }
}
