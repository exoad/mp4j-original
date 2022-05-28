package project.constants;

/**
 * Handles the Size constants for the program.
 * 
 * @author Jack Meng
 */
public final class Size {
  private Size() {}

  public static final int MIN_WIDTH = 900;
  public static final int MIN_HEIGHT = 210;

  public static final int WIDTH = 920;
  public static final int HEIGHT = 363;

  public static final int MAX_WIDTH = 970;
  public static final int MAX_HEIGHT = 360 + Size.HEIGHT / 5;

  public static final int PREV_HEIGHT = 390;

  /**
   * Bigger value here signifies bigger image size
   */
  public static final int PAUSE_PLAY_BUTTON_SIZE = 30;
  public static final int OK_BOX_SIZE = 20;

  public static final int BUTTONS_VIEW_ICONS_SIZE = 15;

  public static final String INDENT_SIZE = "   ";
}
