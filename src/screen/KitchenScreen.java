package screen;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sharedObject.RenderableHolder;

public class KitchenScreen {
    private Stage primaryStage;
    private Canvas gameCanvas;
    public static GraphicsContext mapGc;
    public KitchenScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.gameCanvas = new Canvas(800, 600);

        HBox root = new HBox(4);
        root.setPadding(new Insets(4));

        VBox leftBox = new VBox(4);
        //leftBox contains the map (main screen)
        mapGc = gameCanvas.getGraphicsContext2D();
        mapGc.setFill(Color.BLACK);
        mapGc.drawImage(RenderableHolder.mapScreen_background,0, 0, 500, 600);

        leftBox.getChildren().addAll(gameCanvas);

        root.getChildren().addAll(leftBox);

        Scene scene = new Scene(root);

        this.primaryStage.setScene(scene);
    }
}
