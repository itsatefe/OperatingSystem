package os_schedualing;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Os_Algorithms extends Application {
public static Stage ps;
    public static void main(String[] args) {
        launch(args);
    }

    static Stage getPrimaryStage() {
        return ps;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ps = primaryStage;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("firstPage.fxml"));
        } catch (IOException ex) {
            System.out.println("Cant load first Page");
        }
        
        Scene scene = new Scene(root);
        primaryStage.setTitle("cpu scheduling");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
