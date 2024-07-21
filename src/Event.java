public class Event {
    private static int nextId = 1;
    private int event_id;
    private String name;
    private String date;
    private String location;
    private int capacity;
    public Event(String name, String date, String location, int capacity) {
        this.event_id=nextId++;
        this.name = name;
        this.date = date;
        this.location = location;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }


    public String getLocation() {
        return location;

    }

    public void setLocation(String location) {
        this.location = location;

    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getEvent_id() {
        return event_id;
    }

}
