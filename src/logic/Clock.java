package logic;

import gui.GUIManager;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import logic.base.Interactable;
import sharedObject.RenderableHolder;

public class Clock extends Component implements Interactable{

    private static final int CLOCK_SIZEX = 128;
    private static final int CLOCK_SIZEY = 128;
    private ImageView clockImage;
    private PixelReader pixelReader;
    private boolean isHovered;

    public Clock(int x,int y){
        this.x = x;
        this.y = y;
        this.z = -99;
        this.clockImage = new ImageView(RenderableHolder.mapScreen_clock);
        this.pixelReader = clockImage.getImage().getPixelReader();
        this.isHovered = false;
    }
    @Override
    public void interact(GraphicsContext gc) {
        boolean isRemoved = GameController.isRemovalDone();
        if(isRemoved){
            GameController.isClockInteracted = true;
            Platform.runLater(() -> {
                GameController.setTime(GameController.getTime() + 2);
                GUIManager.getTimerPane().update();
                GameController.isClockInteracted = false;
            });
        }
    }

    @Override
    public void onHover(GraphicsContext gc) {
        isHovered = true;
        draw(gc);

    }

    @Override
    public void onUnhover(GraphicsContext gc) {
        isHovered = false;
        draw(gc);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.clearRect(x - (CLOCK_SIZEX / 2) - 5 , y - (CLOCK_SIZEY / 2) - 5, CLOCK_SIZEX + 10, CLOCK_SIZEY + 10);
        if(isHovered){
            gc.drawImage(clockImage.getImage(), x - (CLOCK_SIZEX / 2) - 5 , y - (CLOCK_SIZEY / 2) - 5, CLOCK_SIZEX + 10, CLOCK_SIZEY + 10);
        } else {
            clockImage.setFitWidth(CLOCK_SIZEX);
            clockImage.setFitHeight(CLOCK_SIZEY);
            gc.drawImage(clockImage.getImage(), x - CLOCK_SIZEX / 2, y - CLOCK_SIZEY / 2);
        }
    }

    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
    }

    public int getSizeX(){
        return CLOCK_SIZEX;
    }
    public int getSizeY(){
        return CLOCK_SIZEY;
    }

    @Override
    public void handleClick(MouseEvent event, GraphicsContext gc) {
        int clickX = (int) event.getX();
        int clickY = (int) event.getY();
        if (clickX >= x - CLOCK_SIZEX / 2 && clickX < x + CLOCK_SIZEX / 2 && clickY >= y - CLOCK_SIZEY / 2 && clickY < y + CLOCK_SIZEY / 2) {
            int imageX = (int) (clickX - (x - CLOCK_SIZEX / 2));
            int imageY = (int) (clickY - (y - CLOCK_SIZEY / 2));
            Color color = pixelReader.getColor(imageX, imageY);
            if (color.getOpacity() > 0) {
                interact(gc);
            }
        }

    }
}
