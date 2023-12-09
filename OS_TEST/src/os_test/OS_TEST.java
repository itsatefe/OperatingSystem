package os_test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class OS_TEST {

    public static void main(String[] args) throws Exception {

        ArrayList<process> pList = ReadFromfile();
        process[] p = new process[pList.size()];
        for (int i = 0; i < pList.size(); i++) {
            p[i] = pList.get(i);
        }
//
        MLFQ_algorithm mlfq = new MLFQ_algorithm(10,30);
        p = mlfq.schedulingMLFQ(p);
        
//        RR_algorithm rr = new RR_algorithm(20);
//        p = rr.schedulingRR(p);
        System.out.println("ID     TT     WT");
        for (int i = 0; i < p.length; i++) {
            System.out.println(
                    p[i].getProcess_ID() + "      "
                    + p[i].getTurnaroundTime() + "      "
                    + p[i].getWaitingTime()
            );
        }

        LinkedList<process> gantt = mlfq.Gant;
        LinkedList<Integer> timegEnd = mlfq.timeGantEnd;
        LinkedList<Integer> timegStart = mlfq.timeGantStart;
        System.out.println("-------");
        for (int i = 0; i < gantt.size(); i++) {
            System.out.println(timegStart.get(i) + "-" + timegEnd.get(i) + "  process: " + gantt.get(i).getProcess_ID());
        }

    }

    private static ArrayList<process> ReadFromfile() throws Exception {

        ArrayList<process> pList = new ArrayList<>();
        File file = new File("E:\\netbeans\\OS_TEST\\src\\os_test\\process.txt");
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
