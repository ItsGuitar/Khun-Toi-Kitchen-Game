package screen;

import constant.ButtonStyles;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import logic.base.HoverableButton;
import sharedObject.RenderableHolder;
import application.Main;

public class ButtonGameScreen extends Button implements HoverableButton{
    public Button gotoKitchenButton;
    public Button backToMapButton;
    public ButtonGameScreen(){
        gotoKitchenButton = new Button("Go to Kitchen");
        gotoKitchenButton.setStyle(ButtonStyles.getKitchenButtonStyle());
        gotoKitchenButton.setPrefSize(300, 100);

        backToMapButton = new Button("Back");
        backToMapButton.setStyle(ButtonStyles.getKitchenButtonStyle());
        backToMapButton.setPrefSize(100, 100);
    }

    @Override
    public void setupIndividuallyButtonHover(Button b) {
        b.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                b.setStyle(ButtonStyles.getKitchenButtonHoverStyle());
            }
        });

        b.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                b.setStyle(ButtonStyles.getKitchenButtonStyle());
            }
        });
    }

    private void handleButtonRoute(Button b,int id,Stage primaryStage){
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(id == 2){
                    //new MapScreen(primaryStage);
                    Main.switchToMapScreen(primaryStage);
                } else if(id == 3){
                    //new KitchenScreen(primaryStage);
                    Main.switchToKitchenScreen(primaryStage);
                }
            }
        });
    }

    public void setupButtonKitchen(Stage primaryStage) {
        handleButtonRoute(this.gotoKitchenButton,3,primaryStage);
    }

    public void setupButtonBack(Stage primaryStage) {
        handleButtonRoute(this.backToMapButton,2,primaryStage);
    }
}