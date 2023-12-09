
package operating_sys;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Operating_Sys extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Parent root = null;
        try {
           root = FXMLLoader.load(getClass().getResource("RRFX.fxml"));
        } catch (IOException ex) {
            System.out.println("Cant load");
        }

        Scene scene = new Scene(root);
        primaryStage.setTitle("Round Rubin");
        //primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/graphmine/spider-graph.jpg")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
