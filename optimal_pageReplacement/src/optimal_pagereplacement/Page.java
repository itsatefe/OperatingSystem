package optimal_pagereplacement;

public class Page {

    private String name ;
    private  int NextLocation = 0;


    public Page(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNextLocation() {
        return NextLocation;
    }

    public void setNextLocation(int NextLocation) {
        this.NextLocation = NextLocation;
    }


}
