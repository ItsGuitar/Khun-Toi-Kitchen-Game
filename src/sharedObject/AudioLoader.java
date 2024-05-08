package sharedObject;

import javafx.scene.media.AudioClip;

public class AudioLoader {
    public static AudioClip startScreen_background = new AudioClip(ClassLoader.getSystemResource("audio/StartScreen_background.mp3").toString());
    public static AudioClip mapScreen_lootOpen = new AudioClip(ClassLoader.getSystemResource("audio/MapScreen_lootOpen.mp3").toString());
    public static AudioClip mapScreen_lootClose = new AudioClip(ClassLoader.getSystemResource("audio/MapScreen_lootClose.mp3").toString());
}
