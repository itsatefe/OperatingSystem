package Clock;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Page> pList = new ArrayList<>();
        try {
            pList = ReadFromfile();
        } catch (Exception ex) {
            System.out.println("Cant Read from clock file");
        }

        Page[] pages = new Page[pList.size()];
        for (int j = 0; j < pList.size(); j++) {
            pages[j] = pList.get(j);
        }
        
        //----------------------------------------------------------------------
        
        int frameNo = 3;
        Clock c = new Clock(frameNo, pages);
        String[][] states = new String[frameNo + 1][pages.length];
        states = c.clock_replace(pages);
        
        //----------------------------------------------------------------------
        for (int i = 0; i < pages.length; i++) {
            System.out.print(pages[i].getName() + " ");
        }
        System.out.println();
        System.out.println("-------------------------");

        for (int i = 0; i < frameNo + 1; i++) {
            for (int j = 0; j < pages.length; j++) {
                if (states[i][j] == null) {
                    states[i][j] = "-";
                }
                System.out.print(states[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static ArrayList<Page> ReadFromfile() throws Exception {

        ArrayList<Page> pList = new ArrayList<>();
        File file = new File("E:\\netbeans\\clock_pageReplacement\\src\\Clock\\ClockFile.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        String page_name = "";
        while ((line = br.readLine()) != null) {
            String[] value = line.split(",");
            page_name = value[0];
            Page p = new Page(page_name);
            pList.add(p);
        }
        return pList;
    }

}
