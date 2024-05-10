package gui;

import javafx.scene.layout.HBox;
import logic.Food;
import logic.GameController;

public class MenuPane extends HBox {
    public MenuPane(){
        this.setPrefHeight(120);
        this.setPrefWidth(600);

        for(Food food: GameController.getFoods()){
            MenuBox menuBox = new MenuBox(food,this);
            this.getChildren().add(menuBox);
        }
    }
}
