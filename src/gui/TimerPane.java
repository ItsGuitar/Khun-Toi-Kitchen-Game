package gui;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.GameController;
import sharedObject.RenderableHolder;

public class TimerPane extends StackPane {
    private static int width = 225;
    private static int height = 55;
    private static GraphicsContext gc;
    private Canvas canvas;
    private static int time;

    // Singleton instance
    private static TimerPane instance = null;

    // Private constructor
    private TimerPane(){
        this.setWidth(width);
        this.setHeight(height);
        canvas = new Canvas(this.getWidth(),this.getHeight());
        gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);
        this.setAlignment(Pos.BOTTOM_LEFT);
    }

    // Public method to get instance
    public static TimerPane getInstance() {
        if (instance == null) {
            instance = new TimerPane();
        }
        return instance;
    }

    public void update(){
        gc.clearRect(0,0,this.getWidth(),this.getHeight());
        this.time = GameController.getTime();
        drawBackground(gc);
        gc.setFont(new Font(30));
        gc.setFill(Color.WHITE);
        gc.fillText(convertSecondTommss(time), 10, 35);
    }

    public String convertSecondTommss(int seconds){
        if (seconds > 3599) {
            return "59:59";
        }
        int minutes = seconds / 60;
        int remainingSeconds = seconds % 60;
        return String.format("%02d:%02d", minutes, remainingSeconds);
    }

    public void drawBackground(GraphicsContext gc){
        gc.drawImage(RenderableHolder.timerPane_background,0,0);
    }
}