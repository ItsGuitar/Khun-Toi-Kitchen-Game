package logic.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public interface Interactable {
    void interact(GraphicsContext gc);
    void onHover(GraphicsContext gc);
    void onUnhover(GraphicsContext gc);
    int getX();
    int getY();
    int getSizeX();
    int getSizeY();
    void handleClick(MouseEvent event, GraphicsContext gc);

}
