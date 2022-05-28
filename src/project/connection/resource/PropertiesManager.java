package project.connection.resource;

import java.util.Properties;
import java.util.Map;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class PropertiesManager {
  private Map<String, String> map;
  private Map<String, String[]> allowedProperties;
  private Properties util;
  private FileReader fr;
  private String location;

  public PropertiesManager(Map<String, String> defaultProperties, Map<String, String[]> allowedProperties,
      String location) {
    this.map = defaultProperties;
    this.allowedProperties = allowedProperties;
    this.location = location;
    util = new Properties();
    if (!new File(location).exists()) {
      try {
        new File(location).createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      createWithDefaultVals();
    } else {
      checkAllPropertiesExistence();
    }
  }

  /// BEGIN PRIVATE METHODS

  private void checkAllPropertiesExistence() {
    try {
      util.load(new FileReader(location));
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (String key : allowedProperties.keySet()) {
      if (!util.containsKey(key)) {
        util.setProperty(key, map.get(key));
      }
    }
    save();
  }

  private void wipeContents() {
    try {
      FileWriter writer = new FileWriter(location);
      writer.write("");
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void createWithDefaultVals() {
    wipeContents();
    try {
      fr = new FileReader(location);
      util.load(fr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    for (Map.Entry<String, String> entry : map.entrySet()) {
      util.setProperty(entry.getKey(), entry.getValue());
    }
    save();
  }

  /// END PRIVATE METHODS
  /// BEGIN PUBLIC METHODS

  public Map<String, String> getDefaultProperties() {
    return map;
  }

  public void setDefaultProperties(Map<String, String> map) {
    this.map = map;
    createWithDefaultVals();
  }

  public String getLocation() {
    return location;
  }

  public boolean contains(String key) {
    return util.containsKey(key);
  }

  public boolean allowed(String key, String value) {
    if (allowedProperties.containsKey(key)) {
      if (allowedProperties.get(key).length == 0) {
        return true;
      }
      String[] allowedValues = allowedProperties.get(key);
      for (String allowedValue : allowedValues) {
        if (allowedValue.equals(value)) {
          return true;
        }
      }
    }
    return false;
  }

  public String get(String key) {
    try {
      util.load(fr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    if (util.getProperty(key) == null) {
      util.setProperty(key, map.get(key));
      save();
    }
    return util.getProperty(key) == null || !allowed(key, util.getProperty(key)) ? map.get(key) : util.getProperty(key);
  }

  public void set(String key, String value, String comments) {
    try {
      util.load(fr);
    } catch (IOException e) {
      e.printStackTrace();
    }
    util.setProperty(key, value);
    try {
      FileWriter writer = new FileWriter(location);
      util.store(writer, comments);
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void save(String comments) {
    try {
      util.store(new FileWriter(location), comments);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void save() {
    save("");
  }

  public boolean open() {
    if (!new File(location).exists()) {
      File f = new File(location);
      try {
        f.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
      System.out.println(f.getAbsolutePath());
      createWithDefaultVals();
    } else {
      try {
        fr = new FileReader(location);
        util.load(fr);
      } catch (IOException e) {
        e.printStackTrace();
        return false;
      }
    }
    return true;
  }

  /// END PUBLIC METHODS
}