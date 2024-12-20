package logic;

import gui.GUIManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import logic.base.Interactable;
import screen.MapScreen;
import sharedObject.AudioLoader;
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
            // plus 1 second to the timer (in the form of nanoseconds)
            MediaPlayer sound = AudioLoader.getMediaPlayer("audio/MapScreen_exchange.mp3");
            sound.play();
            MapScreen.setTimerBank(MapScreen.getTimerBank()+1000000000);
            GUIManager.getTimerPane().update();
        }
        else{
            MediaPlayer sound = AudioLoader.getMediaPlayer("audio/MapScreen_error.mp3");
            sound.play();
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
