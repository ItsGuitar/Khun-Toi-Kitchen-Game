package sharedObject;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioLoader {
    public static MediaPlayer getMediaPlayer(String audioResource) {
        return new MediaPlayer(new Media(ClassLoader.getSystemResource(audioResource).toString()));
    }
}