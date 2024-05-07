package logic;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import logic.base.Interactable;
import sharedObject.RenderableHolder;

public class Loot extends Component implements Interactable {
    private static final int LOOT_SIZEX = 69;
    private static final int LOOT_SIZEY = 52;
    private ImageView lootImage;
    private PixelReader pixelReader;
    private boolean isHovered;

    public Loot(int x,int y){
        this.x = x;
        this.y = y;
        this.z = -100;
        this.lootImage = new ImageView(RenderableHolder.lootSprite);
        this.pixelReader = lootImage.getImage().getPixelReader();
        this.isHovered = false;
    }

    @Override
    public void draw(GraphicsContext gc){
        if (isHovered) {
            this.lootImage.setFitWidth(LOOT_SIZEX * 1.1);
            lootImage.setFitHeight(LOOT_SIZEY * 1.1);
            gc.drawImage(lootImage.getImage(), x - LOOT_SIZEX / 2 - 30, y - LOOT_SIZEY / 2);
        } else {
            lootImage.setFitWidth(LOOT_SIZEX);
            lootImage.setFitHeight(LOOT_SIZEY);
            gc.drawImage(lootImage.getImage(), x - LOOT_SIZEX / 2, y - LOOT_SIZEY / 2);
        }

    }

    public void interact(){
        // Handle the interaction here
        System.out.println("Interacted with loot");
        isHovered = false;
    }
    public void onHover(){
        // Handle the hover here
        System.out.println("Hovered over loot");
        isHovered = true;
    }

    public void onUnhover(){
        // Handle the unhover here
        System.out.println("Unhovered over loot");
        isHovered = false;
    }
    public void handleClick(MouseEvent event){
        int clickX = (int) event.getX();
        int clickY = (int) event.getY();
        if (clickX >= x - LOOT_SIZEX / 2 && clickX < x + LOOT_SIZEX / 2 && clickY >= y - LOOT_SIZEY / 2 && clickY < y + LOOT_SIZEY / 2) {
            int imageX = (int) (clickX - (x - LOOT_SIZEX / 2));
            int imageY = (int) (clickY - (y - LOOT_SIZEY / 2));
            Color color = pixelReader.getColor(imageX, imageY);
            if (color.getOpacity() > 0) {
                interact();
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