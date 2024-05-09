package screen;

import gui.GUIManager;
import gui.TimerPane;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sharedObject.AudioLoader;
import sharedObject.RenderableHolder;

public class KitchenScreen{
    private Stage primaryStage;
    private Canvas backgroundCanvas;
    private Scene scene;
    public GraphicsContext backgroundGc;
    private ButtonGameScreen buttons;

    private static AnchorPane root;
    private AnimationTimer backgroundLoop;

    public KitchenScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.backgroundCanvas = new Canvas(800, 600);
        this.buttons = new ButtonGameScreen();
        this.backgroundGc = backgroundCanvas.getGraphicsContext2D();

        root = new AnchorPane();

        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        backgroundGc.setFill(Color.BLACK);

        buttons.setupIndividuallyButtonHover(buttons.backToMapButton);
        buttons.setupButtonBack(primaryStage);

        drawMainComponent();

        root.getChildren().addAll(backgroundCanvas,buttons.backToMapButton);


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
                backgroundGc.translate(575, 348);

                // Rotate the GraphicsContext
                double rotationAngle = 5 * Math.sin(3*t);
                backgroundGc.rotate(rotationAngle);

                backgroundGc.drawImage(RenderableHolder.kitchenScreen_toi, -175, -248 + 20 - 30, 350 * 1.1, 496 * 1.1);

                // Restore the state of the GraphicsContext
                backgroundGc.restore();

                // Draw the table image
                backgroundGc.drawImage(RenderableHolder.kitchenScreen_table,0, 0, 800, 600);
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

}
