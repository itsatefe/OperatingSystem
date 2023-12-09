package os_algorithms;

public class process implements Comparable<process> {

    private int burstTime;
    private int arrivalTime;
    private int TurnaroundTime;
    private int WaitingTime;
    private int process_ID;
    private int completeTime;
    public int flag = 0;
    public boolean added = false;

    public void setProcess_ID(int process_ID) {
        this.process_ID = process_ID;
    }

    public int getProcess_ID() {
        return process_ID;
    }

    public void setCompleteTime(int completeTime) {
        this.completeTime = completeTime;
    }

    public int getCompleteTime() {
        return completeTime;
    }

    public process(int process_ID, int arrivalTime, int burstTime) {
        this.process_ID = process_ID;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTurnaroundTime() {
        return TurnaroundTime;
    }

    public void setTurnaroundTime(int TurnaroundTime) {
        this.TurnaroundTime = TurnaroundTime;
    }

    public int getWaitingTime() {
        return WaitingTime;
    }

    public void setWaitingTime(int WaitingTime) {
        this.WaitingTime = WaitingTime;
    }

    @Override
    public int compareTo(process o) {
        int Compare = ((process) o).arrivalTime;
        return this.arrivalTime - Compare;
    }

}
