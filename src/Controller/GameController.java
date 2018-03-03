package Controller;

import Model.GameMagic;
import Model.Interfaces.BecauseIDontKnowHowToUseEvents;
import Model.Internet.HighscoreService;
import Model.TetrisBoard;
import Model.TetrisGame;
import Model.UserScore;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class GameController implements BecauseIDontKnowHowToUseEvents{

    @FXML
    public Canvas canvas;
    @FXML
    public Canvas previewCanvas;
    @FXML
    public Label scoreLabel;
    @FXML
    public GridPane pane;
    @FXML
    public ListView<Integer> scoreListView;
    @FXML
    public ListView<String> nameListView;

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

        new Thread(this::LoadHighScores).start();

    }

    private void LoadHighScores() {
        List<String> names = new ArrayList<>();
        List<Integer> scores = new ArrayList<>();

        List<UserScore> userScores = HighscoreService.GetHighScores();

        for(UserScore userScore : userScores){
            names.add(userScore.name);
            scores.add(userScore.score);
        }

        nameListView.setItems(FXCollections.observableArrayList(names));
        scoreListView.setItems(FXCollections.observableArrayList(scores));

    }

    private void startNewGame() {

        game = new TetrisGame(new TetrisBoard(new GameMagic(gc, pgc)), this);
        gameIsRunning = true;
        Platform.runLater(() -> canvas.requestFocus());
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
        System.out.println("In "+keyEvent.getCode());
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) {
        System.out.println("Out " + keyEvent.getCode());
    }


    public void handleMouseClick(MouseEvent mouseEvent) {
        System.out.println("Stuff");
        canvas.requestFocus();
    }

    @Override
    public void gameOver(int score) {
        gameIsRunning = false;
        ShowScoreDialog(score, "", "Your score was ");

    }

    private void ShowScoreDialog(int score, String name, String header) {
        TextInputDialog dialog = new TextInputDialog(name);
        dialog.setTitle("Save your score");
        dialog.setHeaderText(header + score);
        dialog.setContentText("Please enter your name:");

        Optional<String> result = dialog.showAndWait();
// Traditional way to get the response value.

        if (result.isPresent()) {
            if(!HighscoreService.AddHighScore(new UserScore(result.get(), score)))
                ShowScoreDialog(score, result.get(), "Something went wrong, please check your connection and try again, your score was ");
        }
    }
// The Java 8 way to get the response value (with lambda expression).
//        result.ifPresent(name -> System.out.println("Your name: " + name));

    @Override
    public void DisplayScore(int i) {
        scoreLabel.setText(Integer.toString(i));
    }
}
