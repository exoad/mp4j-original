import player.MediaPlayer;
public class MediaPlayerTester {
    public static void main(String[] args) throws com.goxr3plus.streamplayer.stream.StreamPlayerException {
        MediaPlayer mp = new MediaPlayer("D:/zip/projects/MusicPlayer/secure/src-test");
        mp.handledOpener();
        mp.play();

    }
}
