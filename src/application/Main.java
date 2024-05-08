package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import screen.StartScreen;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Start the application
        StartScreen start = new StartScreen(primaryStage);
        primaryStage.setTitle("Khun Toi's Kitchen : 10/10 Challenge");
        primaryStage.setResizable(false);

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
