package screen;

import gui.GUIManager;
import gui.TimerPane;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    public KitchenScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.backgroundCanvas = new Canvas(800, 600);
        this.buttons = new ButtonGameScreen();
        this.backgroundGc = backgroundCanvas.getGraphicsContext2D();

        HBox root = new HBox();

        StackPane leftPane = new StackPane();
        //leftBox contains the map (main screen)
        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        backgroundGc.setFill(Color.BLACK);
        backgroundGc.drawImage(RenderableHolder.mapScreen_background,0, 0, 500, 600);

        buttons.setupIndividuallyButtonHover(buttons.backToMapButton);
        buttons.setupButtonBack(primaryStage);

        TimerPane timerPane = TimerPane.getInstance();
        timerPane.setMouseTransparent(true);
        leftPane.getChildren().addAll(backgroundCanvas,buttons.backToMapButton,timerPane);

        root.getChildren().addAll(leftPane);

        this.scene = new Scene(root);

        this.primaryStage.setScene(scene);
    }

    public Scene getScene(){
        return scene;
    }

}
