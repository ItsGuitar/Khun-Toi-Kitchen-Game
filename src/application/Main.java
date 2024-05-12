package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.SwitchPage;
import screen.GameOverScreen;
import screen.KitchenScreen;
import screen.MapScreen;
import screen.StartScreen;
import sharedObject.RenderableHolder;

public class Main extends Application {
    private static boolean isFirstStart = true;

    @Override
    public void start(Stage primaryStage) {
        SwitchPage.init(primaryStage);

        SwitchPage.switchtoStartScreen(primaryStage);

        primaryStage.setTitle("Khun Toi's Kitchen : 10/10 Challenge");
        primaryStage.setResizable(false);

        primaryStage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.getIcons().add(RenderableHolder.logo);

        primaryStage.show();

        java.util.logging.LogManager.getLogManager().reset();
    }

    public static void main(String[] args) {
        launch(args);
    }
}