package screen;

import gui.*;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.GameController;
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

        Text cookText = new Text("You cook for Khun Toi, or Khun Toi cooks you!");
        cookText.setFont(Font.font("null", FontWeight.BOLD, 20)); // Set the font to bold and size to 20
        cookText.setFill(Color.WHITE); // Set the fill color to white
        cookText.setStroke(Color.BLACK); // Set the stroke color to black
        cookText.setStrokeWidth(1); // Set the stroke width to 1
        cookText.setTranslateY(40);
        cookText.setTranslateX(200);

        MenuPane menuPane = new MenuPane();
        menuPane.setTranslateX(150);
        menuPane.setTranslateY(55);

        root.getChildren().addAll(backgroundCanvas,buttons.backToMapButton,cookText,menuPane,GUIManager.getKitchenDataPane());


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
