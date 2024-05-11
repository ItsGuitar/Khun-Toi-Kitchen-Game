package screen;

import application.Main;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.CornerRadii;
import javafx.stage.Stage;
import logic.SwitchPage;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;


public class StartScreen{
    private Stage primaryStage;
    private GraphicsContext gc;
    private Canvas canvas;
    public static StackPane root;
    private ButtonStartScreen buttons;
    private AnimationTimer backgroundLoop;
    private Scene scene;

    public StartScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.canvas = new Canvas(800, 600);
        this.gc = canvas.getGraphicsContext2D();
        this.buttons = new ButtonStartScreen();
        draw(gc);

    }

    void draw(GraphicsContext gc){
        gc.drawImage(RenderableHolder.startScreen_background, 0, 0,800,600);
        root = new StackPane();
        root.setPrefSize(800,600);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(canvas);
        scene = new Scene(root);
        primaryStage.setScene(scene);

        root.getChildren().addAll(buttons);
        //drawMainComponent(); //every component is drawn in here, then draw the title
        setUpButton();

    }

    public void drawMainComponent(){
        final long startNanoTime = System.nanoTime();
        System.out.println("ooposposfsf");
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
                AudioLoader.startScreen_background.setVolume(0.5);
                if(!AudioLoader.startScreen_background.isPlaying()){
                    AudioLoader.startScreen_background.play();
                }

            }
        };
        backgroundLoop.start();
    }

    public void setUpButton(){
        buttons.setupButtonExit();
        buttons.setupButtonHover();

        buttons.buttonStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                AudioLoader.startScreen_background.stop();
                backgroundLoop.stop();
                SwitchPage.switchToMapScreen(primaryStage);
                MapScreen.initializeGameAfterStart();
            }
        });
    }
    public Scene getScene(){
        return scene;
    }
}