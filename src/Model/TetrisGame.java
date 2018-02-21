package Model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

public class TetrisGame {


    private final GraphicsContext gc;
    private final GraphicsContext pgc;
    private final Difficulty difficulty;
    private byte[][] board;
    private Timeline timeline;

    private final int numberOfCellsInWIdth = 10;

    private final double cellSize = 400.0 / numberOfCellsInWIdth;

    public TetrisGame(GraphicsContext gc, GraphicsContext pgc, Difficulty difficulty){

        this.gc = gc;
        this.pgc = pgc;
        this.difficulty = difficulty;

        board = new byte[numberOfCellsInWIdth][numberOfCellsInWIdth * 2];
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = 0;
            }
        }

        drawGrid();
    }

    public void drawGrid(){
        for(int i = 0; i <= board.length; i++){
         gc.strokeLine(i * cellSize, 0, i * cellSize, 800);
        }
        for(int  i= 0; i <= board[0].length; i++){
            gc.strokeLine( 0,i * cellSize,  600,i * cellSize);        }
    }

    public void start() {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), this::moveTile));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void moveTile(ActionEvent actionEvent) {

    }

    public void MoveLeft() {
        System.out.println("left");
    }

    public void MoveRight() {
        System.out.println("right");
    }

    public void Rotate() {
        System.out.println("rotate");
    }
}
