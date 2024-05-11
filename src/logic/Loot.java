package logic;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.base.Interactable;
import sharedObject.RenderableHolder;
import sharedObject.AudioLoader;
import java.util.Random;
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
    private final int[] secondsLeft = new int[1];

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
        if(isInteract) return;
        // Handle the interaction here
        //System.out.println("Interacted with loot");
        isHovered = false;
        isInteract = true;
        draw(gc);

        AudioLoader.mapScreen_lootOpen.play();
        startCountdown(gc);
    }

    private void startCountdown(GraphicsContext gc){
        Random rand = new Random();
        int randomIndex = rand.nextInt(GameController.LOOT_COOLDOWN.size());
        secondsLeft[0] = GameController.LOOT_COOLDOWN.get(randomIndex);
        GameController.handleRandomize(secondsLeft[0]);
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(() -> {
                    gc.clearRect(x - 40, y - LOOT_SIZEY / 2 - 30, 60, 20);
                    if(secondsLeft[0] > 0){
                        gc.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                        gc.setFill(Color.WHITE);
                        String text = String.valueOf(secondsLeft[0]);
                        double textWidth = new Text(text).getLayoutBounds().getWidth();
                        gc.fillText(text, x - textWidth / 2, y - LOOT_SIZEY / 2 - 10);
                        secondsLeft[0]--;
                    } else {
                        isInteract = false;
                        draw(gc);
                        cancel();
                        if(GameController.currentScreenID == 2 || GameController.currentScreenID == 3) {
                            AudioLoader.mapScreen_lootClose.play();
                        }
                    }
                });
            }
        };
        interactTimer.schedule(task, 0, 1000);
    }
    public void setTimerToZero(){
        secondsLeft[0] = 0;
    }
    public void onHover(GraphicsContext gc){
        // Handle the hover here
        //System.out.println("Hovered over loot");
        isHovered = true;
        draw(gc);
    }

    public void onUnhover(GraphicsContext gc){
        // Handle the unhover here
        //System.out.println("Unhovered over loot");
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

    public int getSizeX(){
        return LOOT_SIZEX;
    }

    public int getSizeY(){
        return LOOT_SIZEY;
    }
}