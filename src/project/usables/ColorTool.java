package project.usables;

import java.awt.Color;

public class ColorTool {
  private ColorTool() {
  }

  public static Color hexToRGBA(String hex) {
    if (!hex.startsWith("#")) {
      hex = "#" + hex;
    }
    return new Color(
        Integer.valueOf(hex.substring(1, 3), 16),
        Integer.valueOf(hex.substring(3, 5), 16),
        Integer.valueOf(hex.substring(5, 7), 16));
  }
}
