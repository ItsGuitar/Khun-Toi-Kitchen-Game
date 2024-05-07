package logic.base;

import javafx.scene.canvas.GraphicsContext;

public interface Interactable {
    void interact(GraphicsContext gc);
    void onHover(GraphicsContext gc);
    void onUnhover(GraphicsContext gc);
}
