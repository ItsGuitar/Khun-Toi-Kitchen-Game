package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import logic.Food;

public class MenuInfoBox extends Canvas {
    private GraphicsContext gc;
    private Food food;
    public MenuInfoBox(Food food){
        super(200, 200);
        this.food = food;
        gc = this.getGraphicsContext2D();
        gc.setFill(javafx.scene.paint.Color.web("#f5ecc4"));
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        draw(food);
    }
    private void draw(Food food){

    }
}
