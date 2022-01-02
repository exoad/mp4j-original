package main.advisors.subadvisors;

import java.io.File;
import java.util.HashSet;

public class AllowedProperties {
  public static final HashSet<String> allowedDarkLaf = new HashSet<>();
  public static final HashSet<String> allowedLiteLaf = new HashSet<>();
  public static final HashSet<String> allowedDefMode = new HashSet<>();
  public static final HashSet<String> allowedDefCache = new HashSet<>();

  public AllowedProperties() {
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
    allowedLiteLaf.add("onelight");

    allowedDefMode.add("dark");
    allowedDefMode.add("light");

    allowedDefCache.add("false");
    allowedDefCache.add("true");
  }

  public static boolean validate(String s) {
    return ((allowedDarkLaf.contains(s) || allowedLiteLaf.contains(s)) || new File(s).isFile() || allowedDefMode.contains(s) || allowedDefCache.contains(s));
  }
}
