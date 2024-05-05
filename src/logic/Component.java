package logic;

import sharedObject.IRenderable;

public abstract class Component implements IRenderable {
    protected double x,y;
    protected int z;
    protected boolean visible;
    protected Component(){
        visible = true;
    }
    public int getZ(){
        return z;
    }
    @Override
    public boolean isVisible(){
        return visible;
    }
}
