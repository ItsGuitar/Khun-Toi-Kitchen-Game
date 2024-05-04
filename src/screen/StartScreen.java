package screen;

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
import sharedObject.RenderableHolder;

import java.awt.*;

public class StartScreen{
    private Stage primaryStage;
    private GraphicsContext gc;
    private Canvas canvas;
    public static StackPane root;
    private ButtonStartScreen buttons;

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
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        gc.drawImage(RenderableHolder.startScreen_title, 0, 0,800,250);
        gc.drawImage(RenderableHolder.startScreen_toi, 280, 200,250,354);
        gc.drawImage(RenderableHolder.startScreen_oven, 280, 350,250,354);


        root.getChildren().addAll(buttons);

    }
}