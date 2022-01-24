package app.core;

import java.util.Properties;
import java.util.Set;

import app.global.Items;
import app.global.Sources;
import app.rules.AllowedProperties;
import app.rules.DefProperties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Map;

public class PropertiesReader {
  private static Properties p;
  private static Map<String, String> setProp = new HashMap<>();

  public PropertiesReader() throws IOException {
    p = new Properties();
    new AllowedProperties();

    if (!hasAllProperties() || !new File(Items.items[1] + "/" + app.global.Sources.PROPERTIES_FILE).exists()) {
      try (OutputStream os = new FileOutputStream(new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE))) {
        p.setProperty("explorer.defaultDir", DefProperties.DEFAULT_DIR);
        p.setProperty("gui.defaultTheme", DefProperties.DEFAULT_GUI_LAF);
        p.setProperty("runner.disableCache", DefProperties.DISABLE_CACHE);
        p.setProperty("gui.defaultBoxSize", String.valueOf(DefProperties.DEFAULT_BOX_SIZE));
        p.setProperty("gui.buttonShape", DefProperties.DEFAULT_BUTTON_SHAPE);
        p.store(os, Items.PROPERTIES_HEADER_COMMENT);
      }
    }

    setProp = keyyedProp();

  }

  public static Set<String> generalProp() throws IOException {
    HashSet<String> properties = new HashSet<>();
    p = new Properties();
    if (!new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE).exists()) {
      new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE).createNewFile();
      reset();
    }
    try (InputStream isr = new FileInputStream(new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE))) {
      p.load(isr);
      if (AllowedProperties.validate(p.getProperty("explorer.defaultDir")))
        properties.add(p.getProperty("explorer.defaultDir"));

      if (AllowedProperties.validate(p.getProperty("gui.defaultTheme")))
        properties.add(p.getProperty("gui.defaultTheme"));

      if (AllowedProperties.validate(p.getProperty("runner.disableCache")))
        properties.add(p.getProperty("runner.disableCache"));

      if (AllowedProperties.validate(p.getProperty("gui.defaultBoxSize")))
        properties.add(p.getProperty("gui.defaultBoxSize"));

      if (AllowedProperties.validate(p.getProperty("gui.buttonShape")))
        properties.add(p.getProperty("gui.buttonShape"));

    }

    return properties;
  }

  public static Map<String, String> keyyedProp() throws IOException {
    HashMap<String, String> properties = new HashMap<>();
    p = new Properties();
    if (!new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE).exists()) {
      new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE).createNewFile();
      reset();
    }
    try (InputStream isr = new FileInputStream(new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE))) {
      p.load(isr);
      if (AllowedProperties.validate(p.getProperty("explorer.defaultDir")))
        properties.put("explorer.defaultDir", p.getProperty("explorer.defaultDir"));

      if (AllowedProperties.validate(p.getProperty("gui.defaultTheme")))
        properties.put("gui.defaultTheme", p.getProperty("gui.defaultTheme"));

      if (AllowedProperties.validate(p.getProperty("runner.disableCache")))
        properties.put("runner.disableCache", p.getProperty("runner.disableCache"));

      if (AllowedProperties.validate(p.getProperty("gui.defaultBoxSize")))
        properties.put("gui.defaultBoxSize", p.getProperty("gui.defaultBoxSize"));

      if(AllowedProperties.validate(p.getProperty("gui.buttonShape"))){
        properties.put("gui.buttonShape", p.getProperty("gui.buttonShape"));
      }
    }
    return properties;
  }

  public static boolean hasAllProperties() throws IOException {
    p = new Properties();
    try (InputStream isr = new FileInputStream(new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE))) {
      p.load(isr);
      if (p.getProperty("gui.defaultBoxSize") == null || p.getProperty("gui.defaultBoxSize").isEmpty())
        return false;
      if (!AllowedProperties.validate(p.getProperty("explorer.defaultDir"))
          || !AllowedProperties.validate(p.getProperty("runner.disableCache"))
          || !AllowedProperties.validate(p.getProperty("gui.defaultTheme")) ||
          !AllowedProperties.validate(p.getProperty("gui.buttonShape")))
        return false;
    }
    return true;
  }

  public static String getProp(String key) {
    return setProp.get(key);
  }

  public static String setProperty(String key, String value) throws IOException {
    p.setProperty(key, value);
    try (OutputStream os = new FileOutputStream(
        new File(Items.items[1] + System.getProperty("file.separator") + Sources.PROPERTIES_FILE))) {
      p.store(os, Items.PROPERTIES_HEADER_COMMENT);
    }
    return value;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String key : setProp.keySet()) {
      sb.append(key + "=" + setProp.get(key) + "\n");
    }
    return sb.toString();
  }

  public static boolean reset() {
    try (OutputStream os = new FileOutputStream(new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE))) {
      p.setProperty("explorer.defaultDir", DefProperties.DEFAULT_DIR);
      p.setProperty("gui.defaultTheme", DefProperties.DEFAULT_GUI_LAF);
      p.setProperty("runner.disableCache", DefProperties.DISABLE_CACHE);
      p.setProperty("gui.defaultBoxSize", String.valueOf(DefProperties.DEFAULT_BOX_SIZE));
      p.setProperty("gui.buttonShape", String.valueOf(DefProperties.DEFAULT_BUTTON_SHAPE));

      p.store(os, Items.PROPERTIES_HEADER_COMMENT);
    } catch (IOException e) {
      return false;
    }
    return true;
  }

  public String getVal(String key) {
    return p.getProperty(key);
  }

  public static void main(String[] args) {
    try {
      PropertiesReader pr = new PropertiesReader();
      System.out.println(pr.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
