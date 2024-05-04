package screen;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class StartScreen{
    private Stage primaryStage;
    private GraphicsContext gc;
    private Canvas canvas;
    public static StackPane root;

    public StartScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.canvas = new Canvas(1280, 720);
        this.gc = canvas.getGraphicsContext2D();
        draw(gc);
    }

    void draw(GraphicsContext gc){
        root = new StackPane();
        root.setPrefSize(1280,720);
        root.getChildren().addAll(canvas);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

    }
}