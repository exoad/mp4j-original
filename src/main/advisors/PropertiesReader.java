package main.advisors;

import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;

import main.advisors.subadvisors.DefProperties;
import main.advisors.subadvisors.AllowedProperties;
import main.Sources;
import main.Items;

public class PropertiesReader {
  private static Properties p;
  private static AllowedProperties ap;

  public PropertiesReader() throws IOException {
    p = new Properties();
    ap = new AllowedProperties();

    try (OutputStream os = new FileOutputStream(new File(Items.items[1] + "/" + Sources.PROPERTIES_FILE))) {
      p.setProperty("explorer.defaultDir", DefProperties.DEFAULT_DIR);
      p.setProperty("gui.defaultTheme", DefProperties.DEFAULT_GUI_LAF);
      p.setProperty("runner.disableCache", DefProperties.DISABLE_CACHE);

      p.store(os, Items.PROPERTIES_HEADER_COMMENT);
    }

  }

  public static HashSet<String> generalProp(String source) throws IOException, InvalidPropertiesFormatException {
    HashSet<String> properties = new HashSet<>();

    try (InputStream isr = new FileInputStream(new File(source))) {
      p.load(isr);
      if (AllowedProperties.validate(p.getProperty("explorer.defaultDir")))
        properties.add(p.getProperty("explorer.defaultDir"));

      if (AllowedProperties.validate(p.getProperty("gui.defaultTheme")))
        properties.add(p.getProperty("gui.defaultTheme"));

      if (AllowedProperties.validate(p.getProperty("runner.disableCache")))
        properties.add(p.getProperty("runner.disableCache"));

    }

    return properties;
  }
}
