package logic;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Food {
    private String name;
    private int score;
    private ArrayList<Integer> ingredients;
    private Image image;

    public Food(String name, int score, ArrayList<Integer> ingredients, Image image){
        this.name = name;
        this.score = score;
        this.ingredients = ingredients;
        this.image = image;
    }

    public boolean isMatchIngredient(ArrayList<Integer> ingredientAmount){
        for (int i = 0; i < ingredients.size(); i++){
            if (ingredientAmount.get(i) < ingredients.get(i)){
                return false;
            }
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Integer> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Integer> ingredients) {
        this.ingredients = ingredients;
    }
}
