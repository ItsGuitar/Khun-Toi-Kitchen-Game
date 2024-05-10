package gui;

import javafx.scene.layout.HBox;
import logic.Food;
import logic.GameController;
import screen.KitchenScreen;

public class MenuPane extends HBox {
    private KitchenScreen kitchenScreen;

    public MenuPane(KitchenScreen kitchenScreen){
        this.kitchenScreen = kitchenScreen;
        this.setPrefHeight(120);
        this.setPrefWidth(600);

        for(Food food: GameController.getFoods()){
            MenuBox menuBox = new MenuBox(food, kitchenScreen);
            this.getChildren().add(menuBox);
        }
    }
}