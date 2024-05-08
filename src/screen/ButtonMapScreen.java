package screen;

import constant.ButtonStyles;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import logic.base.HoverableButton;

public class ButtonMapScreen extends Button implements HoverableButton{
    public Button gotoKitchenButton;
    public ButtonMapScreen(){
        gotoKitchenButton = new Button("Go to Kitchen");
        gotoKitchenButton.setStyle(ButtonStyles.getKitchenButtonStyle());
        gotoKitchenButton.setPrefSize(300, 100);
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


}