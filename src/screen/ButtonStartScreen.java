package screen;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class ButtonStartScreen extends VBox {
    public Button buttonStart;
    public Button buttonExit;
    public Button buttonHowToPlay;

    public ButtonStartScreen(){
        buttonStart = new Button("Start");
        buttonExit = new Button("Exit");
        buttonHowToPlay = new Button("How to Play");

        buttonStart.setPrefSize(100, 50);
        buttonExit.setPrefSize(100, 50);
        buttonHowToPlay.setPrefSize(100, 50);

        this.getChildren().addAll(buttonStart, buttonHowToPlay, buttonExit);
    }
}
