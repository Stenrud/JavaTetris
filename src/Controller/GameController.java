package Controller;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class GameController {

    @FXML
    public Canvas canvas;

    private GraphicsContext gc;

    public GameController(){

    }

    @FXML
    public void initialize(){
        gc = canvas.getGraphicsContext2D();

    }

    @FXML
    public void handleKeyPressed(KeyEvent keyEvent) {
        System.out.println("sont press");
    }

    @FXML
    public void handleKeyReleased(KeyEvent keyEvent) {
    }


    public void handleMouseClick(MouseEvent mouseEvent) {
        System.out.println("Stuff");
        gc = canvas.getGraphicsContext2D();
        gc.fillRect(0,0,2000,2000);
    }
}
