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

    public int getActualHeught(){
        int count = 0;
        for (int j = 0; j < shape[0].length; j++) {
            for (int i = 0; i < shape.length; i++) {
                if(shape[i][j] != 0){
                   count++;
                   break;
                }
            }
        }
        return count;
    }

    protected byte GetColor(){
        return (byte)(Math.random()*10+1);
    }

    public void rotate() {
        int maxIndex = shape.length - 1;

        byte[][] newShape = new byte[shape[0].length][shape.length];

        for (int i = 0; i < shape[0].length; i++) {
            for (int j = 0; j < shape.length; j++) {
                newShape[i][j] = shape[maxIndex - j][i];
            }
        }
        shape = newShape;
    }
}
