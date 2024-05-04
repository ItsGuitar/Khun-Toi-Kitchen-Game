package application;

import javafx.application.Application;
import javafx.stage.Stage;
import screen.StartScreen;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Start the application
        StartScreen start = new StartScreen(primaryStage);
        primaryStage.setTitle("Krua Khun Toi (Weird Edition)");
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
