package screen;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Loot;
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
        InitMouseClick();
        InitMouseHover();
        Scene scene = new Scene(root);

        this.primaryStage.setScene(scene);
    }

    public void handleMouseInteraction(MouseEvent event, int actionType) {
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if (entity instanceof Loot) {
                Loot loot = (Loot) entity;
                int mouseX = (int) event.getX();
                int mouseY = (int) event.getY();
                if (mouseX >= loot.getX() - Loot.getLootSizeX() / 2 && mouseX < loot.getX() + Loot.getLootSizeX() / 2 && mouseY >= loot.getY() - Loot.getLootSizeY() / 2 && mouseY < loot.getY() + Loot.getLootSizeY() / 2) {
                    if (actionType == 1) { // click
                        loot.handleClick(event);
                    } else if (actionType == 2) { // hover
                        loot.onHover();
                    } else if (actionType == 3) { // unhover
                        loot.onUnhover();
                    }
                    entity.draw(mapGc);
                } else if (actionType != 1) { // unhover for mouse outside the loot
                    loot.onUnhover();
                    entity.draw(mapGc);
                }
            }
        }
    }

    public void InitMouseClick(){
        gameCanvas.setOnMouseClicked(event -> {
            handleMouseInteraction(event, 1); // 1 for click
        });
    }

    public void InitMouseHover(){
        gameCanvas.setOnMouseMoved(event -> {
            handleMouseInteraction(event, 2); // 2 for hover
        });
    }

    public void InitMouseUnhover(){
        gameCanvas.setOnMouseExited(event -> {
            handleMouseInteraction(event, 3); // 3 for unhover
        });
    }
}
