package app.global;

/**
 * <h1>Sources</h1>
 * <p>
 * This class holds the constants for
 * all of the save file directories
 * and names that will be used to read
 * data after they have been saved to
 * the hard disk
 * <p>
 *
 * @author Jack Meng
 *
 * @since 1.1
 * @see app.global.Sources
 * @see app.Runner
 * @see app.global.Items
 */

public abstract class Sources {
  private Sources() {
  }
  public final static String LIFEPRESERVER_PREVDIR = "LifePreserver_prevdir.save";
  public final static String PROPERTIES_FILE = "MusicPlayer_Properties.properties";
}
