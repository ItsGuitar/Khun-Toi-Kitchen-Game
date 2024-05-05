package sharedObject;

import javafx.scene.media.AudioClip;

public class AudioLoader {
    public static AudioClip startScreen_background = new AudioClip(ClassLoader.getSystemResource("audio/StartScreen_background.mp3").toString());
}
