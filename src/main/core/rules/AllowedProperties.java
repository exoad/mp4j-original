package main.core.rules;

import java.io.File;
import java.util.HashSet;

public class AllowedProperties {
  public static final HashSet<String> allowedDarkLaf = new HashSet<>();
  public static final HashSet<String> allowedLiteLaf = new HashSet<>();
  public static final HashSet<String> allowedDefCache = new HashSet<>();
  public static final HashSet<String> allowedDefDirs = new HashSet<>();

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

    allowedDefCache.add("false");
    allowedDefCache.add("true");

    allowedDefDirs.add(".");
    allowedDefDirs.add("~");
    allowedDefDirs.add("/");
  }

  public static boolean validate(String s) {
    if(s == null || s.isEmpty() || s == " ")
      return false;
    return ((allowedDarkLaf.contains(s) || allowedLiteLaf.contains(s)) || new File(s).isFile() || allowedDefCache.contains(s) || allowedDefDirs.contains(s));
  }

  public static boolean valInt(Object a) {
    if(!(a instanceof Integer))
      return false;

    int b = Integer.parseInt(String.valueOf(a));
    return (b >= 1 && b <= 64);
  }
}
