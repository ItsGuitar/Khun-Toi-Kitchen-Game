package gui;

import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import logic.Food;

public class MenuBox extends Canvas{
    private GraphicsContext gc;
    private Food food;
    private MenuInfoBox menuInfoBox;
    private Popup popup;
    public MenuBox(Food food){
        super(120, 120);
        this.food = food;
        popup = new Popup();
        gc = this.getGraphicsContext2D();
        gc.setFill(Color.web("#f5ecc4"));
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());
        draw(food);
        setupIndividuallyBoxHover();

    }

    private void draw(Food food){
        Color borderColor = Color.web("#e8d579");
        gc.setStroke(borderColor);
        gc.setLineWidth(3);
        gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
        gc.drawImage(food.getImage(), 25, 10, 70, 70);

        // The text you want to display
        String displayText = "+ " + food.getScore() + " points";

        // Set the font size
        double fontSize = 15; // Change this to the size you want
        Font font = Font.font(fontSize);

        // Create a Text object with the string you want to measure
        Text text = new Text(displayText);
        text.setFont(font); // Set the font for the Text object

        // Get the width of the Text object
        double textWidth = text.getLayoutBounds().getWidth();

        // Calculate the center of the canvas
        double centerX = this.getWidth() / 2;

        // Adjust the x position of the text so it's centered
        double textX = centerX - textWidth / 2;

        gc.setFont(font); // Set the font for the GraphicsContext
        gc.setFill(Color.BLACK); // Set the fill color to black
        gc.fillText(displayText, textX, 100);
    }

    public void setupIndividuallyBoxHover() {
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gc.setFill(Color.web("#e8d579")); // Change the fill color to gray
                gc.fillRect(0, 0, getWidth(), getHeight());
                draw(food);
                gc.setStroke(Color.web("#f5ecc4"));
                gc.setLineWidth(3);
                gc.strokeRect(0, 0, getWidth(), getHeight());

                menuInfoBox = new MenuInfoBox(food);
                menuInfoBox.setMouseTransparent(true);
                popup.getContent().add(menuInfoBox);

                // Get the MenuBox's position and dimensions
                Bounds bounds = MenuBox.this.localToScreen(MenuBox.this.getBoundsInLocal());
                double boxX = bounds.getMinX();
                double boxY = bounds.getMinY();
                double boxWidth = MenuBox.this.getWidth();
                double boxHeight = MenuBox.this.getHeight();

                // Calculate the Popup's position
                double popupX = boxX + boxWidth / 2 - menuInfoBox.getWidth() / 2;
                double popupY = boxY + boxHeight;

                // Show the Popup
                popup.show(MenuBox.this, popupX, popupY);
            }
        });

        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                gc.setFill(Color.web("#f5ecc4")); // Change the fill color back to white
                gc.fillRect(0, 0, getWidth(), getHeight());
                draw(food);

                popup.getContent().remove(menuInfoBox); // Remove the MenuInfoBox from the Popup
                popup.hide();
            }
        });
    }
}

