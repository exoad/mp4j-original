package app.rules;

import app.CLI;
import app.global.cli.CliType;

import java.util.HashSet;
import java.util.Set;

public class AllowedProperties {
  protected static final Set<String> allowedDarkLaf = new HashSet<>();
  protected static final Set<String> allowedLiteLaf = new HashSet<>();
  protected static final Set<String> allowedDefCache = new HashSet<>();
  protected static final Set<String> allowedDefDirs = new HashSet<>();
  protected static final Set<String> allowedButtonShape = new HashSet<>();

  private AllowedProperties() {
    allowedDarkLaf.add("regulardark");
    allowedDarkLaf.add("material");
    allowedDarkLaf.add("onedark");
    allowedDarkLaf.add("arcdark");
    allowedDarkLaf.add("dracula");
    allowedDarkLaf.add("nord");
    allowedDarkLaf.add("gruvbox");
    allowedDarkLaf.add("vuesion");

    allowedLiteLaf.add("regularlight");
    allowedLiteLaf.add("solarized");
    allowedLiteLaf.add("gradientogreen");
    allowedLiteLaf.add("materiallighter");

    allowedDefCache.add("false");
    allowedDefCache.add("true");

    allowedButtonShape.add("round");
    allowedButtonShape.add("square");
    allowedButtonShape.add("default");
  }

  public static boolean valTheme(String s) {
    return allowedDarkLaf.contains(s) || allowedLiteLaf.contains(s) && (!s.isBlank() || s.isBlank());
  }


  public static boolean valCache(String s) {
    CLI.print(allowedDefCache.contains(s), CliType.INFO);
    return allowedDefCache.contains(s) && (!s.isBlank() || s.isBlank());
  }

  public static boolean valBox(String s) {
    CLI.print(allowedButtonShape.contains(s), CliType.INFO);
    return allowedButtonShape.contains(s) && (!s.isBlank() || s.isBlank());
  }

  public static boolean valBoxSize(String a) {
    int b = Integer.parseInt(String.valueOf(a));
    CLI.print(b >= 1 && b <= 32, CliType.INFO);
    return (b >= 1 && b <= 32);
  }

  public static boolean valTransparency(String a) {
    double d = Double.parseDouble(String.valueOf(a));
    CLI.print(d >= 0.0 && d <= 1.0, CliType.INFO);
    return (d > 0 && d <= 1);
  }
}
