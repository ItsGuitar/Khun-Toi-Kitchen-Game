package screen;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameController;
import sharedObject.AudioLoader;
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
        //gc.drawImage(RenderableHolder.winScreen_background, 0, 0,800,600);
        root = new StackPane();
        root.setPrefSize(800,600);
        root.getChildren().add(canvas);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        //drawMainComponent();
    }


    public void drawMainComponent(){
        final long startNanoTime = System.nanoTime();
        backgroundLoop = new AnimationTimer(){
            public void handle(long currentNanoTime){
                gc.drawImage(RenderableHolder.winScreen_background, 0, 0,800,600);
            }
        };
        backgroundLoop.start();
    }

    public static void stopLoop(){
        backgroundLoop.stop();
    }



    public Scene getScene(){
        return scene;
    }
}
