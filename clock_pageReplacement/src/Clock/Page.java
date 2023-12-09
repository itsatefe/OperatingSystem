package Clock;

public class Page {

    private String name;
    private boolean used;

    public Page(String name) {
        this.name = name;
        this.used = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

}
