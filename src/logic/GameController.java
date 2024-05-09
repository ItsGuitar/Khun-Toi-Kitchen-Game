package logic;

import gui.GUIManager;
import sharedObject.RenderableHolder;

import java.util.*;

public class GameController {
    private static ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> lootLocation;
    public static final ArrayList<Integer> LOOT_COOLDOWN = new ArrayList<>(Arrays.asList(5,7,10,12,15));
    private static ArrayList<Integer> ingredient_amount;
    public static final String[] INGREDIENTS = {"Jackfruit", "White Perch", "Rice Noodles", "Red Chili Paste", "Mango", "Kaffir Lime Leaves", "Holy Basil", "Egg", "Ginger", "Grapefruit"};
    private static int percentageWinning;
    public static final int STARTTIME = 30;
    private static int time;
    public static boolean isClockInteracted = false;

    public static void initGame(){
        setPercentageWinning(0);
        setIngredient_amount(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0)));
        lootLocation = new ArrayList<>();
        initLoot();
        initClock();
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

    public static int getPercentageWinning() {
        return percentageWinning;
    }

    public static void setIngredient_amount(ArrayList<Integer> ingredient_amount) {
        GameController.ingredient_amount = ingredient_amount;
    }

    public static void setPercentageWinning(int percentageWinning) {
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
        cooldownMap.put(7, new ArrayList<>(Arrays.asList(1, 5)));
        cooldownMap.put(10, new ArrayList<>(Arrays.asList(2, 7)));
        cooldownMap.put(12, new ArrayList<>(Arrays.asList(2, 10)));
        cooldownMap.put(15, new ArrayList<>(Arrays.asList(3, 15)));
        return cooldownMap.get(seconds);
    }

    public static void randomUpdateIngredient(int amount){
        ArrayList<Integer> currentIngredientAmount = getIngredient_amount();
        Random rand = new Random();
        for(int i = 0; i < amount; i++){
            int randomIndex = rand.nextInt(INGREDIENTS.length);
            currentIngredientAmount.set(randomIndex, currentIngredientAmount.get(randomIndex) + 1);
        }
        setIngredient_amount(currentIngredientAmount);
        System.out.println(getIngredient_amount());
    }

    public static void handleRandomize(int seconds){
        ArrayList<Integer> randomize = randomizeFromSeconds(seconds);
        randomUpdateIngredient(randomize.get(0));
        GUIManager.getDataPane().update();
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

}
