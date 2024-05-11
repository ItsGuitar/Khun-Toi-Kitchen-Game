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
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameController;
import sharedObject.RenderableHolder;

public class GameOverScreen {
    private Stage primaryStage;
    public GraphicsContext gc;
    private Canvas canvas;
    private static StackPane root;
    private Scene scene;
    private ButtonGameScreen button;
    public GameOverScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.canvas = new Canvas(800, 600);
        this.gc = canvas.getGraphicsContext2D();
        this.button = new ButtonGameScreen();
        draw(gc);
    }

    public void draw(GraphicsContext gc){
        gc.drawImage(RenderableHolder.gameOverScreen_background, 0, 0,800,600);
        root = new StackPane();
        root.setPrefSize(800,600);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

        // Create a Rectangle for the box
        Rectangle box = new Rectangle(50, 250, 200, 80);
        box.setFill(Color.BLACK); // Set the fill color to black
        box.setStroke(Color.WHITE); // Set the border color to white
        box.setStrokeWidth(2.0); // Set the border width
        box.setArcWidth(20.0); // Set the corner radius (horizontal)
        box.setArcHeight(20.0); // Set the corner radius (vertical)

        // Create the text to be placed on top of the rectangle
        Text text_up = new Text(String.format("Your score is %.2f", GameController.getPercentageWinning() / 10.0));
        Text text_down = new Text(String.format("(เอาไป %.2f คะแนนไม่เต็ม)", GameController.getPercentageWinning() / 10.0));
        text_up.setFont(Font.font("Arial Unicode MS", FontWeight.BOLD, 20));
        text_up.setFill(Color.WHITE);
        text_down.setFont(Font.font("Arial Unicode MS", 15));
        text_down.setFill(Color.WHITE);

        box.setTranslateX(-250);
        box.setTranslateY(-10);
        text_up.setTranslateX(-250);
        text_up.setTranslateY(-25);
        text_down.setTranslateX(-250);

        button.setupIndividuallyButtonHover(button.backToStartScreen);
        button.setupButtonBackToStartScreen(primaryStage);
        button.backToStartScreen.setTranslateX(250);
        // Add the canvas, box and the text to the root StackPane
        root.getChildren().addAll(canvas, button.backToStartScreen,box, text_up, text_down);

        scene = new Scene(root);
        //primaryStage.setScene(scene);
    }

    public Scene getScene(){
        return scene;
    }
}
