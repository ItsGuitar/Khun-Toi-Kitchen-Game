package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.base.Interactable;
import sharedObject.RenderableHolder;
import java.util.Timer;
import java.util.TimerTask;

public class Loot extends Component implements Interactable {
    private static final int LOOT_SIZEX = 69;
    private static final int LOOT_SIZEY = 52;
    private ImageView lootImage;
    private ImageView lootOpenedImage;
    private PixelReader pixelReader;
    private boolean isHovered;
    private boolean isInteract;
    private Timer interactTimer;

    public Loot(int x,int y){
        this.x = x;
        this.y = y;
        this.z = -100;
        this.lootImage = new ImageView(RenderableHolder.lootSprite);
        this.lootOpenedImage = new ImageView(RenderableHolder.lootOpenedSprite);
        this.pixelReader = lootImage.getImage().getPixelReader();
        this.isHovered = false;
        this.interactTimer = new Timer();
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.clearRect(x - (LOOT_SIZEX / 2) - 5 , y - (LOOT_SIZEY / 2) - 5, LOOT_SIZEX + 10, LOOT_SIZEY + 10);
        if (isInteract){
            gc.drawImage(lootOpenedImage.getImage(), x - LOOT_SIZEX / 2, y - LOOT_SIZEY / 2);
        } else if (isHovered) {
            gc.drawImage(lootImage.getImage(), x - (LOOT_SIZEX / 2) - 5 , y - (LOOT_SIZEY / 2) - 5, LOOT_SIZEX + 10, LOOT_SIZEY + 10);
        } else {
            lootImage.setFitWidth(LOOT_SIZEX);
            lootImage.setFitHeight(LOOT_SIZEY);
            gc.drawImage(lootImage.getImage(), x - LOOT_SIZEX / 2, y - LOOT_SIZEY / 2);
        }
    }

    public void interact(GraphicsContext gc){
        // Handle the interaction here
        System.out.println("Interacted with loot");
        isHovered = false;
        isInteract = true;
        draw(gc);

        interactTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                isInteract = false;
                draw(gc);
            }
        }, 2000);
    }
    public void onHover(GraphicsContext gc){
        // Handle the hover here
        System.out.println("Hovered over loot");
        isHovered = true;
        draw(gc);
    }

    public void onUnhover(GraphicsContext gc){
        // Handle the unhover here
        System.out.println("Unhovered over loot");
        isHovered = false;
        draw(gc);
    }
    public void handleClick(MouseEvent event, GraphicsContext gc){
        int clickX = (int) event.getX();
        int clickY = (int) event.getY();
        if (clickX >= x - LOOT_SIZEX / 2 && clickX < x + LOOT_SIZEX / 2 && clickY >= y - LOOT_SIZEY / 2 && clickY < y + LOOT_SIZEY / 2) {
            int imageX = (int) (clickX - (x - LOOT_SIZEX / 2));
            int imageY = (int) (clickY - (y - LOOT_SIZEY / 2));
            Color color = pixelReader.getColor(imageX, imageY);
            if (color.getOpacity() > 0) {
                interact(gc);
            }
        }
    }

    public int getX(){
        return (int)x;
    }

    public int getY(){
        return (int)y;
    }

    public static int getLootSizeX(){
        return LOOT_SIZEX;
    }

    public static int getLootSizeY(){
        return LOOT_SIZEY;
    }
}