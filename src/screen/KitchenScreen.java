package screen;

import gui.*;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameController;
import logic.SwitchPage;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;

public class KitchenScreen{
    private Stage primaryStage;
    private Canvas backgroundCanvas;
    private Scene scene;
    public GraphicsContext backgroundGc;
    private ButtonGameScreen button;

    private static AnchorPane root;
    private AnimationTimer backgroundLoop;
    private ProgressBar progressBar;

    public KitchenScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.backgroundCanvas = new Canvas(800, 600);
        this.button = new ButtonGameScreen();
        this.backgroundGc = backgroundCanvas.getGraphicsContext2D();

        root = new AnchorPane();

        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        backgroundGc.setFill(Color.BLACK);

        button.setupIndividuallyButtonHover(button.backToMapButton);
        button.setupButtonBack(primaryStage);

        drawMainComponent();

        Text cookText = new Text("You cook for Khun Toi, or Khun Toi cooks you!");
        cookText.setFont(Font.font("null", FontWeight.BOLD, 20)); // Set the font to bold and size to 20
        cookText.setFill(Color.WHITE); // Set the fill color to white
        cookText.setStroke(Color.BLACK); // Set the stroke color to black
        cookText.setStrokeWidth(1); // Set the stroke width to 1
        cookText.setTranslateY(40);
        cookText.setTranslateX(200);

        MenuPane menuPane = new MenuPane(this);
        menuPane.setTranslateX(150);
        menuPane.setTranslateY(55);

        progressBar = new ProgressBar();
        progressBar.setProgress(GameController.getPercentageWinning() / 100.0);
        progressBar.setRotate(270);
        progressBar.setTranslateX(650);
        progressBar.setTranslateY(300);
        progressBar.setPrefWidth(200); // Set the preferred width to 200
        progressBar.setPrefHeight(40);
        progressBar.setStyle("-fx-accent: orange;");
        root.getChildren().addAll(backgroundCanvas,button.backToMapButton,cookText,menuPane,GUIManager.getKitchenDataPane(),progressBar);


        this.scene = new Scene(root);

        this.primaryStage.setScene(scene);
    }

    public void drawMainComponent(){
        final long startNanoTime = System.nanoTime();
        backgroundLoop = new AnimationTimer(){
            public void handle(long currentNanoTime){
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;

                // Clear the canvas
                backgroundGc.clearRect(0, 0, 800, 600);

                // Draw the background image
                backgroundGc.drawImage(RenderableHolder.kitchenScreen_background,0, 0, 800, 600);

                // Save the current state of the GraphicsContext
                backgroundGc.save();

                // Translate to the center of the image
                backgroundGc.translate(614, 595);

                // Rotate the GraphicsContext
                double rotationAngle = 5 * Math.sin(3*t);
                backgroundGc.rotate(rotationAngle);

                backgroundGc.drawImage(RenderableHolder.kitchenScreen_toi, -150, -450, 350, 496);

                // Restore the state of the GraphicsContext
                backgroundGc.restore();

                // Draw the table image
                backgroundGc.drawImage(RenderableHolder.kitchenScreen_table,160, 100, 667, 500);

                updateProgressBar();
            }
        };
        backgroundLoop.start();
    }
    public Scene getScene(){
        return scene;
    }
    public static void removeTime(){
        root.getChildren().remove(TimerPane.getInstance());
    }
    public static void addTime(){
        root.getChildren().add(TimerPane.getInstance());
    }

    public void updateProgressBar() {
        Platform.runLater(() -> {
            progressBar.setProgress(GameController.getPercentageWinning() / 100.0);
        });
        if(GameController.getPercentageWinning() >= 100){
            //MediaPlayer sound = AudioLoader.getMediaPlayer("audio/WinSound.mp3");
            //sound.play();
            GameController.setPercentageWinning(0);
            //AudioLoader.winScreen_background.play();
            SwitchPage.switchToWinScreen(primaryStage);
        }
    }

}
