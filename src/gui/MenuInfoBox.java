package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.Food;
import logic.GameController;

import java.util.ArrayList;

public class MenuInfoBox extends Canvas {
    private GraphicsContext gc;
    private Food food;
    public MenuInfoBox(Food food){
        super(200, 120);
        this.food = food;
        gc = this.getGraphicsContext2D();
        gc.setFill(javafx.scene.paint.Color.web("#f5ecc4f2"));
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        draw(food);

        this.setMouseTransparent(true);
    }
    private void draw(Food food){
        gc.setFont(javafx.scene.text.Font.font("Verdana", javafx.scene.text.FontWeight.BOLD, 10.5)); // Set the font size to 12 and make it bold
        gc.setFill(javafx.scene.paint.Color.BLACK); // Set the fill color to black
        gc.fillText(food.getName(), 10, 30);
        gc.setFont(javafx.scene.text.Font.font("Verdana", 10.5));
        gc.fillText("+ " + food.getScore() + " score", 10, 45);
        gc.fillText("Ingredients needed :", 10, 60);
        gc.fillText(ingredientToString(food.getIngredients()), 10, 75);
    }
    public String ingredientToString(ArrayList<Integer> ingredients){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ingredients.size(); i++) {
            int amount = ingredients.get(i);
            if (amount > 0) {
                String ingredientName = GameController.INGREDIENTS[i];
                sb.append(ingredientName).append(" x").append(amount).append("\n");
            }
        }
        return sb.toString();
    }

}
