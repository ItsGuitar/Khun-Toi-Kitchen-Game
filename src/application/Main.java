package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import screen.KitchenScreen;
import screen.MapScreen;
import screen.StartScreen;

public class Main extends Application {
    private static MapScreen mapScreen;
    private static KitchenScreen kitchenScreen;
    private static boolean isFirstStart = true;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the MapScreen and KitchenScreen
        mapScreen = new MapScreen(primaryStage);
        kitchenScreen = new KitchenScreen(primaryStage);

        // Start the application
        if (isFirstStart) {
            StartScreen start = new StartScreen(primaryStage);
            isFirstStart = false;
        } else {
            primaryStage.setScene(mapScreen.getScene());
        }

        primaryStage.setTitle("Khun Toi's Kitchen : 10/10 Challenge");
        primaryStage.setResizable(false);

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        primaryStage.show();
    }

    public static void switchToMapScreen(Stage primaryStage){
        primaryStage.setScene(mapScreen.getScene());
    }

    public static void switchToKitchenScreen(Stage primaryStage) {
        primaryStage.setScene(kitchenScreen.getScene());
    }
    public static void main(String[] args) {
        launch(args);
    }
}