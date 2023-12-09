package LRU;

public class Page {

    private String name ;
    private  int LastLocation = 0;


    public Page(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLastLocation() {
        return LastLocation;
    }

    public void setLastLocation(int LastLocation) {
        this.LastLocation = LastLocation;
    }


}
