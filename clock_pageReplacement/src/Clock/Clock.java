package Clock;

import java.util.ArrayList;
import java.util.LinkedList;

public class Clock {

    private final int frameNo;
    private final Page[] page;

    public Clock(int frameNo, Page[] page) {
        this.frameNo = frameNo;
        this.page = page;
    }

    public String[][] clock_replace(Page[] page) {

        ArrayList<LinkedList<Page>> FrameList = new ArrayList<>();
        String[][] states = new String[frameNo + 1][page.length];

        //----------------------------------------------------------------------
        int pointer = 0;
        LinkedList<Page> l = new LinkedList<>();
        states[pointer][0] = page[0].getName();
        states[frameNo][0] = "F";
        page[0].setUsed(true);
        l.add(pointer, page[0]);
        FrameList.add(l);
        pointer++;
        //----------------------------------------------------------------------

        for (int i = 1; i < frameNo; i++) {
            for (int j = 0; j < frameNo + 1; j++) {
                states[j][i] = states[j][i - 1];
            }
            FrameList.add(i, FrameList.get(i - 1));
            boolean hit = false;
            int k = 0;
            for (int j = 0; j < FrameList.get(i).size(); j++) {
                if (FrameList.get(i).get(j).getName().equals(page[i].getName())) {
                    hit = true;
                    k = j;
                    break;
                }
            }
            if (hit) {
                FrameList.get(i).get(k).setUsed(true);
                FrameList.get(i).set(k, page[i]);
                states[frameNo][i] = "H";
            } else {
                states[pointer][i] = page[i].getName();
                states[frameNo][i] = "F";
                page[i].setUsed(true);
                FrameList.get(i).add(pointer, page[i]);
                pointer++;
            }
        }
        if (pointer == frameNo) {
            pointer = 0;
        }
        //----------------------------------------------------------------------
        
        for (int i = frameNo; i < page.length; i++) {
            page[i].setUsed(true);
            FrameList.add(i, FrameList.get(i - 1));
            for (int j = 0; j < frameNo + 1; j++) {
                states[j][i] = states[j][i - 1];
            }

            boolean hit = false;
            int k = 0;
            for (int j = 0; j < FrameList.get(i).size(); j++) {
                if (FrameList.get(i).get(j).getName().equals(page[i].getName())) {
                    hit = true;
                    k = j;
                    break;
                }
            }
            if (hit) {
                FrameList.get(i).set(k, page[i]);
                states[frameNo][i] = "H";
            } else {
                if (FrameList.get(i).size() < frameNo) {
                    states[pointer][i] = page[i].getName();
                    states[frameNo][i] = "F";
                    FrameList.get(i).add(pointer, page[i]);
                    pointer++;
                    if (pointer >= frameNo) {
                        pointer = 0;
                    }
                } else if (FrameList.get(i).size() == frameNo) {
                    while (FrameList.get(i).get(pointer).getUsed() == true) {
                        FrameList.get(i).get(pointer).setUsed(false);
                        pointer++;
                        if (pointer == frameNo) {
                            pointer = 0;
                        }
                    }
                    states[pointer][i] = page[i].getName();
                    states[frameNo][i] = "F";
                    FrameList.get(i).set(pointer, page[i]);
                    pointer++;
                    if (pointer == frameNo) {
                        pointer = 0;
                    }
                }
            }
        }
        return states;
    }

}
