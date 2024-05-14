package screen;

import gui.GUIManager;
import gui.TimerPane;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import logic.GameController;
import logic.SwitchPage;
import logic.base.Interactable;
import sharedObject.AudioLoader;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;


public class MapScreen{
    private static Stage primaryStage;
    private Canvas backgroundCanvas;
    private Canvas lootCanvas;
    public GraphicsContext backgroundGc;
    public GraphicsContext entityGc;
    private Interactable lastHoveredEntity;
    private ButtonGameScreen buttons;
    private Scene scene;
    public static int gametime;
    private static AnchorPane leftPane;
    private static long timerBank;
    private static AnimationTimer timer;
    private static MediaPlayer sound;
    public MapScreen(Stage primaryStage){
        // Initialize the primary stage, canvases, button, and GraphicsContexts
        this.primaryStage = primaryStage;
        this.backgroundCanvas = new Canvas(500, 600);
        this.lootCanvas = new Canvas(500, 600);
        this.lastHoveredEntity = null; // no loot hovered at the start
        this.buttons = new ButtonGameScreen();
        this.backgroundGc = backgroundCanvas.getGraphicsContext2D(); // Reinitialize GraphicsContext
        this.entityGc = lootCanvas.getGraphicsContext2D();

        // Make the loot canvas transparent to mouse events
        lootCanvas.setMouseTransparent(true);

        // Initialize the game
        GameController.initGame();

        // Initialize the root HBox, GUIManager, and left pane
        HBox root = new HBox();
        GUIManager.init();
        GUIManager.update();
        leftPane = new AnchorPane();

        // Initialize the background GraphicsContext and draw the background image
        backgroundGc = backgroundCanvas.getGraphicsContext2D();
        backgroundGc.setFill(Color.BLACK);
        backgroundGc.drawImage(RenderableHolder.mapScreen_background,0, 0, 500, 600);

        // Initialize the game time and timer pane
        gametime = GameController.getStartTime();
        TimerPane timerPane = TimerPane.getInstance();
        timerPane.setMouseTransparent(true);

        // Initialize the dance image and add it to the left pane
        Image danceImage = RenderableHolder.mapScreen_dance;
        ImageView danceImageView = new ImageView(danceImage);
        danceImageView.setX(380);
        leftPane.getChildren().addAll(backgroundCanvas, danceImageView, timerPane, lootCanvas);

        // Set the anchors for the timer pane
        AnchorPane.setBottomAnchor(timerPane, 0.0);
        AnchorPane.setLeftAnchor(timerPane, 0.0);

        // Initialize the right VBox and add the inventory and button to it
        VBox rightBox = new VBox();
        buttons.setupIndividuallyButtonHover(buttons.gotoKitchenButton);
        buttons.setupButtonKitchen(primaryStage);
        rightBox.getChildren().addAll(GUIManager.getDataPane(), buttons.gotoKitchenButton);

        // Add the left pane and right VBox to the root HBox
        root.getChildren().addAll(leftPane,rightBox);

        // Draw all entities
        for(IRenderable entity : RenderableHolder.getInstance().getEntities()){
            if(entity.isVisible() && isEntityVisibleOnScreen(entity)){
                entity.draw(entityGc);
            }
        }

        // Initialize click and hover events
        initMouseClick();
        initMouseHover();

        // Initialize the scene for the map screen
        this.scene = new Scene(root);

        // Set the scene for the primary stage
        this.primaryStage.setScene(scene);
    }

    public static void removeTime(){
        leftPane.getChildren().remove(TimerPane.getInstance());
    }
    public static void addTime(){
        if (!leftPane.getChildren().contains(TimerPane.getInstance())) {
            leftPane.getChildren().add(TimerPane.getInstance());
        }
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
                        interactableEntity.handleClick(event, entityGc);
                    } else if (actionType == 2 && interactableEntity != lastHoveredEntity) { // hover
                        interactableEntity.onHover(entityGc);
                    }
                }
            }
        }
        if (lastHoveredEntity != null && lastHoveredEntity != hoveredEntity) {
            lastHoveredEntity.onUnhover(entityGc);
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
        sound = AudioLoader.getMediaPlayer("audio/GameMusic.mp3");
        sound.setVolume(0.1);
        sound.setCycleCount(MediaPlayer.INDEFINITE);
        if(sound.getStatus() != MediaPlayer.Status.PLAYING){
            sound.play();
        }
        timerBank = System.nanoTime();
        timerUpdate();
        GUIManager.getDataPane().update();
    }

    public static void timerUpdate(){
            timer = new AnimationTimer(){
            public void handle(long currentNanoTime){
                Platform.runLater(() -> {
                    if (!GameController.isClockInteracted) {
                        double elapsedTimeInSeconds = (currentNanoTime - timerBank) / 1000000000.0;
                        gametime = (int)(GameController.getStartTime() - elapsedTimeInSeconds);
                        GameController.setTime(gametime);
                        GUIManager.getTimerPane().update();
                        //System.out.println(gametime);
                        if(gametime < 0){

                            //AudioLoader.gameMusic.stop();
                            sound.stop();
                            this.stop();
                            SwitchPage.switchToGameOverScreen(primaryStage);
                        }
                    }
                });
            }
        };
        timer.start();
    }
    public void stopTimer(){
        timer.stop();
    }


    public static long getTimerBank() {
        return timerBank;
    }

    public static void setTimerBank(long timerBank) {
        MapScreen.timerBank = timerBank;
    }

    private boolean isEntityVisibleOnScreen(IRenderable entity) {
        // Replace these with the actual width and height of your screen
        int screenWidth = 800;
        int screenHeight = 600;

        int entityX = entity.getX();
        int entityY = entity.getY();
        int entityWidth = entity.getSizeX();
        int entityHeight = entity.getSizeY();

        return entityX + entityWidth >= 0 && entityX <= screenWidth &&
                entityY + entityHeight >= 0 && entityY <= screenHeight;
    }
}


