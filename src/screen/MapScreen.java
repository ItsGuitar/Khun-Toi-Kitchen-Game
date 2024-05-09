package screen;

import application.Main;
import gui.GUIManager;
import gui.TimerPane;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.Loot;
import logic.base.Interactable;
import sharedObject.AudioLoader;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;


public class MapScreen{
    private static Stage primaryStage;
    private Canvas backgroundCanvas;
    private Canvas lootCanvas;
    public GraphicsContext backgroundGc;
    public GraphicsContext lootGc;
    private Interactable lastHoveredEntity;
    private ButtonGameScreen buttons;
    private Scene scene;
    public static int gametime;
    private static AnchorPane leftPane;
    public MapScreen(Stage primaryStage){
        //initialize
        this.primaryStage = primaryStage;
        this.backgroundCanvas = new Canvas(500, 600);
        this.lootCanvas = new Canvas(500, 600);
        this.lastHoveredEntity = null; // no loot hovered at the start
        this.buttons = new ButtonGameScreen();
        this.backgroundGc = backgroundCanvas.getGraphicsContext2D(); // Reinitialize GraphicsContext
        this.lootGc = lootCanvas.getGraphicsContext2D();

        lootCanvas.setMouseTransparent(true);
        GameController.initGame();
        //--------------------------------------
        //root contains leftPane and rightBox
        HBox root = new HBox();
        GUIManager.init();
        GUIManager.update();
        //leftPane contains the map (main screen) and the loot
        leftPane = new AnchorPane();
        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        lootGc = lootCanvas.getGraphicsContext2D();
        backgroundGc.setFill(Color.BLACK);
        backgroundGc.drawImage(RenderableHolder.mapScreen_background,0, 0, 500, 600);
        gametime = GameController.STARTTIME;
        TimerPane timerPane = TimerPane.getInstance();
        timerPane.setMouseTransparent(true);
        leftPane.getChildren().addAll(backgroundCanvas, timerPane, lootCanvas);

        AnchorPane.setBottomAnchor(timerPane, 0.0);
        AnchorPane.setLeftAnchor(timerPane, 0.0);
        //--------------------------------------
        //rightBox contains the inventory and the button
        VBox rightBox = new VBox();

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

        this.scene = new Scene(root);

        this.primaryStage.setScene(scene);
    }

    public static void removeTime(){
        leftPane.getChildren().remove(TimerPane.getInstance());
    }
    public static void addTime(){
        leftPane.getChildren().add(TimerPane.getInstance());
    }
    //Power of polymorphism
    public void handleMouseInteraction(MouseEvent event, int actionType) {
        Interactable hoveredEntity = null;
        for (IRenderable entity : RenderableHolder.getInstance().getEntities()) {
            if (entity instanceof Interactable) {
                Interactable interactableEntity = (Interactable) entity;
                int mouseX = (int) event.getX();
                int mouseY = (int) event.getY();
                if (mouseX >= interactableEntity.getX() - interactableEntity.getSizeX() / 2 && mouseX < interactableEntity.getX() + interactableEntity.getSizeX() / 2 && mouseY >= interactableEntity.getY() - interactableEntity.getSizeY() / 2 && mouseY < interactableEntity.getY() + interactableEntity.getSizeY() / 2) {
                    hoveredEntity = interactableEntity;
                    if (actionType == 1) { // click
                        interactableEntity.handleClick(event, lootGc);
                    } else if (actionType == 2 && interactableEntity != lastHoveredEntity) { // hover
                        interactableEntity.onHover(lootGc);
                    }
                }
            }
        }
        if (lastHoveredEntity != null && lastHoveredEntity != hoveredEntity) {
            lastHoveredEntity.onUnhover(lootGc);
        }
        lastHoveredEntity = hoveredEntity;
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

    public Scene getScene(){
        return scene;
    }

    // This method is important as it will be called after the game is started, including timer, and other game logic
    public static void initializeGameAfterStart(){
        //set audio
        AudioLoader.gameMusic.setVolume(0.1);
        AudioLoader.gameMusic.setCycleCount(MediaPlayer.INDEFINITE);
        if(!AudioLoader.gameMusic.isPlaying()){
            AudioLoader.gameMusic.play();
        }
        timerUpdate();
    }

    public static void timerUpdate(){
        final long startNanoTime = System.nanoTime();
        AnimationTimer timer = new AnimationTimer(){
            public void handle(long currentNanoTime){
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
                gametime = (int)(GameController.STARTTIME - t);
                GameController.setTime(gametime);
                GUIManager.getTimerPane().update();
                //System.out.println(gametime);
                if(gametime < 0){
                    AudioLoader.gameMusic.stop();
                    Main.switchToGameOverScreen(primaryStage);
                    this.stop();
                }
            }
        };
        timer.start();
    }

}


