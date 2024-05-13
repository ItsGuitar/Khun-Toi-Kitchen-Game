package screen;

import constant.ButtonStyles;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import logic.base.HoverableButton;

public class ButtonStartScreen extends StackPane implements HoverableButton {
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
        setMargin(buttonExit, new Insets(150, 50, 0, 0));

        //styling

        String buttonStyle = ButtonStyles.getNormalButtonStyle();
        buttonStart.setStyle(buttonStyle);
        buttonExit.setStyle(buttonStyle);
        buttonHowToPlay.setStyle(buttonStyle);

        this.getChildren().addAll(buttonStart, buttonHowToPlay, buttonExit);
    }

    public void setupButtonExit(){
        buttonExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
    }

    @Override
    public void setupIndividuallyButtonHover(Button b){
        b.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String hoverButtonStyle = ButtonStyles.getHoverButtonStyle();
                b.setStyle(hoverButtonStyle);
            }
        });

        b.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String buttonStyle = ButtonStyles.getNormalButtonStyle();
                b.setStyle(buttonStyle);
            }
        });
    }
    public void setupButtonHover(){
        setupIndividuallyButtonHover(buttonStart);
        setupIndividuallyButtonHover(buttonExit);
        setupIndividuallyButtonHover(buttonHowToPlay);
    }
}
