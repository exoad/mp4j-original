package app.rules;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class AllowedProperties {
  protected static final Set<String> allowedDarkLaf = new HashSet<>();
  protected static final Set<String> allowedLiteLaf = new HashSet<>();
  protected static final Set<String> allowedDefCache = new HashSet<>();
  protected static final Set<String> allowedDefDirs = new HashSet<>();
  protected static final Set<String> allowedButtonShape = new HashSet<>();

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
    allowedLiteLaf.add("gradientogreen");
    allowedLiteLaf.add("materiallighter");

    allowedDefCache.add("false");
    allowedDefCache.add("true");

    allowedDefDirs.add(".");
    allowedDefDirs.add("~");
    allowedDefDirs.add("/");

    allowedButtonShape.add("round");
    allowedButtonShape.add("square");
    allowedButtonShape.add("default");
  }

  public static boolean validate(String s) {
    if(s == null || s.isEmpty() || " ".equals(s))
      return false;
    return ((allowedButtonShape.contains(s) || allowedDarkLaf.contains(s) || allowedLiteLaf.contains(s)) || new File(s).isFile() || allowedDefCache.contains(s) || allowedDefDirs.contains(s));
  }

  public static boolean valInt(Object a) {
    if(!(a instanceof Integer))
      return false;

    int b = Integer.parseInt(String.valueOf(a));
    return (b >= 1 && b <= 64);
  }

  public static boolean valTransparency(Object a) {
    if(!(a instanceof Integer))
      return false;
    
    int b = Integer.parseInt(String.valueOf(a));
    return (b >= 0 && b <= 1);
  }

}
