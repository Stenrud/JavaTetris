package Model.Tiles;

import javafx.geometry.Point2D;

public abstract class Tile {
    protected byte[][] shape;
    public Point2D position;

    public byte getPosition(int x, int y){
        return shape[x][y];
    }

    public int getWidth(){
        return shape.length;
    }

    public int getHeight(){
        return shape[0].length;
    }

    protected byte GetColor(){
        return (byte)(Math.random()*10+1);
    }
}
