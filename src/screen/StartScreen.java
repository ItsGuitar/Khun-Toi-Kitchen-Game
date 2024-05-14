package screen;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import logic.SwitchPage;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;


public class StartScreen{
    private Stage primaryStage;
    private GraphicsContext gc;
    private Canvas canvas;
    public static StackPane root;
    private ButtonStartScreen buttons;
    private  AnimationTimer backgroundLoop;
    private Scene scene;
    private Alert currentAlert = null;
    private static MediaPlayer sound;
    public static boolean isPlaying = false;

    public StartScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.canvas = new Canvas(800, 600);
        this.gc = canvas.getGraphicsContext2D();
        this.buttons = new ButtonStartScreen();



        draw(gc);

    }

    private void draw(GraphicsContext gc){
        gc.drawImage(RenderableHolder.startScreen_background, 0, 0,800,600);
        root = new StackPane();
        root.setPrefSize(800,600);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(canvas);
        scene = new Scene(root);
        primaryStage.setScene(scene);

        root.getChildren().addAll(buttons);
        setUpButton();

    }

    public void drawMainComponent(){
        final long startNanoTime = System.nanoTime();
        backgroundLoop = new AnimationTimer(){
            public void handle(long currentNanoTime){
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                double x = 0;
                double y = 10 * Math.sin(2*t);
                gc.clearRect(x, y, 800, 250);

                // Redraw the images that are supposed to stay on the canvas
                gc.drawImage(RenderableHolder.startScreen_background, 0, 0,800,600);
                gc.drawImage(RenderableHolder.startScreen_toi, 280, 200,250,354);
                gc.drawImage(RenderableHolder.startScreen_oven, 280, 350,250,354);

                gc.drawImage(RenderableHolder.startScreen_title, x, y, 800, 250);

                //Audio
                if(!isPlaying){
                    isPlaying = true;
                    sound = AudioLoader.getMediaPlayer("audio/StartScreen_background.mp3");
                    sound.setVolume(0.5);
                    if(sound.getStatus() != MediaPlayer.Status.PLAYING){
                        sound.play();
                    }
                }

            }
        };
        backgroundLoop.start();
    }
    public void stopLoop(){
        backgroundLoop.stop();
    }

    public void setUpButton() {
        buttons.setupButtonExit();
        buttons.setupButtonHover();

        buttons.buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sound.stop();
                backgroundLoop.stop();
                SwitchPage.switchToMapScreen(primaryStage);
                MapScreen.initializeGameAfterStart();
            }
        });
        buttons.buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                sound.stop();
                backgroundLoop.stop();
                SwitchPage.switchToMapScreen(primaryStage);
                MapScreen.initializeGameAfterStart();
            }
        });
        buttons.buttonHowToPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (currentAlert != null) {
                    currentAlert.close();
                    currentAlert = null;
                }

                int autoCloseTime = 10; // Time in seconds
                currentAlert = new Alert(Alert.AlertType.INFORMATION);
                currentAlert.setTitle("How to Play");
                currentAlert.setHeaderText(null);
                currentAlert.setContentText("- Click on loot to find ingredients.\n" +
                        "- If you have enough ingredients, head to the kitchen and cook some food!\n" +
                        "- The more food you cook, the more points you'll get.\n" +
                        "- Your goal is to satisfy Khun Toi by reaching 10 points before the time runs out.\n" +
                        "- In a pinch, you can trade random ingredients for more time.\n" +
                        "- But be careful! If the timer hits zero, Khun Toi will be very angry!");

                // Set the logo
                ImageView logo = new ImageView(RenderableHolder.mapScreen_dance);
                currentAlert.setGraphic(logo);

                // Remove the default header (exclamation mark)
                currentAlert.setHeaderText(null);

                // Change the text of the OK button
                ButtonType okButtonType = new ButtonType("OK, I know how to play");
                currentAlert.getButtonTypes().setAll(okButtonType);

                currentAlert.initModality(Modality.NONE); // This allows the alert to lose focus
                currentAlert.show();

                // Set the mini logo on the top left
                Stage alertStage = (Stage) currentAlert.getDialogPane().getScene().getWindow();
                alertStage.getIcons().add(RenderableHolder.logo);

                // Auto close after autoCloseTime seconds
                Timeline autoCloseTimeline = new Timeline(new KeyFrame(
                        Duration.millis(autoCloseTime * 1000),
                        ae -> {
                            if (currentAlert != null) { // Add this line
                                currentAlert.close();
                                currentAlert = null;
                            }
                        }
                ));
                autoCloseTimeline.play();
            }
        });
    }
    public Scene getScene(){
        return scene;
    }
}