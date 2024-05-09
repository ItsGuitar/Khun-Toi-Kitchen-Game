package screen;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class GameOverScreen {
    private Stage primaryStage;
    private GraphicsContext gc;
    private Canvas canvas;
    private static StackPane root;
    private Scene scene;
    public GameOverScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.canvas = new Canvas(800, 600);
        this.gc = canvas.getGraphicsContext2D();
        draw(gc);
    }

    void draw(GraphicsContext gc){
        gc.drawImage(RenderableHolder.gameOverScreen_background, 0, 0,800,600);
        root = new StackPane();
        root.setPrefSize(800,600);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        root.getChildren().addAll(canvas);
        scene = new Scene(root);
        primaryStage.setScene(scene);
    }

    public Scene getScene(){
        return scene;
    }
}
