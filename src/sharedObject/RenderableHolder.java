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
    public static Image dataPane_background;
    public static ArrayList<Image> ingredientSprite = new ArrayList<>();
    public static Image timerPane_background;
    public static Image gameOverScreen_background;
    public static Image kitchenScreen_background;
    public static Image kitchenScreen_table;
    public static Image kitchenScreen_toi;
    public static Image mapScreen_clock;
    public static Image mapScreen_dance;
    public static Image kitchenDataPane_background;
    public static Image winScreen_background;
    public static Image winScreen_title;
    public static Image winScreen_toi;
    public static Image logo;
    public static ArrayList<Image> foodSprite = new ArrayList<>();
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
        dataPane_background = new Image(ClassLoader.getSystemResource(im+"DataPane_background.png").toString());
        timerPane_background = new Image(ClassLoader.getSystemResource(im+"TimerPane_background.png").toString());
        gameOverScreen_background = new Image(ClassLoader.getSystemResource(im+"GameOverScreen_background.png").toString());
        kitchenScreen_background = new Image(ClassLoader.getSystemResource(im+"KitchenScreen_background.png").toString());
        kitchenScreen_table = new Image(ClassLoader.getSystemResource(im+"KitchenScreen_table.png").toString());
        kitchenScreen_toi = new Image(ClassLoader.getSystemResource(im+"KitchenScreen_toi.png").toString());
        mapScreen_clock = new Image(ClassLoader.getSystemResource(im+"MapScreen_exchange.png").toString());
        mapScreen_dance = new Image(ClassLoader.getSystemResource(im+"MapScreen_dance.gif").toString());
        kitchenDataPane_background = new Image(ClassLoader.getSystemResource(im+"KitchenDataPane_background.png").toString());
        winScreen_background = new Image(ClassLoader.getSystemResource(im+"WinScreen_background.png").toString());
        winScreen_title = new Image(ClassLoader.getSystemResource(im+"WinScreen_title.png").toString());
        winScreen_toi = new Image(ClassLoader.getSystemResource(im+"WinScreen_toi.png").toString());
        logo = new Image(ClassLoader.getSystemResource(im+"Logo.png").toString());

        for(int i = 0; i < 10; i++){
            ingredientSprite.add(new Image(ClassLoader.getSystemResource(im+"IngredientSprite_"+i+".png").toString()));
        }
        for(int i = 0; i < 5; i++){
            foodSprite.add(new Image(ClassLoader.getSystemResource(im+"FoodSprite_"+i+".png").toString()));
        }
    }

    public static RenderableHolder getInstance() {
        return instance;
    }

    public List<IRenderable> getEntities() {
        return entities;
    }

}
