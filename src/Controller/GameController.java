package Controller;

import Model.Difficulty;
import Model.TetrisGame;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class GameController {

    @FXML
    public Canvas canvas;

    @FXML
    public Canvas previewCanvas;

    @FXML
    public GridPane pane;

    TetrisGame game;
    private GraphicsContext gc, pgc;

    public GameController(){

    }

    @FXML
    public void initialize(){
        gc = canvas.getGraphicsContext2D();
        pgc = previewCanvas.getGraphicsContext2D();
        startNewGame();
    }

    private void startNewGame() {
        game = new TetrisGame(gc, pgc, Difficulty.Medium);

    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()){
            case LEFT:
                game.MoveLeft();
                break;
            case RIGHT:
                game.MoveRight();
                break;
            case UP:
                game.Rotate();
                break;
        }
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) {

    }


    public void handleMouseClick(MouseEvent mouseEvent) {
        System.out.println("Stuff");
        gc = canvas.getGraphicsContext2D();
        canvas.requestFocus();
    }
}
