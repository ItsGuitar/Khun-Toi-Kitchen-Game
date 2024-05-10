package gui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import logic.base.HoverableButton;

public class MenuBox extends Canvas implements HoverableButton {
    private GraphicsContext gc;
    public void setupIndividuallyButtonHover(Button b) {
        b.setOnMouseEntered(e -> {
            b.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff;");
        });
        b.setOnMouseExited(e -> {
            b.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #000000;");
        });
    }
}
