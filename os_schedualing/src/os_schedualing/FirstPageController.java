package os_schedualing;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class FirstPageController implements Initializable {
    
    @FXML
    private Pane pane;
    @FXML
    private Button RRBtn;
    @FXML
    private Button MLFQBtn;
    @FXML
    private TextField txtFieldQ;
    

 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void RRscheduling(ActionEvent event) {
        RRFXController.Quantum = Integer.parseInt(txtFieldQ.getText());
        Stage stage = Os_Algorithms.getPrimaryStage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("RRFX.fxml"));
        } catch (IOException ex) {
            System.out.println("rr page doesnt load");
        }
        stage.setScene(new Scene(root));

    
    }

    @FXML
    private void MLFQScheduling(ActionEvent event) {
        String Q[]=txtFieldQ.getText().split(",");
        MLFQFXController.Quantum1 = Integer.parseInt(Q[0]);
        MLFQFXController.Quantum2 = Integer.parseInt(Q[1]);
       
        Stage stage = Os_Algorithms.getPrimaryStage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("MLFQFX.fxml"));
        } catch (IOException ex) {
            System.out.println("mlfq page doesnt load");
        }
        stage.setScene(new Scene(root));
    }
    
}
