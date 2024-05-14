package logic;

import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import screen.*;
import sharedObject.AudioLoader;

public class SwitchPage {
    private static MapScreen mapScreen;
    private static KitchenScreen kitchenScreen;
    private static GameOverScreen gameOverScreen;
    private static StartScreen startScreen;
    private static WinScreen winScreen;

    public static void init(Stage primaryStage){
        mapScreen = new MapScreen(primaryStage);
        kitchenScreen = new KitchenScreen(primaryStage);
        gameOverScreen = new GameOverScreen(primaryStage);
        startScreen = new StartScreen(primaryStage);
        winScreen = new WinScreen(primaryStage);
    }
    public static void switchToMapScreen(Stage primaryStage){
        GameController.setCurrentScreenID(2);
        primaryStage.setScene(mapScreen.getScene());
        StartScreen.isPlaying = false;
        startScreen.stopLoop();
    }

    public static void switchToKitchenScreen(Stage primaryStage) {
        GameController.setCurrentScreenID(3);
        primaryStage.setScene(kitchenScreen.getScene());
    }
    public static void switchToGameOverScreen(Stage primaryStage){
        GameController.setCurrentScreenID(4);
        gameOverScreen.draw(gameOverScreen.gc);
        primaryStage.setScene(gameOverScreen.getScene());
        MediaPlayer sound = AudioLoader.getMediaPlayer("audio/FailSound.mp3");
        sound.play();
    }

    public static void switchToStartScreen(Stage primaryStage) {
        GameController.setCurrentScreenID(1);
        primaryStage.setScene(startScreen.getScene());
        startScreen.drawMainComponent();
        //GameController.initGame();
    }

    public static void switchToWinScreen(Stage primaryStage){
        GameController.setCurrentScreenID(5);
        MediaPlayer sound = AudioLoader.getMediaPlayer("audio/WinSound.mp3");
        sound.play();
        primaryStage.setScene(winScreen.getScene());
        winScreen.drawMainComponent();
        mapScreen.stopTimer();

    }
}
