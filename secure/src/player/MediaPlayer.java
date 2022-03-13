package player;

import com.goxr3plus.streamplayer.stream.*;

import java.io.File;

import java.net.URL;

/**
 * <h1>This is a wrapper class</h1>
 * <p>
 *     Allows for the usage of the other classes in the standard
 *     Java Media Stream Player.
 *
 *     It particularly Unwraps much if not most of the classes found within
 * </p>
 *
 * @author Jack Meng
 * @see com.goxr3plus.streamplayer.stream.StreamPlayer
 */

public class MediaPlayer extends StreamPlayer {
    public final File f;
    public MediaPlayer(File f) {
        this.f = f;
    }

    public MediaPlayer(URL file) {
        this.f = new File(file.getFile());
    }

    public MediaPlayer(String absolutePath) {
        this.f = new File(absolutePath);
    }

    /**
     * <p>
     *  Creates a handledOpener and opens the current file
     * </p>
     */
    public synchronized void handledOpener() {
        try {
            open(f);
        } catch (com.goxr3plus.streamplayer.stream.StreamPlayerException e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>
     *  Plays the current file, however will reopen the file.
     * </p>
     */
    public synchronized void handledPlayer() {
        try {
            open(f);
            play();
        } catch (StreamPlayerException e) {
            e.printStackTrace();
        }
    }
}
