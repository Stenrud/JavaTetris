package Model;

import Model.Tiles.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class TetrisGame {



    private final GameMagic magic;

    private Tile tile;

    private final Difficulty difficulty;
    private byte[][] board;
    private Timeline timeline;
    private static Point2D left = new Point2D(-1, 0);
    private static Point2D right = new Point2D(1, 0);
    private static Point2D down = new Point2D(0, 1);




    public TetrisGame(GameMagic magic, Difficulty difficulty){

        this.magic = magic;
        this.difficulty = difficulty;

        board = new byte[magic.GetWidth()][magic.GetWidth() * 2];
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }

        start();
    }

    public void start() {
        tile = NextTile();
        timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), this::moveTile));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveTile(ActionEvent actionEvent) {
        if(CheckMove(down))
            MoveTile(down);
        else{
            onTiileHitBottom();
        }

        Render();
    }

    private void onTiileHitBottom() {
        FixTile();
        tile = NextTile();
    }

    private Tile NextTile() {

        Tile tempTile = getTile();

        tempTile.position = new Point2D(3, 0);
         return tempTile;
    }

    private Tile getTile() {
        int i = (int)(Math.random()*5);

        switch (i){
            case 0:
                return new LTile();
            case 1:
                return new TTile();
            case 2:
                return new BoxTile();
            case 3:
                return new ITile();
            case 4:
                return new STile();
        }
        return null;
    }

    private void FixTile() {

        for(int i = 0; i < tile.getWidth(); i++){
            for(int j = 0; j < tile.getHeight(); j++){

                int x = i + (int)tile.position.getX(), y = j + (int)tile.position.getY();
                byte newValue = tile.getPosition(i, j);

                if(newValue != 0)
                    board[x][y] = newValue;
            }
        }
    }

    private void DrawTile() {
        for(int i = 0; i < tile.getWidth(); i++){
            for(int j = 0; j < tile.getHeight(); j++){

                int x = i + (int)tile.position.getX(), y = j + (int)tile.position.getY();

                byte value = tile.getPosition(i, j);

                if(value != 0)
                    magic.DrawCell(x, y, ByteToColorConverter.Convert(value));
            }
        }
    }

    private boolean CheckMove(Point2D movement) {

        Point2D position = tile.position.add(movement);

        for(int i = 0; i < tile.getWidth(); i++){
            for(int j = 0; j < tile.getHeight(); j++){

                int x = i + (int)position.getX(), y = j + (int)position.getY();

                byte tileValue = tile.getPosition(i, j);

                if(tileValue != 0) {
                    if (x < 0 || y < 0 || y >= magic.GetWidth() * 2 || x >= magic.GetWidth())
                        return false;
                    if (tileValue > 0 && board[x][y] > 0)
                        return false;
                }
            }
        }

        return true;
    }

    private void MoveTile(Point2D movement) {
        tile.position = tile.position.add(movement);
    }

    public void DrawBoard(){
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                magic.DrawCell(i, j, ByteToColorConverter.Convert(board[i][j]));
            }
        }
    }




    public void MoveLeft() {
        if(CheckMove(left))
            MoveTile(left);
        Render();
    }

    public void MoveRight() {
        if(CheckMove(right))
            MoveTile(right);
        Render();
    }

    public void Rotate() {
        System.out.println("rotate");
    }

    public void MoveDown() {
        while(CheckMove(down))
            MoveTile(down);

        onTiileHitBottom();
        Render();
    }

    private void Render() {
        DrawBoard();
        DrawTile();
        magic.drawGrid();
    }
}
