import com.goxr3plus.streamplayer.stream.*;

import java.io.File;

import java.net.URL;

public class MediaPlayer extends StreamPlayer {
    private File f;
    public MediaPlayer(File f) {
        this.f = f;
    }

    public MediaPlayer(URL file) {
        this.f = new File(file.getFile());
    }
}
