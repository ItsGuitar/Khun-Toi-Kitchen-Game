package sharedObject;

import javafx.scene.canvas.GraphicsContext;

public interface IRenderable {
    public int getZ(); //Order of rendering
    public int getX();
    public int getY();
    public int getSizeX();
    public int getSizeY();
    public void draw(GraphicsContext gc); //Draw the object
    public boolean isVisible(); //Check if the object is visible
}
