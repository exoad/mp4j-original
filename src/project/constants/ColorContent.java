package project.constants;

import java.awt.Color;

import project.connection.resource.ResourceFolder;
import project.usables.ColorTool;

/**
 * Handles the Colors used throughout the project in a big constant
 * source file
 * 
 * This class is still waiting for the conversion of many other
 * RGB values to HEX and resource avaliable colors.
 * 
 * @author Jack Meng
 */
public class ColorContent {
  public static final Color BORDER = ColorTool
      .hexToRGBA(ResourceFolder.pm.get(ProjectManager.KEY_BORDER_HEXCOLOR));
  public static final Color VOLUME_SLIDER_HEARING_LOSS_FG = new Color(250, 45, 45);
  public static final Color VOLUME_SLIDER_BG = ColorTool
      .hexToRGBA(ResourceFolder.pm.get(ProjectManager.KEY_VOLUME_SLIDER_BG_HEX_COLOR));
  public static final Color VOLUME_SLIDER_WARNING_FG = new Color(255, 170, 43);
  public static final Color VOLUME_SLIDER_NORMAL_FG = ColorTool.hexToRGBA(
      ResourceFolder.pm.get(ProjectManager.KEY_VOLUME_SLIDER_FG_HEX_COLOR));
  public static final Color VOLUME_SLIDER_MUTED_FG = new Color(105, 105, 105);
  public static final Color PROGRESS_SLIDER_NORMAL = ColorTool.hexToRGBA(
      ResourceFolder.pm.get(ProjectManager.KEY_PROGRESS_SLIDER_NORMAL_HEX_COLOR));
  public static final Color WAVE_FORM_BAR_X = ColorTool
      .hexToRGBA(ResourceFolder.pm.get(ProjectManager.KEY_WAVEFORM_UPPER_X_HEXCOLOR));
  public static final Color WAVE_FORM_LOWER_X = ColorTool
      .hexToRGBA(ResourceFolder.pm.get(ProjectManager.KEY_WAVEFORM_LOWER_X_HEXCOLOR));
  public static final Color AIE_MAJOR_TAG_FG = ColorTool
      .hexToRGBA(ResourceFolder.pm.get(ProjectManager.KEY_AIE_MAJOR_TAG_HEX_COLOR));
  public static final Color AIE_MINOR_TAG_FG = ColorTool
      .hexToRGBA(ResourceFolder.pm.get(ProjectManager.KEY_AIE_MINOR_TAG_HEX_COLOR));

  /// public static final Color WAVE_FORM_BAR_Y = new Color(245, 171, 98);
  /// public static final Color WAVE_FORM_LOWER_Y = new Color(255, 120, 203);
  /// public static final Color WAVE_FORM_LOWER_Z = new Color(253, 133, 112);
  /// public static final Color WAVE_FORM_LOWER_A = new Color(33, 250, 185);
  private ColorContent() {
  }
}
