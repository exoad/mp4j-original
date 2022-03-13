package player;

import com.goxr3plus.streamplayer.stream.*;

public class MediaException extends StreamPlayerException {
    /**
     * Constructor.
     *
     * @param paramString
     *         String Parameter
     */
    public MediaException(com.goxr3plus.streamplayer.stream.StreamPlayerException.PlayerException paramString) {
        super(paramString);
    }

    /**
     * Constructor.
     *
     * @param paramThrowable
     *         the param throwable
     */
    public MediaException(Throwable paramThrowable) {
        super(paramThrowable);
    }

    /**
     * Constructor.
     *
     * @param paramString
     *         the param string
     * @param paramThrowable
     *         the param throwable
     */
    public MediaException(com.goxr3plus.streamplayer.stream.StreamPlayerException.PlayerException paramString, Throwable paramThrowable) {
        super(paramString, paramThrowable);
    }
}
