package logic.base;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

public interface Interactable {
    public void interact(GraphicsContext gc);
    public void onHover(GraphicsContext gc);
    public void onUnhover(GraphicsContext gc);
    public int getX();
    public int getY();
    public int getSizeX();
    public int getSizeY();
    public void handleClick(MouseEvent event, GraphicsContext gc);

}
