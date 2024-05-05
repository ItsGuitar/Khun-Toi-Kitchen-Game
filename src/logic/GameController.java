package logic;

import sharedObject.RenderableHolder;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;

public class GameController {
    private static ArrayList<AbstractMap.SimpleEntry<Integer,Integer>> lootLocation;
    public static final ArrayList<Integer> LOOT_COOLDOWN = new ArrayList<>(Arrays.asList(7,10,12,15,20));
    private static ArrayList<Integer> ingredient_amount;
    public static final String[] INGREDIENTS = {"Jackfruit", "White Perch", "Rice Noodles", "Red Chili Paste", "Mango", "Kaffir Lime Leaves", "Holy Basil", "Egg", "Ginger", "Grapefruit"};
    private static int percentageWinning;

    public static void InitGame(){
        setPercentageWinning(0);
        setIngredient_amount(new ArrayList<>(Arrays.asList(0,0,0,0,0,0,0,0,0,0)));
        lootLocation = new ArrayList<>();
        InitLoot();
    }

    public static ArrayList<Integer> getIngredient_amount() {
        return ingredient_amount;
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

    public static void InitLoot(){
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
}
