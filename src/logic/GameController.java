package logic;

import gui.GUIManager;
import javafx.application.Platform;
import screen.KitchenScreen;
import sharedObject.IRenderable;
import sharedObject.RenderableHolder;

import java.util.*;

public class GameController {
    private static ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> lootLocation;
    public static final ArrayList<Integer> LOOT_COOLDOWN = new ArrayList<>(Arrays.asList(5,7,10,12,15));
    private static ArrayList<Integer> ingredient_amount;
    public static final String[] INGREDIENTS = {"Jackfruit", "White Perch", "Rice Noodles", "Red Chili Paste", "Mango", "Kaffir Lime Leaves", "Holy Basil", "Egg", "Ginger", "Grapefruit"};
    private static double percentageWinning;
    public static final int STARTTIME = 30;
    private static int time;
    public static boolean isClockInteracted = false;
    private static ArrayList<Food> foods;
    public static int currentScreenID;

    public static void initGame(){
        resetGame();
        foods = new ArrayList<>();
        lootLocation = new ArrayList<>();
        initLoot();
        initClock();
        initFood();
    }
    public static void resetGame(){
        setPercentageWinning(0);
        setIngredient_amount(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0)));

        for (IRenderable item : RenderableHolder.getInstance().getEntities()) {
            if (item instanceof Loot) {
                ((Loot) item).setTimerToZero();
            }
        }

    }
    public static ArrayList<Integer> getIngredient_amount() {
        return ingredient_amount;
    }
    public static int getTime(){
        return time;
    }

    public static void setTime(int time) {
        GameController.time = time;
    }

    public static double getPercentageWinning() {
        return percentageWinning;
    }

    public static void setIngredient_amount(ArrayList<Integer> ingredient_amount) {
        GameController.ingredient_amount = ingredient_amount;
    }

    public static void setPercentageWinning(double percentageWinning) {
        GameController.percentageWinning = percentageWinning;
    }

    public static void initLoot(){
        // set location
        lootLocation.add(new AbstractMap.SimpleEntry<>(176,90)); //North
        lootLocation.add(new AbstractMap.SimpleEntry<>(340,160)); //North-East
        lootLocation.add(new AbstractMap.SimpleEntry<>(277,268)); //East
        lootLocation.add(new AbstractMap.SimpleEntry<>(195,225)); //Center
        lootLocation.add(new AbstractMap.SimpleEntry<>(214,469)); //South

        for(AbstractMap.SimpleEntry<Integer,Integer> loot : lootLocation){
            RenderableHolder.getInstance().add(new Loot(loot.getKey(),loot.getValue()));
        }
    }

    public static void initClock(){
            RenderableHolder.getInstance().add(new Clock(381,427)); }
    public static ArrayList<Integer> randomizeFromSeconds(int seconds){
        HashMap<Integer, ArrayList<Integer>> cooldownMap = new HashMap<>();
        cooldownMap.put(5, new ArrayList<>(Arrays.asList(1, 0)));
        cooldownMap.put(7, new ArrayList<>(Arrays.asList(2, 5)));
        cooldownMap.put(10, new ArrayList<>(Arrays.asList(3, 7)));
        cooldownMap.put(12, new ArrayList<>(Arrays.asList(4, 10)));
        cooldownMap.put(15, new ArrayList<>(Arrays.asList(5, 15)));
        return cooldownMap.get(seconds);
    }

    public static void initFood(){
        // The spaces after Arrays.asList are for alignment purposes, to make the code more readable by aligning the lists of ingredients for each food item.
        foods.add(new Food("White Perch Salad", 0.52, new ArrayList<>(Arrays.asList       (0,1,0,1,2,0,0,0,0,0)), RenderableHolder.foodSprite.get(0)));
        foods.add(new Food("Watercress Curry", 0.49, new ArrayList<>(Arrays.asList        (1,0,0,0,0,1,1,0,0,0)), RenderableHolder.foodSprite.get(1)));
        foods.add(new Food("Khanom Jeen Nam Prik Ong", 0.53, new ArrayList<>(Arrays.asList(0,0,2,1,0,0,0,1,0,0)), RenderableHolder.foodSprite.get(2)));
        foods.add(new Food("Stir-fried Grapefruit", 0.62, new ArrayList<>(Arrays.asList   (0,0,0,1,0,0,1,0,1,1)), RenderableHolder.foodSprite.get(3)));
        foods.add(new Food("Mango with Poached Egg", 0.41, new ArrayList<>(Arrays.asList  (0,0,0,0,2,0,0,1,0,0)), RenderableHolder.foodSprite.get(4)));

    }

    public static void randomUpdateIngredient(int amount){
        ArrayList<Integer> currentIngredientAmount = getIngredient_amount();
        Random rand = new Random();
        for(int i = 0; i < amount; i++){
            int randomIndex = rand.nextInt(INGREDIENTS.length);
            currentIngredientAmount.set(randomIndex, currentIngredientAmount.get(randomIndex) + 1);
        }
        setIngredient_amount(currentIngredientAmount);
        //System.out.println(getIngredient_amount());
    }

    public static void handleRandomize(int seconds){
        ArrayList<Integer> randomize = randomizeFromSeconds(seconds);
        randomUpdateIngredient(randomize.get(0));
        Platform.runLater(() -> {
            GUIManager.getDataPane().update();
            GUIManager.getKitchenDataPane().update();
        });
    }

    public static boolean isRemovalDone(){
        ArrayList<Integer> currentIngredientAmount = getIngredient_amount();
        Random rand = new Random();

        // Check if there are any ingredients with an amount greater than 0
        List<Integer> indicesWithNonZeroAmount = new ArrayList<>();
        for (int i = 0; i < currentIngredientAmount.size(); i++) {
            if (currentIngredientAmount.get(i) > 0) {
                indicesWithNonZeroAmount.add(i);
            }
        }

        // If there are no ingredients with an amount greater than 0, return false
        if (indicesWithNonZeroAmount.isEmpty()) {
            return false;
        }

        // Randomly select one of the ingredients with an amount greater than 0
        int randomIndex = indicesWithNonZeroAmount.get(rand.nextInt(indicesWithNonZeroAmount.size()));

        // Decrease the amount of the selected ingredient by 1
        currentIngredientAmount.set(randomIndex, currentIngredientAmount.get(randomIndex) - 1);

        // Update the ingredient amounts
        setIngredient_amount(currentIngredientAmount);
        GUIManager.getDataPane().update();

        return true;
    }

    public static ArrayList<Food> getFoods() {
        return foods;
    }

    public static ArrayList<Integer> subtractIngredient(ArrayList<Integer> ingredientAmount, ArrayList<Integer> ingredients){
        for(int i = 0; i < ingredients.size(); i++){
            ingredientAmount.set(i, ingredientAmount.get(i) - ingredients.get(i));
        }
        return ingredientAmount;
    }
}
