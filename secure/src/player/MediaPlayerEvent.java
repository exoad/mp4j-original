package player;

import com.goxr3plus.streamplayer.stream.StreamPlayerEvent;

public class MediaPlayerEvent extends StreamPlayerEvent {

    /**
     * Constructor.
     *
     * @param source
     *         the source
     * @param status
     *         the status
     * @param encodededStreamPosition
     *         the stream position
     * @param description
     */
    public MediaPlayerEvent(Object source, com.goxr3plus.streamplayer.enums.Status status, int encodededStreamPosition, Object description) {
        super(source, status, encodededStreamPosition, description);
    }
}
