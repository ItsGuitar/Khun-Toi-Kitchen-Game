package logic;

import javafx.stage.Stage;
import screen.GameOverScreen;
import screen.KitchenScreen;
import screen.MapScreen;

public class SwitchPage {
    private static MapScreen mapScreen;
    private static KitchenScreen kitchenScreen;
    private static GameOverScreen gameOverScreen;

    public static void init(Stage primaryStage){
        mapScreen = new MapScreen(primaryStage);
        kitchenScreen = new KitchenScreen(primaryStage);
        gameOverScreen = new GameOverScreen(primaryStage);
    }
    public static void switchToMapScreen(Stage primaryStage){
        primaryStage.setScene(mapScreen.getScene());
    }

    public static void switchToKitchenScreen(Stage primaryStage) {
        primaryStage.setScene(kitchenScreen.getScene());
    }
    public static void switchToGameOverScreen(Stage primaryStage){
        primaryStage.setScene(gameOverScreen.getScene());
    }
}
