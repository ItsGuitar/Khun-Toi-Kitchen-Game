package screen;

import gui.GUIManager;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Loot;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class MapScreen {
    private Stage primaryStage;
    private Canvas backgroundCanvas;
    private Canvas lootCanvas;
    public static GraphicsContext backgroundGc;
    public static GraphicsContext lootGc;
    private Loot lastHoveredLoot;
    public MapScreen(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.backgroundCanvas = new Canvas(500, 600);
        this.lootCanvas = new Canvas(500, 600);
        this.lastHoveredLoot = null; // no loot hovered at the start
        lootCanvas.setMouseTransparent(true);
        GameController.initGame();
        HBox root = new HBox();
        //root.setPadding(new Insets(4));
        GUIManager.init();

        //leftPane contains the map (main screen)
        StackPane leftPane = new StackPane();
        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        lootGc = lootCanvas.getGraphicsContext2D();
        backgroundGc.setFill(Color.BLACK);
        backgroundGc.drawImage(RenderableHolder.mapScreen_background,0, 0, 500, 600);
        leftPane.getChildren().addAll(backgroundCanvas,lootCanvas);

        //rightBox contains the inventory and the button
        VBox rightBox = new VBox();
        //rightBox.setPadding(new Insets(4));
        GUIManager.update();
        rightBox.getChildren().addAll(GUIManager.getDataPane());

        root.getChildren().addAll(leftPane,rightBox);

        // draw
        for(IRenderable entity : RenderableHolder.getInstance().getEntities()){
            if(entity.isVisible()){
                entity.draw(lootGc);
            }
        }
        initMouseClick();
        initMouseHover();
        Scene scene = new Scene(root);

        this.primaryStage.setScene(scene);
    }

    public void handleMouseInteraction(MouseEvent event, int actionType) {
        Loot hoveredLoot = null;
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if (entity instanceof Loot) {
                Loot loot = (Loot) entity;
                int mouseX = (int) event.getX();
                int mouseY = (int) event.getY();
                if (mouseX >= loot.getX() - Loot.getLootSizeX() / 2 && mouseX < loot.getX() + Loot.getLootSizeX() / 2 && mouseY >= loot.getY() - Loot.getLootSizeY() / 2 && mouseY < loot.getY() + Loot.getLootSizeY() / 2) {
                    hoveredLoot = loot;
                    if (actionType == 1) { // click
                        loot.handleClick(event, lootGc);
                    } else if (actionType == 2 && loot != lastHoveredLoot) { // hover
                        loot.onHover(lootGc);
                    }
                }
            }
        }
        if (lastHoveredLoot != null && lastHoveredLoot != hoveredLoot) {
            lastHoveredLoot.onUnhover(lootGc);
        }
        lastHoveredLoot = hoveredLoot;
    }

    public void initMouseClick(){
        backgroundCanvas.setOnMouseClicked(event -> {
            handleMouseInteraction(event, 1); // 1 for click
        });
    }

    public void initMouseHover(){
        backgroundCanvas.setOnMouseMoved(event -> {
            handleMouseInteraction(event, 2); // 2 for hover
        });
    }

}


