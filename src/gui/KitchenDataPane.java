package gui;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import logic.GameController;
import sharedObject.RenderableHolder;

import java.util.ArrayList;
import java.util.Collections;

public class KitchenDataPane extends StackPane {
    private static int width = 171;
    private static int height = 342;
    private static GraphicsContext gc;
    private static GridPane gridPane;
    private static ArrayList<Integer> ingredientAmount;
    private Canvas canvas;
    public KitchenDataPane(){
        this.setWidth(width);
        this.setHeight(height);
        canvas = new Canvas(this.getWidth(),this.getHeight());
        gc = canvas.getGraphicsContext2D();
        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        ingredientAmount = new ArrayList<>(Collections.nCopies(10, 0));
        canvas.setTranslateY(185);
        gridPane.setTranslateY(185);
        this.getChildren().addAll(canvas,gridPane);
        this.setMouseTransparent(true);
    }
    public void update(){
        gc.clearRect(0,0,this.getWidth(),this.getHeight());
        drawBackground(gc);
        ArrayList<Integer> previousIngredientAmount = new ArrayList<>(ingredientAmount);
        ArrayList<Integer> flashedIndex = new ArrayList<>();
        for (int i = 0; i < ingredientAmount.size(); i++){
            int currentAmount = GameController.getIngredient_amount().get(i);
            if (currentAmount - previousIngredientAmount.get(i) != 0) {
                flashedIndex.add(i);
            }
            ingredientAmount.set(i, currentAmount);
        }
        drawIngredient(gc);

        for (int index : flashedIndex) {
            flashImage(index);
        }

    }
    public void drawBackground(GraphicsContext gc){
        gc.drawImage(RenderableHolder.kitchenDataPane_background,0,0,this.getWidth(),this.getHeight());
    }
    public void drawIngredient(GraphicsContext gc){
        gridPane.getChildren().clear();
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 5; j++){
                VBox vBox = new VBox();
                vBox.setAlignment(Pos.CENTER);
                ImageView imageView = new ImageView(RenderableHolder.ingredientSprite.get(i * 5 + j));
                imageView.setFitWidth(50); // Set the width of the ImageView
                imageView.setFitHeight(50); // Set the height of the ImageView
                Label nameLabel = new Label(GameController.INGREDIENTS[i * 5 + j] + " : " + String.valueOf(ingredientAmount.get(i * 5 + j)));
                nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 9");
                vBox.getChildren().addAll(imageView, nameLabel);
                gridPane.add(vBox,i,j);
            }
        }
    }

    private void flashImage(int index) {
        // Get the VBox for the ingredient at the given index
        VBox vBox = (VBox) gridPane.getChildren().get(index);

        // Get the ImageView from the VBox
        ImageView imageView = (ImageView) vBox.getChildren().get(0);

        // Create a ColorAdjust effect to make the image white
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(1.0);
        imageView.setEffect(colorAdjust);

        // Create a Timeline to gradually decrease the brightness of the ColorAdjust effect
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0), new KeyValue(colorAdjust.brightnessProperty(), 1.0)),
                new KeyFrame(Duration.seconds(1), new KeyValue(colorAdjust.brightnessProperty(), 0.0))
        );

        // Start the Timeline
        timeline.play();
    }
}