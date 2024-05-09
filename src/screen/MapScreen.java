package screen;

import gui.GUIManager;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Loot;
import sharedObject.AudioLoader;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

public class MapScreen{
    private Stage primaryStage;
    private Canvas backgroundCanvas;
    private Canvas lootCanvas;
    public GraphicsContext backgroundGc;
    public GraphicsContext lootGc;
    private Loot lastHoveredLoot;
    private ButtonGameScreen buttons;
    public MapScreen(Stage primaryStage){
        //initialize
        this.primaryStage = primaryStage;
        this.backgroundCanvas = new Canvas(500, 600);
        this.lootCanvas = new Canvas(500, 600);
        this.lastHoveredLoot = null; // no loot hovered at the start
        this.buttons = new ButtonGameScreen();
        this.backgroundGc = backgroundCanvas.getGraphicsContext2D(); // Reinitialize GraphicsContext
        this.lootGc = lootCanvas.getGraphicsContext2D();

        lootCanvas.setMouseTransparent(true);
        GameController.initGame();
        //--------------------------------------
        //root contains leftPane and rightBox
        HBox root = new HBox();
        GUIManager.init();

        //leftPane contains the map (main screen)
        StackPane leftPane = new StackPane();
        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        lootGc = lootCanvas.getGraphicsContext2D();
        backgroundGc.setFill(Color.BLACK);
        backgroundGc.drawImage(RenderableHolder.mapScreen_background,0, 0, 500, 600);
        leftPane.getChildren().addAll(backgroundCanvas,lootCanvas);
        //--------------------------------------
        //rightBox contains the inventory and the button
        VBox rightBox = new VBox();
        GUIManager.update();
        buttons.setupIndividuallyButtonHover(buttons.gotoKitchenButton);
        buttons.setupButtonKitchen(primaryStage);
        rightBox.getChildren().addAll(GUIManager.getDataPane(), buttons.gotoKitchenButton);
        //--------------------------------------
        //add leftPane and rightBox to root
        root.getChildren().addAll(leftPane,rightBox);
        //--------------------------------------
        // draw all entities
        for(IRenderable entity : RenderableHolder.getInstance().getEntities()){
            if(entity.isVisible()){
                entity.draw(lootGc);
            }
        }
        //--------------------------------------
        //initialize click and hover event
        initMouseClick();
        initMouseHover();
        //--------------------------------------
        //set audio
        AudioLoader.gameMusic.setVolume(0.1);
        AudioLoader.gameMusic.setCycleCount(MediaPlayer.INDEFINITE);
        if(!AudioLoader.gameMusic.isPlaying()){
            AudioLoader.gameMusic.play();
        }
        //--------------------------------------
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


