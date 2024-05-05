package logic;

import javafx.scene.canvas.GraphicsContext;
import sharedObject.RenderableHolder;

public class Loot extends Component{
    private static final int LOOT_SIZEX = 69;
    private static final int LOOT_SIZEY = 52;

    public Loot(int x,int y){
        this.x = x;
        this.y = y;
        this.z = -100;
    }

    @Override
    public void draw(GraphicsContext gc){
        gc.drawImage(RenderableHolder.lootSprite, x-LOOT_SIZEX/2, y-LOOT_SIZEY/2);
    }

}
