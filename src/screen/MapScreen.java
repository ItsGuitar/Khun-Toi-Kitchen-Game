package screen;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import logic.GameController;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class MapScreen {
    private Stage primaryStage;
    private Canvas gameCanvas;
    public static GraphicsContext mapGc;
    public MapScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.gameCanvas = new Canvas(800, 600);
        GameController.InitGame();
        HBox root = new HBox(4);
        root.setPadding(new Insets(4));

        //leftBox contains the map (main screen)
        VBox leftBox = new VBox(4);
        mapGc = gameCanvas.getGraphicsContext2D();
        mapGc.setFill(Color.BLACK);
        mapGc.drawImage(RenderableHolder.mapScreen_background,0, 0, 500, 600);
        leftBox.getChildren().addAll(gameCanvas);

        //rightBox contains the inventory and the button
        VBox rightBox = new VBox(4);
        rightBox.setPadding(new Insets(4));

        root.getChildren().addAll(leftBox);

        // draw
        for(IRenderable entity : RenderableHolder.getInstance().getEntities()){
            if(entity.isVisible()){
                entity.draw(mapGc);
            }
        }
        Scene scene = new Scene(root);

        this.primaryStage.setScene(scene);
    }
}
