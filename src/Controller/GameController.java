package Controller;

import Model.GameMagic;
import Model.Interfaces.BecauseIDontKnowHowToUseEvents;
import Model.TetrisBoard;
import Model.TetrisGame;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class GameController implements BecauseIDontKnowHowToUseEvents{

    @FXML
    public Canvas canvas;

    @FXML
    public Canvas previewCanvas;

    @FXML
    public Label scoreLabel;
    @FXML
    public GridPane pane;

    TetrisGame game;
    private GraphicsContext gc, pgc;
    private boolean gameIsRunning;

    public GameController(){

    }

    @FXML
    public void initialize(){
        gc = canvas.getGraphicsContext2D();
        pgc = previewCanvas.getGraphicsContext2D();
        startNewGame();
    }

    private void startNewGame() {

        game = new TetrisGame(new TetrisBoard(new GameMagic(gc, pgc)), this);
        gameIsRunning = true;
    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        if(gameIsRunning)
        switch (keyEvent.getCode()){
            case LEFT:
                game.MoveLeft();
                break;
            case RIGHT:
                game.MoveRight();
                break;
            case DOWN:
                game.MoveDown();
                break;
            case SPACE:
                game.BodySlam();
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
        canvas.requestFocus();
    }

    @Override
    public void gameOver() {
        gameIsRunning = false;
    }

    @Override
    public void DisplayScore(int i) {
        scoreLabel.setText(Integer.toString(i));
    }
}
