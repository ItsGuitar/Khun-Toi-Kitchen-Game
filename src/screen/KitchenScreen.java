package screen;

import gui.GUIManager;
import gui.TimerPane;
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

    public KitchenScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.backgroundCanvas = new Canvas(800, 600);
        this.buttons = new ButtonGameScreen();
        this.backgroundGc = backgroundCanvas.getGraphicsContext2D();

        root = new AnchorPane();

        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        backgroundGc.setFill(Color.BLACK);
        backgroundGc.drawImage(RenderableHolder.kitchenScreen_background,0, 0, 800, 600);

        buttons.setupIndividuallyButtonHover(buttons.backToMapButton);
        buttons.setupButtonBack(primaryStage);

        root.getChildren().addAll(backgroundCanvas,buttons.backToMapButton);


        this.scene = new Scene(root);

        this.primaryStage.setScene(scene);
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
