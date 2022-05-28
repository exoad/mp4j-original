package project.constants;

import java.util.Map;
import java.util.HashMap;

/**
 * Similar to {@link PreConfig}, but for the runtime style.
 * 
 * @author Jack Meng
 */
public class ProjectManager {
  private ProjectManager() {
  }
  public static final String[] EXT_RSC_FOLDERS = new String[] {"logs", "bin", "cache"};
  public static final String GITHUB_PROJECT_URL = "https://github.com/Exoad4JVM/mp4j";
  public static final boolean DEBUG_LAYOUT = false;
  public static final boolean PRODUCTION_STYLE = true;
  public static final boolean DISABLE_IO = false;
  public static final String EXT_RSC_FOLDER = "halcyon-mp4j";
  public static final String KEY_WAVEFORM_LOWER_X_HEXCOLOR = "waveform.lower.hex_color";
  public static final String KEY_WAVEFORM_UPPER_X_HEXCOLOR = "waveform.upper.hex_color";
  public static final String KEY_BORDER_HEXCOLOR = "gui.border.hex_color";
  public static final String KEY_VOLUME_SLIDER_BG_HEX_COLOR = "gui.volumeslider_bg.hex_color";
  public static final String KEY_VOLUME_SLIDER_FG_HEX_COLOR = "gui.volumeslider_fg.hex_color";
  public static final String KEY_PROGRESS_SLIDER_NORMAL_HEX_COLOR = "gui.progressslider_normal.hex_color";
  public static final String KEY_AIE_MAJOR_TAG_HEX_COLOR = "gui.aie_major_tag.hex_color";
  public static final String KEY_AIE_MINOR_TAG_HEX_COLOR = "gui.aie_minor_tag.hex_color";

  public static Map<String, String> getDefaultPropertiesCustomization() {
    Map<String, String> properties = new HashMap<>();
    properties.put(KEY_WAVEFORM_LOWER_X_HEXCOLOR, "#BBC4EE");
    properties.put(KEY_WAVEFORM_UPPER_X_HEXCOLOR, "#6B75DB");
    properties.put(KEY_BORDER_HEXCOLOR, "#BBC4EE");
    properties.put(KEY_VOLUME_SLIDER_BG_HEX_COLOR, "#BBC4EE");
    properties.put(KEY_VOLUME_SLIDER_FG_HEX_COLOR, "#6956F5");
    properties.put(KEY_PROGRESS_SLIDER_NORMAL_HEX_COLOR, "#5D6AF5");
    properties.put(KEY_AIE_MAJOR_TAG_HEX_COLOR, "#6c32c9");
    properties.put(KEY_AIE_MINOR_TAG_HEX_COLOR, "#7f32fa");
    return properties;
  }

  public static Map<String, String[]> getAllowedPropertiesCustomization() {
    Map<String, String[]> properties = new HashMap<>();
    properties.put(KEY_WAVEFORM_LOWER_X_HEXCOLOR, new String[] {});
    properties.put(KEY_WAVEFORM_UPPER_X_HEXCOLOR, new String[] {});
    properties.put(KEY_BORDER_HEXCOLOR, new String[] {});
    properties.put(KEY_VOLUME_SLIDER_BG_HEX_COLOR, new String[] {});
    properties.put(KEY_VOLUME_SLIDER_FG_HEX_COLOR, new String[] {});
    properties.put(KEY_PROGRESS_SLIDER_NORMAL_HEX_COLOR, new String[] {});
    properties.put(KEY_AIE_MAJOR_TAG_HEX_COLOR, new String[] {});
    properties.put(KEY_AIE_MINOR_TAG_HEX_COLOR, new String[] {});
    return properties;
  }
}
