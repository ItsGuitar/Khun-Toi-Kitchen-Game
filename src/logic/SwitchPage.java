package logic;

import javafx.stage.Stage;
import screen.GameOverScreen;
import screen.KitchenScreen;
import screen.MapScreen;
import screen.StartScreen;

public class SwitchPage {
    private static MapScreen mapScreen;
    private static KitchenScreen kitchenScreen;
    private static GameOverScreen gameOverScreen;
    private static StartScreen startScreen;

    public static void init(Stage primaryStage){
        mapScreen = new MapScreen(primaryStage);
        kitchenScreen = new KitchenScreen(primaryStage);
        gameOverScreen = new GameOverScreen(primaryStage);
        startScreen = new StartScreen(primaryStage);
    }
    public static void switchToMapScreen(Stage primaryStage){
        primaryStage.setScene(mapScreen.getScene());
    }

    public static void switchToKitchenScreen(Stage primaryStage) {
        primaryStage.setScene(kitchenScreen.getScene());
    }
    public static void switchToGameOverScreen(Stage primaryStage){
        gameOverScreen.draw(gameOverScreen.gc);
        primaryStage.setScene(gameOverScreen.getScene());
    }

    public static void switchtoStartScreen(Stage primaryStage) {
        primaryStage.setScene(startScreen.getScene());
        startScreen.drawMainComponent();
        //GameController.initGame();
    }
}
