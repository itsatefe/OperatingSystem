package optimal_pagereplacement;

import java.util.ArrayList;
import java.util.LinkedList;

public class Optimal {

    private final Page[] page;
    private final int frameNo;

    public Optimal(int frameNo, Page[] page) {
        this.page = page;
        this.frameNo = frameNo;
    }

    public String[][] Optimal_Replace(Page[] page) {
        String[][] states = new String[frameNo + 1][page.length];
        ArrayList<LinkedList<Page>> FrameList = new ArrayList<LinkedList<Page>>();
        for (int i = 0; i < page.length; i++) {
            page[i].setNextLocation(i);
        }
        //--------------------------------------------------------------------------
        int index = 0;
        LinkedList<Page> l = new LinkedList<>();
        states[index][0] = page[0].getName();
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
                states[frameNo][i] = "H";
            } else {
                states[index][i] = page[i].getName();
                states[frameNo][i] = "F";
                FrameList.get(i).add(index, page[i]);
                index++;
            }
        }
        //--------------------------------------------------------------------------
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
                states[frameNo][i] = "H";
            } else {
                if (FrameList.get(i).size() < frameNo) {
                    index = FrameList.get(i).size();
                    states[frameNo][i] = "F";
                    states[index][i] = page[i].getName();
                    FrameList.get(i).add(index, page[i]);
                } else if (FrameList.get(i).size() == frameNo) {
                    int[] NL = new int[FrameList.get(i).size()];
                    for (int j = 0; j < FrameList.get(i).size(); j++) {
                        NL[j] = FrameList.get(i).get(j).getNextLocation();
                    }
                    int pageLocation = max(NL);
                    index = FrameList.get(i).indexOf(page[pageLocation]);
                    states[index][i] = page[i].getName();
                    states[frameNo][i] = "F";
                    FrameList.get(i).set(index, page[i]);
                }
            }
        }
        return states;
    }

    private int max(int[] array) {
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
}
