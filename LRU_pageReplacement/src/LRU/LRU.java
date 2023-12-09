package LRU;

import java.util.ArrayList;
import java.util.LinkedList;

public class LRU {

    private int frameNo = 0;
    private final Page[] page;

    public LRU(int frameNo, Page[] page) {
        this.page = page;
        this.frameNo = frameNo;
    }

    public String[][] LRU_replace(Page[] page) {

        ArrayList<LinkedList<Page>> FrameList = new ArrayList<LinkedList<Page>>();
        String[][] states = new String[frameNo + 1][page.length];
        //----------------------------------------------------------------------

        int index = 0;
        LinkedList<Page> l = new LinkedList<>();
        states[index][0] = page[0].getName();
        page[0].setLastLocation(0);
        states[frameNo][0] = "F";
        l.add(index, page[0]);
        FrameList.add(l);
        index++;
        //----------------------------------------------------------------------

        for (int i = 1; i < frameNo; i++) {
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
                page[i].setLastLocation(i);
                states[frameNo][i] = "H";

            } else {
                states[index][i] = page[i].getName();
                states[frameNo][i] = "F";
                FrameList.get(i).add(index, page[i]);
                page[i].setLastLocation(i);
                index++;
            }
        }

        //----------------------------------------------------------------------
        for (int i = frameNo; i < page.length; i++) {
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
                page[i].setLastLocation(i);
                states[frameNo][i] = "H";
            } else {
                if (FrameList.get(i).size() < frameNo) {
                    index = FrameList.get(i).size();
                    states[frameNo][i] = "F";
                    states[index][i] = page[i].getName();
                    page[i].setLastLocation(i);
                    FrameList.get(i).add(index, page[i]);
                } else if (FrameList.get(i).size() == frameNo) {
                    int[] LL = new int[FrameList.get(i).size()];
                    for (int j = 0; j < FrameList.get(i).size(); j++) {
                        LL[j] = FrameList.get(i).get(j).getLastLocation();
                    }
                    int pageLocation = min(LL);
                    index = FrameList.get(i).indexOf(page[pageLocation]);
                    states[index][i] = page[i].getName();
                    states[frameNo][i] = "F";
                    FrameList.get(i).set(index, page[i]);
                    page[i].setLastLocation(i);
                }
            }
        }
        return states;
    }

    private int min(int[] array) {
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }
        return min;
    }
}