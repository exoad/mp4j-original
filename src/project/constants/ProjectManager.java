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

  public static final String[] EXT_RSC_FOLDERS = new String[] { "logs", "bin", "cache" };
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
  public static final String KEY_VOLUME_SLIDER_MUTED_FG_HEX_COLOR = "gui.volumeslider_muted_fg.hex_color";
  public static final String KEY_VOLUME_SLIDER_WARNING_FG_HEX_COLOR = "gui.volumeslider_warning.hex_color";
  public static final String KEY_VOLUME_SLIDER_HEARING_LOSS_FG_HEX_COLOR = "gui.volumeslider_hearing_loss.hex_color";
  public static final String KEY_PROGRESS_SLIDER_NORMAL_HEX_COLOR = "gui.progressslider_normal.hex_color";
  public static final String KEY_AIE_MAJOR_TAG_HEX_COLOR = "gui.aie_major_tag.hex_color";
  public static final String KEY_AIE_MINOR_TAG_HEX_COLOR = "gui.aie_minor_tag.hex_color";
  public static final String KEY_APPLICATION_TOTAL_LAF_MODE = "gui.laf_mode";

  public static final String VALUE_WAVEFORM_LOWER_X_HEXCOLOR = "#BBC4EE";
  public static final String VALUE_WAVEFORM_UPPER_X_HEXCOLOR = "#6B75DB";
  public static final String VALUE_BORDER_HEXCOLOR = "#BBC4EE";
  public static final String VALUE_VOLUME_SLIDER_BG_HEX_COLOR = "#BBC4EE";
  public static final String VALUE_VOLUME_SLIDER_FG_HEX_COLOR = "#6956F5";
  public static final String VALUE_VOLUME_SLIDER_NORMAL_HEX_COLOR = "#5D6AF5";
  public static final String VALUE_AIE_MAJOR_TAG_HEX_COLOR = "#6c32c9";
  public static final String VALUE_AIE_MINOR_TAG_HEX_COLOR = "#7f32fa";
  public static final String VALUE_VOLUME_SLIDER_WARNING_FG_HEX_COLOR = "#ffaa2b";
  public static final String VALUE_VOLUME_SLIDER_MUTED_FG_HEX_COLOR = "#696969";
  public static final String VALUE_VOLUME_SLIDER_HEARING_LOSS_FG_HEX_COLOR = "#fa2d2d";
  public static final String VALUE_APPLICATION_TOTAL_LAF_MODE = "1"; // 1 = light mode, 0 = dark mode

  public static Map<String, String> getDefaultPropertiesCustomization() {
    Map<String, String> properties = new HashMap<>();
    properties.put(KEY_WAVEFORM_LOWER_X_HEXCOLOR, VALUE_WAVEFORM_LOWER_X_HEXCOLOR);
    properties.put(KEY_WAVEFORM_UPPER_X_HEXCOLOR, VALUE_WAVEFORM_UPPER_X_HEXCOLOR);
    properties.put(KEY_BORDER_HEXCOLOR, VALUE_BORDER_HEXCOLOR);
    properties.put(KEY_VOLUME_SLIDER_BG_HEX_COLOR, VALUE_VOLUME_SLIDER_BG_HEX_COLOR);
    properties.put(KEY_VOLUME_SLIDER_FG_HEX_COLOR, VALUE_VOLUME_SLIDER_FG_HEX_COLOR);
    properties.put(KEY_PROGRESS_SLIDER_NORMAL_HEX_COLOR, VALUE_VOLUME_SLIDER_NORMAL_HEX_COLOR);
    properties.put(KEY_AIE_MAJOR_TAG_HEX_COLOR, VALUE_AIE_MAJOR_TAG_HEX_COLOR);
    properties.put(KEY_AIE_MINOR_TAG_HEX_COLOR, VALUE_AIE_MINOR_TAG_HEX_COLOR);
    properties.put(KEY_VOLUME_SLIDER_WARNING_FG_HEX_COLOR, VALUE_VOLUME_SLIDER_WARNING_FG_HEX_COLOR);
    properties.put(KEY_VOLUME_SLIDER_MUTED_FG_HEX_COLOR, VALUE_VOLUME_SLIDER_MUTED_FG_HEX_COLOR);
    properties.put(KEY_VOLUME_SLIDER_HEARING_LOSS_FG_HEX_COLOR, VALUE_VOLUME_SLIDER_HEARING_LOSS_FG_HEX_COLOR);
    properties.put(KEY_APPLICATION_TOTAL_LAF_MODE, VALUE_APPLICATION_TOTAL_LAF_MODE);
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
    properties.put(KEY_VOLUME_SLIDER_WARNING_FG_HEX_COLOR, new String[] {});
    properties.put(KEY_VOLUME_SLIDER_MUTED_FG_HEX_COLOR, new String[] {});
    properties.put(KEY_VOLUME_SLIDER_HEARING_LOSS_FG_HEX_COLOR, new String[] { "1", "0" });
    return properties;
  }
}
