package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import logic.SwitchPage;
import screen.GameOverScreen;
import screen.KitchenScreen;
import screen.MapScreen;
import screen.StartScreen;

public class Main extends Application {
    private static MapScreen mapScreen;
    private static KitchenScreen kitchenScreen;
    private static GameOverScreen gameOverScreen;
    private static boolean isFirstStart = true;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the MapScreen and KitchenScreen
        SwitchPage.init(primaryStage);

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

        java.util.logging.LogManager.getLogManager().reset();
    }

    public static void main(String[] args) {
        launch(args);
    }
}