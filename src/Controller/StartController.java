package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {

    public Button startButton;

    public void OnStartButtonClicked(ActionEvent actionEvent) throws IOException {

        if(actionEvent.getSource() == startButton){
            Stage stage = (Stage) startButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("/View/GameView.fxml"));
            Scene scene = new Scene(root, 600, 800);
            stage.setScene(scene);
        }
    }

    public void OnHighscoreButtonClicked(ActionEvent actionEvent) {
        //// TODO se issue #3 ;p
    }
}
