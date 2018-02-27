package Model;

import Model.Interfaces.BecauseIDontKnowHowToUseEvents;
import Model.Tiles.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Point2D;
import javafx.util.Duration;

public class TetrisGame {


    private final TetrisBoard board;
    private final BecauseIDontKnowHowToUseEvents controller;
    private Tile mainTile;
    private Tile nextTile;

    private Timeline timeline;
    private static Point2D left = new Point2D(-1, 0);
    private static Point2D right = new Point2D(1, 0);
    private static Point2D down = new Point2D(0, 1);
    private static Point2D spawn = new Point2D(0, 0);
    private double multiplier;
    private int score;


    public TetrisGame(TetrisBoard board, BecauseIDontKnowHowToUseEvents controller) {
        this.board = board;
        this.controller = controller;
        score = 0;
        multiplier = 1;
        start();
    }

    public void start() {
        mainTile = getRandomTile();
        mainTile.position = board.getStartPosition(mainTile);
        nextTile = getRandomTile();
        board.previewTile(nextTile);

        timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), this::moveTile));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveTile(ActionEvent actionEvent) {
        if(board.CheckMove(mainTile, down))
            MoveTile(down);
        else{
            onTiileHitBottom();
        }

        drawTile();
    }

    private void onTiileHitBottom() {
        board.FixTile(mainTile);

        int count = board.countAndRemoveCompleteRows(0, board.getHeight());
        if(count != 0) {
            score += count * multiplier;
            multiplier *= 1.0 + count/10.0;
            System.out.println(multiplier);
            controller.DisplayScore(score);
            timeline.setRate(multiplier);
        }
        NextTile();
        if(!board.CheckMove(mainTile, spawn)){
            gameOver();
        }
    }

    private void gameOver() {
        timeline.stop();
        controller.gameOver();
    }

    private void NextTile() {
        mainTile = nextTile;
        mainTile.position = board.getStartPosition(mainTile);
        nextTile = getRandomTile();
        board.previewTile(nextTile);
    }

    private Tile getRandomTile() {
        int i = (int)(Math.random()*7);

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
            case 5:
                return new SRTile();
            case 6:
                return new LRTile();
        }
        return null;
    }


    private void MoveTile(Point2D movement) {
        mainTile.position = mainTile.position.add(movement);
    }


    public void MoveLeft() {
        if(board.CheckMove(mainTile, left))
            MoveTile(left);
        drawTile();
    }

    private void drawTile() {
        board.RenderWIthTile(mainTile);

        Point2D position = down;

        while (board.CheckMove(mainTile, position)){
            position = position.add(down);
        }

        board.RenderAimingGhost(mainTile, mainTile.position.add(position.subtract(down)));
    }

    public void MoveRight() {
        if(board.CheckMove(mainTile, right))
            MoveTile(right);
        drawTile();
    }

    public void MoveDown() {
        if(board.CheckMove(mainTile, down))
            MoveTile(down);
        drawTile();
    }

    public void Rotate() {
        if(board.checkRotation(mainTile)){
            mainTile.rotate();
            drawTile();
        }
    }

    public void BodySlam() {
        while(board.CheckMove(mainTile, down))
            MoveTile(down);

        onTiileHitBottom();
        drawTile();
    }


}
