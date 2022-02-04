package app.core;

import java.util.Properties;

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
import java.util.Map;

/**
 * @author Jack Meng
 */
public class PropertiesReader {
  private static Properties p;

  public PropertiesReader() {
    System.out.println("hasAll" + checkPropertiesAll() + "\nFile exists? " + new java.io.File(app.global.Items.items[1] + System.getProperty("file.separator") + app.global.Sources.PROPERTIES_FILE).exists());
    if (!new java.io.File(app.global.Items.items[1] + "/" + app.global.Sources.PROPERTIES_FILE).exists()) {
      reset();
    }
  }

  public Map<String, String> keyyedProp() throws IOException {
    HashMap<String, String> properties = new HashMap<>();
    p = new Properties();
    if (!new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE).exists()) {
      reset();
    }
    try (InputStream isr = new FileInputStream(app.global.Items.items[1] + "/" + app.global.Sources.PROPERTIES_FILE)) {
      p.load(isr);
      if (AllowedProperties.valTheme(p.getProperty("gui.defaultTheme")))
        properties.put("gui.defaultTheme", p.getProperty("gui.defaultTheme"));

      if (AllowedProperties.valCache(p.getProperty("runner.disableCache")))
        properties.put("runner.disableCache", p.getProperty("runner.disableCache"));

      if (AllowedProperties.valBoxSize(p.getProperty("gui.defaultBoxSize")))
        properties.put("gui.defaultBoxSize", p.getProperty("gui.defaultBoxSize"));

      if (AllowedProperties.valBox(p.getProperty("gui.buttonShape"))) {
        properties.put("gui.buttonShape", p.getProperty("gui.buttonShape"));
      }

      if (AllowedProperties.valTransparency(p.getProperty("gui.window_transparency"))) {
        properties.put("gui.window_transparency", p.getProperty("gui.window_transparency"));
      }
    }
    return properties;
  }

  public boolean checkPropertiesAll() {
    p = new Properties();
    if(!new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE).exists()) {
      return false;
    }
    try (InputStream isr = new FileInputStream(Items.items[1] + "/" + Sources.PROPERTIES_FILE)) {
      p.load(isr);

      if(!AllowedProperties.valTheme(p.getProperty("gui.defaultTheme")) || !AllowedProperties.valCache(p.getProperty("runner.disableCache")) || !AllowedProperties.valBoxSize(p.getProperty("gui.defaultBoxSize")) || !AllowedProperties.valBox(p.getProperty("gui.buttonShape")) || !AllowedProperties.valTransparency(p.getProperty("gui.window_transparency"))) {
        return false;
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return true;
  } 

  public String setProperty(String key, String value) throws IOException {
    p.setProperty(key, value);
    try (OutputStream os = new FileOutputStream(
        new File(Items.items[1] + System.getProperty("file.separator") + Sources.PROPERTIES_FILE))) {
      p.store(os, Items.PROPERTIES_HEADER_COMMENT);
    }
    return value;
  }

  public static boolean reset() {
    try {
      new File(Items.items[1] + "/" + app.global.Sources.PROPERTIES_FILE).createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    try (OutputStream os = new FileOutputStream(app.global.Items.items[1] + "/" + app.global.Sources.PROPERTIES_FILE)) {
      p = new java.util.Properties();
      p.setProperty("gui.defaultTheme", DefProperties.DEFAULT_GUI_LAF);
      p.setProperty("runner.disableCache", DefProperties.DISABLE_CACHE);
      p.setProperty("gui.defaultBoxSize", String.valueOf(DefProperties.DEFAULT_BOX_SIZE));
      p.setProperty("gui.buttonShape", DefProperties.DEFAULT_BUTTON_SHAPE);
      p.setProperty("gui.window_transparency", String.valueOf(DefProperties.DEFAULT_WINDOW_TRANSPARENCY));
      p.store(os, Items.PROPERTIES_HEADER_COMMENT);
    } catch (java.io.IOException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }

  public String getVal(String key) {
    return p.getProperty(key);
  }
}
