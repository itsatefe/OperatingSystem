package os_schedualing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Files {

    public ArrayList<process> ReadFromfile() throws Exception {

        ArrayList<process> pList = new ArrayList<>();
        File file = new File("E:\\netbeans\\Os_Algorithms\\src\\os_algorithms\\process.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        int arrivalTime = 0;
        int burstTime = 0;
        int process_ID = 0;
        while ((line = br.readLine()) != null) {

            String[] value = line.split(",");
            process_ID = Integer.parseInt(value[0]);
            arrivalTime = Integer.parseInt(value[1]);
            burstTime = Integer.parseInt(value[2]);
            process p = new process(process_ID, arrivalTime, burstTime);
            pList.add(p);
        }
        //System.out.println(arrivalTime+" "+burstTime+" "+process_ID);
        return pList;
    }
}
