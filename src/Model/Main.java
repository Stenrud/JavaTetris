package Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("../View/StartView.fxml"));

        Scene scene = new Scene(root, 600, 800);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    public static void main(String ... args){
        launch(args);

    }
}
