package main.core;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;

import main.Sources;
import main.core.rules.AllowedProperties;
import main.core.rules.DefProperties;
import main.Items;

public class PropertiesReader {
  private static Properties p;
  private static HashMap<String, String> setProp = new HashMap<>();

  public PropertiesReader() throws IOException {
    p = new Properties();
    new AllowedProperties();

    if (!hasAllProperties() || new File(Items.items[1] + "/" + main.Sources.PROPERTIES_FILE).exists()) {
      try (OutputStream os = new FileOutputStream(new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE))) {
        p.setProperty("explorer.defaultDir", DefProperties.DEFAULT_DIR);
        p.setProperty("gui.defaultTheme", DefProperties.DEFAULT_GUI_LAF);
        p.setProperty("runner.disableCache", DefProperties.DISABLE_CACHE);
        p.setProperty("gui.defaultBoxSize", String.valueOf(DefProperties.DEFAULT_BOX_SIZE));
        p.store(os, Items.PROPERTIES_HEADER_COMMENT);
      }
    }

    setProp = keyyedProp();

  }

  public static HashSet<String> generalProp() throws IOException, InvalidPropertiesFormatException {
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
    }

    return properties;
  }

  public static HashMap<String, String> keyyedProp() throws IOException, InvalidPropertiesFormatException {
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
          || !AllowedProperties.validate(p.getProperty("gui.defaultTheme")))
        return false;
    }
    return true;
  }

  public String getProp(String key) {
    return setProp.get(key);
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
