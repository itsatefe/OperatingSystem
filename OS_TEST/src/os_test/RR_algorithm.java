package os_test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//baraye time++ ham ye fekri kon

public class RR_algorithm {

    int time = 0;
    public int quantum;

    public RR_algorithm(int quantum) {
        this.quantum = quantum;
    }
    LinkedList<Integer> timeGantEnd = new LinkedList<>();
    LinkedList<Integer> timeGantStart = new LinkedList<>();
    LinkedList<process> Gant = new LinkedList<>();
    Queue<process> Queue = new LinkedList<>();

    public process[] schedulingRR(process pArray[]) {
        Arrays.sort(pArray);
        LinkedList<process> pList = new LinkedList<>();
        int[] burstTime = new int[pArray.length];
        for (int i = 0; i < pArray.length; i++) {
            burstTime[i] = pArray[i].getBurstTime();
            pList.add(i, pArray[i]);
        }
        while (pArray[0].getArrivalTime() > time) {
            time++;
        }
        while (true) {
            boolean done = true;
            for (int i = 0; i < pArray.length; i++) {
                if (pArray[i].getArrivalTime() <= time) {
                    Queue.add(pArray[i]);
                }
                if (!Queue.isEmpty()) {
                    while (!Queue.isEmpty()) {
                        process p = Queue.remove();
                        int k = pList.indexOf(p);
                        if (burstTime[k] > 0) {
                            Gant.add(p);
                            done = false;
                            if (burstTime[k] <= quantum) {
                                timeGantStart.add(time);
                                time += burstTime[k];
                                timeGantEnd.add(time);
                                p.setCompleteTime(time);
                                burstTime[k] = 0;
                            } else {
                                timeGantStart.add(time);
                                time += quantum;
                                timeGantEnd.add(time);
                                burstTime[k] = burstTime[k] - quantum;
                                for (int j = k + 1; j < pArray.length; j++) {
                                    if (pArray[j].getArrivalTime() <= time && pArray[j].added == false) {
                                        pArray[j].added = true;
                                        Queue.add(pArray[j]);
                                    }
                                }
                                Queue.add(p);
                            }
                        }
                    }
                } else {
                    while (pArray[i].getArrivalTime() > time) {
                        time++;
                    }
                    if (burstTime[i] > 0) {
                        Gant.add(pArray[i]);
                        done = false;
                        if (burstTime[i] <= quantum) {
                            timeGantStart.add(time);
                            time += burstTime[i];
                            timeGantEnd.add(time);
                            pArray[i].setCompleteTime(time);
                            burstTime[i] = 0;
                        } else {
                            timeGantStart.add(time);
                            time += quantum;
                            timeGantEnd.add(time);
                            burstTime[i] = burstTime[i] - quantum;
                            for (int j = i + 1; j < pArray.length; j++) {
                                if (pArray[j].getArrivalTime() <= time && pArray[j].added == false) {
                                    pArray[j].added = true;
                                    Queue.add(pArray[j]);
                                }
                            }
                            Queue.add(pArray[i]);
                        }
                    }
                }
            }
            if (done == true) {
                break;
            }
        }

        this.calcTurnArroundTime(pArray);
        this.calcWatingTime(pArray);
        return pArray;
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
