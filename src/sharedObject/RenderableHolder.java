package sharedObject;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
    private List<IRenderable> entities;
    private Comparator<IRenderable> comparator;
    private static final RenderableHolder instance = new RenderableHolder();

    public static Image startScreen_background;
    public static Image startScreen_title;
    public static Image startScreen_toi;
    public static Image startScreen_oven;
    public static Image mapScreen_background;
    public static Image lootSprite;
    public static Image lootOpenedSprite;
    static{
        loadResource();
    }
    public RenderableHolder(){
        entities = new ArrayList<IRenderable>();
        comparator = (IRenderable o1, IRenderable o2) -> {
            if(o1.getZ() > o2.getZ()) return 1;
            return -1;
        };
    }

    public void add(IRenderable entity) {
        entities.add(entity);
        Collections.sort(entities, comparator);
    }

    public static void loadResource() {
        String im = "image/";
        startScreen_background = new Image(ClassLoader.getSystemResource(im+"StartScreen_background.png").toString());
        startScreen_title = new Image(ClassLoader.getSystemResource(im+"StartScreen_title.png").toString());
        startScreen_toi = new Image(ClassLoader.getSystemResource(im+"StartScreen_toi.png").toString());
        startScreen_oven = new Image(ClassLoader.getSystemResource(im+"StartScreen_oven.png").toString());
        mapScreen_background = new Image(ClassLoader.getSystemResource(im+"MapScreen_background.png").toString());
        lootSprite = new Image(ClassLoader.getSystemResource(im+"MapScreen_loot.png").toString());
        lootOpenedSprite = new Image(ClassLoader.getSystemResource(im+"MapScreen_lootOpened.png").toString());
    }

    public static RenderableHolder getInstance() {
        return instance;
    }

    public List<IRenderable> getEntities() {
        return entities;
    }
}
