package screen;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ButtonStartScreen extends StackPane {
    public Button buttonStart;
    public Button buttonExit;
    public Button buttonHowToPlay;

    public ButtonStartScreen(){
        buttonStart = new Button("Start");
        buttonExit = new Button("Exit");
        buttonHowToPlay = new Button("How to Play");

        setAlignment(Pos.CENTER_RIGHT);
        setAlignment(buttonHowToPlay, Pos.CENTER_LEFT);

        buttonStart.setPrefSize(200, 50);
        buttonExit.setPrefSize(200, 50);
        buttonHowToPlay.setPrefSize(200, 50);

        //margin
        setMargin(buttonHowToPlay, new Insets(50, 0, 0, 50)); // top, right, bottom, left
        setMargin(buttonStart, new Insets(0, 50, 0, 0));
        setMargin(buttonExit, new Insets(125, 50, 0, 0));

        //styling
        String buttonStyle = "-fx-background-color: linear-gradient(to bottom, white, #FFB3BA); " + // ultra light pink color
                "-fx-border-color: black; " + // black border
                "-fx-border-width: 2px; " + // border width
                "-fx-font-family: 'Comic Sans MS'; " + // cartoonish text
                "-fx-font-size: 20px; " + // text size
                "-fx-text-fill: black; " + // black text color
                "-fx-background-radius: 15; " + // corner radii
                "-fx-border-radius: 15;"; // border radii
        buttonStart.setStyle(buttonStyle);
        buttonExit.setStyle(buttonStyle);
        buttonHowToPlay.setStyle(buttonStyle);

        this.getChildren().addAll(buttonStart, buttonHowToPlay, buttonExit);
    }

    public void setupButtonExit(){
        buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.exit();
            }
        });
    }
}
