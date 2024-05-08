package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.GameController;

import java.util.ArrayList;

public class DataPane extends Canvas {
    private static int width = 300;
    private static int height = 500;
    private static GraphicsContext gc;
    private static ArrayList<Integer> ingredientAmount;
    public DataPane(){
        this.setWidth(width);
        this.setHeight(height);
        gc = this.getGraphicsContext2D();
        ingredientAmount = new ArrayList<>(10);
    }
    public void update(){
        gc.clearRect(0,0,this.getWidth(),this.getHeight());
        for (int i = 0; i < ingredientAmount.size(); i++){
            ingredientAmount.set(i, GameController.getIngredient_amount().get(i));
        }

    }
    public void drawBackground(){

    }

}
