package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import logic.SwitchPage;
import sharedObject.RenderableHolder;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        SwitchPage.init(primaryStage);
        SwitchPage.switchToStartScreen(primaryStage);
        primaryStage.setTitle("Khun Toi's Kitchen : 10/10 Challenge");
        primaryStage.setResizable(false);

        // Set the action when the application window is closed
        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.getIcons().add(RenderableHolder.logo);
        primaryStage.show();

        // Reset the log manager
        java.util.logging.LogManager.getLogManager().reset();
    }
    public static void main(String[] args) {
        launch(args);
    }
}