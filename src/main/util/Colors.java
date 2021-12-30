package main.util;

public enum Colors {

  BLUE("#4286f4"),
  GREEN("#4caf50"),
  RED("#f44336"),
  YELLOW("#ffeb3b");

  private final String s;

  private Colors(String s) {
    this.s = s;
  }

  public String getColor() {
    return s;
  }
}
