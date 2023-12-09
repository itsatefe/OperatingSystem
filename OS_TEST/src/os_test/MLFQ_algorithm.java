package os_test;

import java.util.LinkedList;
import java.util.Queue;

public class MLFQ_algorithm {

    private int quantum1;
    private int quantum2;
    private int time = 0;
    
    public MLFQ_algorithm(int quantum1, int quantum2) {
        this.quantum1 = quantum1;
        this.quantum2 = quantum2;
    }

    Queue<process> Q1 = new LinkedList<>();
    LinkedList<process> pList = new LinkedList<>();
    LinkedList<process> Gant = new LinkedList<>();
    LinkedList<Integer> timeGantStart = new LinkedList<>();
    LinkedList<Integer> timeGantEnd = new LinkedList<>();
    Queue<process> Q2 = new LinkedList<>();
    Queue<process> Q3 = new LinkedList<>();

    public process[] schedulingMLFQ(process[] p) {

        int[] burstTime = new int[p.length];
        int[] arrivalTime = new int[p.length];

        for (int i = 0; i < p.length; i++) {
            pList.add(p[i]);
            burstTime[i] = p[i].getBurstTime();
            arrivalTime[i] = p[i].getArrivalTime();
        }

        while (!check(p, arrivalTime) && Q1.isEmpty() && Q2.isEmpty() && Q3.isEmpty()) {
            time++;
        }

        while (true) {
            boolean finish = true;

            check(p, arrivalTime);
            while (!Q1.isEmpty()) {
                //System.out.println(Q1.element().getProcess_ID());
                finish = false;
                executeQ1(burstTime);
                check(p, arrivalTime);
            }
            if (Q1.isEmpty()) {

                if (!Q2.isEmpty()) {
                    finish = false;
                    LinkedList<process> Q2Elem = new LinkedList<>();
                    int i = 0;
                    while (i < Q2.size()) {
                        //System.out.println("alwayyyys");
                        Q2Elem.add(Q2.element());
                        i++;
                    }
                    int keepPlaceQ2 = 0;
                    for (int j = keepPlaceQ2; j < Q2Elem.size(); j++) {
                        executeQ2(burstTime);
                        boolean checked = check(p, arrivalTime);
                        if (checked) {
                            keepPlaceQ2 = j;
                            while (!Q1.isEmpty()) {
                                finish = false;
                                Q2Elem = executeQ1(burstTime);
                            }
                        }
                    }
                } else {
                    if (!Q3.isEmpty()) {
                        finish = false;
                        LinkedList<process> Q3Elem = new LinkedList<>();
                        int i = 0;
                        while (i < Q3.size()) {
                            Q3Elem.add(Q3.element());
                            i++;
                        }
                        int KeepPlaceQ3 = 0;
                        for (int j = KeepPlaceQ3; j < Q3Elem.size(); j++) {
                            executeQ3(burstTime);
                            boolean checked = check(p, arrivalTime);
                            if (checked) {
                                KeepPlaceQ3 = j;
                                do {
                                    executeQ1(burstTime);
                                } while (!Q1.isEmpty());
                                do {
                                    Q3Elem = executeQ2(burstTime);
                                } while (!Q2.isEmpty());
                            }
                        }
                    }
                }
            }

            if (finish == true) {
                break;
            }
        }
        this.calcTurnArroundTime(p);
        this.calcWatingTime(p);
        System.out.println("------");
        return p;
    }

    private LinkedList<process> executeQ1(int[] burstTime) {
        process p = Q1.remove();
        int k = pList.indexOf(p);
        if (burstTime[k] > 0) {
            //System.out.println("Execute Q1 process :" + p.getProcess_ID());
            Gant.add(p);
            if (burstTime[k] <= quantum1) {
                timeGantStart.add(time);
                time += burstTime[k];
                timeGantEnd.add(time);
                p.setCompleteTime(time);
                burstTime[k] = 0;
            } else {
                timeGantStart.add(time);
                time += quantum1;
                timeGantEnd.add(time);
                //System.out.println(time);
                burstTime[k] -= quantum1;
                Q2.add(p);
            }
        }
        LinkedList<process> Q2List = new LinkedList<>();
        for (int i = 0; i < Q2.size(); i++) {
            //boolean addmishe = 
            Q2List.add(Q2.element());
            //System.out.println("process "+Q2.element().getProcess_ID()+"add shod b Q2List: "+addmishe);
        }
        return Q2List;
    }

    private LinkedList<process> executeQ2(int[] burstTime) {
        process p = Q2.remove();
        int k = pList.indexOf(p);
        if (burstTime[k] > 0) {
            //System.out.println("execute Q2 process :" + p.getProcess_ID());
            Gant.add(p);
            if (burstTime[k] <= quantum2) {
                timeGantStart.add(time);
                time += burstTime[k];
                timeGantEnd.add(time);
                p.setCompleteTime(time);
                burstTime[k] = 0;
            } else {
                timeGantStart.add(time);
                time += quantum2;
                timeGantEnd.add(time);
                burstTime[k] -= quantum2;
                //boolean bool = 
                Q3.add(p);
                //System.out.println("process " + p.getProcess_ID() + " b safe Q3 add shod?" + bool);
            }
        }
        LinkedList<process> Q3List = new LinkedList<>();
        if (!Q3.isEmpty()) {
            for (int i = 0; i < Q2.size(); i++) {
                Q3List.add(Q3.element());
            }
        }
        return Q3List;
    }

    private void executeQ3(int burstTime[]) {
        process p = Q3.remove();
        int k = pList.indexOf(p);
        if (burstTime[k] > 0) {
            Gant.add(p);
            timeGantStart.add(time);
            time += burstTime[k];
            timeGantEnd.add(time);
            p.setCompleteTime(time);
            burstTime[k] = 0;
        }
    }

    private boolean check(process[] p, int[] arrivalTime) {

        boolean bool = false;
        for (int i = 0; i < p.length; i++) {
            if (arrivalTime[i] <= time && p[i].added == false) {
                p[i].added = true;
                Q1.add(p[i]);
                bool = true;
            }
        }
        return bool;
    }

    private void calcTurnArroundTime(process p[]) {
        float AvG = 0;
        for (int i = 0; i < p.length; i++) {
            p[i].setTurnaroundTime(p[i].getCompleteTime() - p[i].getArrivalTime());
            AvG += p[i].getTurnaroundTime();
        }
        System.out.println("Average of TurnArround time :" + (float) AvG / p.length);
    }

    private void calcWatingTime(process p[]) {
        float AvG = 0;
        for (int i = 0; i < p.length; i++) {
            p[i].setWaitingTime(p[i].getTurnaroundTime() - p[i].getBurstTime());
            AvG += p[i].getWaitingTime();
        }
        System.out.println("Average of Waiting time :" + (float) AvG / p.length);
    }

}
