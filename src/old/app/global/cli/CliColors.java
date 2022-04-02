package app.global.cli;
/**
 * @author Jack Meng
 */
public enum CliColors {

  RED_BG("\u001B[41m"),
  GREEN_BG("\u001B[42m"),
  YELLOW_BG("\u001B[43m"),
  BLUE_BG("\u001B[44m"),
  MAGENTA_BG("\u001B[45m"),
  CYAN_BG("\u001B[46m"),
  WHITE_BG("\u001B[47m"),
  BLACK_BG("\u001B[40m"),
  RESET("\u001B[0m"),
  BOLD("\u001B[1m"),
  UNDERLINE("\u001B[4m"),
  BLINK("\u001B[5m"),
  REVERSE("\u001B[7m"),
  HIDDEN("\u001B[8m"),
  RED_TXT("\u001B[31m"),
  GREEN_TXT("\u001B[32m"),
  YELLOW_TXT("\u001B[33m"),
  BLUE_TXT("\u001B[34m"),
  MAGENTA_TXT("\u001B[35m"),
  CYAN_TXT("\u001B[36m"),
  WHITE_TXT("\u001B[37m"),
  BLACK_TXT("\u001B[30m");

  private String color;

  private CliColors(String e) {
    color = e;
  }

  public String getColor() {
    return color;
  }
}
