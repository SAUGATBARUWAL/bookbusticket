package shared;
import java.io.Serializable;

public class Ticket implements Serializable {
    private String name;
    private String destination;
    private String date;

    public Ticket(String name, String destination, String date) {
        this.name = name;
        this.destination = destination;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }
}
 

