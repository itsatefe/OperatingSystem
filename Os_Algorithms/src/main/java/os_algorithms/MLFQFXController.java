package os_algorithms;

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

public class MLFQFXController implements Initializable {

    private ArrayList<process> pList;
    public static int Quantum1;
    public static int Quantum2;
    
    @FXML
    private Pane pane;
    @FXML
    private Label AWT;
    @FXML
    private Label ATT;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Files files = new Files();
        try {
            pList = files.ReadFromfile();
        } catch (Exception ex) {
            System.out.println("cant Read from File in mlfq");
        }
        LinkedList<Label> labels = new LinkedList<>();
        for (int i = 0; i < pList.size(); i++) {
            Label label = new Label("P" + String.valueOf(pList.get(i).getProcess_ID()));
            labels.add(label);
            label.setLayoutX(10);
            label.setLayoutY(390 - i * 90);
            label.setFont(new Font(30.0));
            label.setVisible(true);
            pane.getChildren().add(label);
            //System.out.println(pList.get(i).getProcess_ID());
        }

        process[] p = new process[pList.size()];
        for (int i = 0; i < pList.size(); i++) {
            p[i] = pList.get(i);
            //System.out.println(pList.get(i).getProcess_ID()+"   "+pList.get(i).getArrivalTime());
        }

        MLFQ_algorithm mlfq = new MLFQ_algorithm(Quantum1, Quantum2);
        p = mlfq.schedulingMLFQ(p);

        LinkedList<process> gantt = mlfq.Gant;
        LinkedList<Integer> timegEnd = mlfq.timeGantEnd;
        LinkedList<Integer> timegStart = mlfq.timeGantStart;

        for (int i = 0; i < gantt.size(); i++) {
            System.out.println(timegStart.get(i) + "-" + timegEnd.get(i) + "  process: " + gantt.get(i).getProcess_ID());
        }

        int t = (700 / timegEnd.getLast());
        for (int i = 0; i < gantt.size(); i++) {

            double startX = 0;
            double EndX = 0;
            Line slice = new Line(86, 0, 100, 0);
            slice.setRotate(-90);
            slice.setLayoutX(timegEnd.get(i) * t - 43.65);
            slice.setLayoutY(480);
            pane.getChildren().add(slice);
            Label tSlice = new Label(String.valueOf(timegEnd.get(i)));
            tSlice.setLayoutX(timegEnd.get(i) * t + 43.65);
            tSlice.setLayoutY(500);
            tSlice.setFont(new Font(12));
            pane.getChildren().add(tSlice);

            for (int j = 0; j < p.length; j++) {
                boolean EQ = gantt.get(i).equals(p[j]);
                if (EQ) {
                    double Y = labels.get(j).getLayoutY() + 20;
                    startX = timegStart.get(i) * t;
                    EndX = timegEnd.get(i) * t;
                    Line line = new Line(startX, Y, EndX, Y);
                    line.setLayoutX(50);
                    pane.getChildren().add(line);
                }
            }
        }
   AWT.setText(String.valueOf(MLFQ_algorithm.AWTime));
   
   AWT.setText(String.valueOf(MLFQ_algorithm.ATTime));
    }
    
}
