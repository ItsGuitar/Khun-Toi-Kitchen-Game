package screen;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class WinScreen {
    private Stage primaryStage;
    public GraphicsContext gc;
    private Canvas canvas;
    private static StackPane root;
    private Scene scene;
    private ButtonGameScreen button;
    private static AnimationTimer backgroundLoop;
    public WinScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.canvas = new Canvas(800, 600);
        this.gc = canvas.getGraphicsContext2D();
        this.button = new ButtonGameScreen();

        draw(gc);
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(RenderableHolder.winScreen_background, 0, 0,800,600);
        root = new StackPane();
        root.setPrefSize(800,600);
        if (root.getChildren().contains(canvas)) {
            root.getChildren().remove(canvas);
        }
        root.getChildren().add(canvas); // Add the canvas to the root
        scene = new Scene(root);
        primaryStage.setScene(scene);
        drawMainComponent();
    }


    public void drawMainComponent(){
        final long startNanoTime = System.nanoTime();
        backgroundLoop = new AnimationTimer(){
            public void handle(long currentNanoTime){
                double t = (currentNanoTime - startNanoTime) / 1000000000.0; // convert to seconds
                double y_title = 10 + 10 * Math.sin(2 * t);

                // calculate x-coordinate for toi image
                double x_toi = 239 + 200 * Math.sin(1.5 * t);
                double y_toi = 200 + 20 * Math.sin(10 * t);

                // calculate rotation angle for toi image
                double angle_toi = 5 * Math.cos(12 * t);

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // clear the previous frame
                gc.drawImage(RenderableHolder.winScreen_background, 0, 0,800,600);
                gc.drawImage(RenderableHolder.winScreen_title,0,y_title,800,164);

                // draw toi image with rotation
                gc.save();
                gc.translate(x_toi + 322 / 2, y_toi + 480); // move origin to the bottom middle point of the image
                gc.rotate(angle_toi);
                gc.drawImage(RenderableHolder.winScreen_toi, -322 / 2, -480, 322, 480); // draw the image at the new origin
                gc.restore();
            }
        };
        backgroundLoop.start();

        button.setupIndividuallyButtonHover(button.backToStartScreen);
        button.setupButtonBackToStartScreen(primaryStage);
        button.backToStartScreen.setTranslateY(220);
        if (root.getChildren().contains(button.backToStartScreen)) {
            root.getChildren().remove(button.backToStartScreen);
        }
        root.getChildren().add(button.backToStartScreen);
    }

    public Scene getScene(){
        return scene;
    }
}
