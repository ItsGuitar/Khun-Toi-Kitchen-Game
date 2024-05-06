package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import sharedObject.RenderableHolder;


public class Loot extends Component{
    private static final int LOOT_SIZEX = 69;
    private static final int LOOT_SIZEY = 52;
    private Image lootImage;
    private PixelReader pixelReader;

    public Loot(int x,int y){
        this.x = x;
        this.y = y;
        this.z = -100;
        this.lootImage = RenderableHolder.lootSprite;
        this.pixelReader = lootImage.getPixelReader();
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.drawImage(RenderableHolder.lootSprite, x - LOOT_SIZEX / 2, y - LOOT_SIZEY / 2);
    }

    public void handleClick(MouseEvent event){
        int clickX = (int) event.getX();
        int clickY = (int) event.getY();
        if (clickX >= x - LOOT_SIZEX / 2 && clickX < x + LOOT_SIZEX / 2 && clickY >= y - LOOT_SIZEY / 2 && clickY < y + LOOT_SIZEY / 2) {
            int imageX = (int) (clickX - (x - LOOT_SIZEX / 2));
            int imageY = (int) (clickY - (y - LOOT_SIZEY / 2));
            Color color = pixelReader.getColor(imageX, imageY);
            if (color.getOpacity() > 0) {
                // Handle the click event here
                System.out.println("Clicked on a non-transparent pixel");
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
