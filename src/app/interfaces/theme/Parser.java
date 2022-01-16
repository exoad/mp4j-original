package app.interfaces.theme;

import app.core.PropertiesReader;
import app.interfaces.theme.rules.*;

import java.io.IOException;

public class Parser {
  private final String theme;
  private final String[] themePartsRegularDark = {
      "regulardark",
      "regular dark",
      "REGULARDARK",
      "Regular Dark",
  };
  private final String[] themePartsVuesion = {
      "vuesion",
      "VUESION",
      "Vuesion"
  };
  private final String[] themePartsOneDark = {
      "onedark",
      "one dark",
      "ONEDARK",
      "One Dark",
  };
  private final String[] themePartsArcDark = {
      "arcdark",
      "arc dark",
      "ARCDARK",
      "Arc Dark",
  };
  private final String[] themePartsMaterialDark = {
      "materialdark",
      "material dark",
      "MATERIALDARK",
      "Material Dark",
  };
  private final String[] themePartsDracula = {
      "dracula",
      "DRACULA",
      "Dracula"
  };
  private final String[] themePartsNord = {
      "nord",
      "NORD",
      "Nord"
  };
  private final String[] themePartsGruvbox = {
      "gruvbox",
      "GRUVBOX",
      "Gruvbox"
  };
  private final String[] themePartsSolarized = {
      "solarized",
      "SOLARIZED",
      "Solarized"
  };
  private final String[] themePartsRegularLight = {
      "regularlight",
      "regular light",
      "REGULARLIGHT",
      "Regular Light",
  };

  public Parser(String theme) {
    assert theme != null;
    this.theme = theme;
  }

  private boolean contains(String[] array, String value) {
    for (String s : array) {
      if (s.equals(value)) {
        return true;
      }
    }
    return false;
  }

  public Refresh getTheme() {
    if (contains(themePartsRegularDark, theme)) {
      return new RegularDark();
    } else if (contains(themePartsVuesion, theme)) {
      return new Vuesion();
    } else if (contains(themePartsOneDark, theme)) {
      return new OneDark();
    } else if (contains(themePartsArcDark, theme)) {
      return new ArcDark();
    } else if (contains(themePartsMaterialDark, theme)) {
      return new Material();
    } else if (contains(themePartsDracula, theme)) {
      return new Dracula();
    } else if (contains(themePartsNord, theme)) {
      return new Nord();
    } else if (contains(themePartsGruvbox, theme)) {
      return new Gruvbox();
    } else if (contains(themePartsSolarized, theme)) {
      return new SolarizedLight();
    } else if (contains(themePartsRegularLight, theme)) {
      return new RegularLight();
    } else {
      return new RegularDark();
    }
  }

  public void parseThemeToProperty() throws IOException {
    PropertiesReader.setProperty("gui.defaultTheme", getTheme().toString());
  }
}
