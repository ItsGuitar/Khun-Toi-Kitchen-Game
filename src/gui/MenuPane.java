package gui;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import logic.Food;
import logic.GameController;

public class MenuPane extends HBox {
    public MenuPane(){
        this.setPrefHeight(120);
        this.setPrefWidth(600);

        for(Food food: GameController.getFoods()){
            MenuBox menuBox = new MenuBox(food);
            this.getChildren().add(menuBox);
        }
    }
}
