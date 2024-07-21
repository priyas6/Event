public class Registeration {
    private static int nextId = 1;
    private int registeration_id;
    private int event_id;
    private int participant_id;
    private String date;
    public Registeration(int event_id, int participant_id, String date) {
        this.registeration_id = nextId++;
        this.event_id = event_id;
        this.participant_id = participant_id;
        this.date = date;

    }
    public int getRegisteration_id() {
        return this.registeration_id;
    }
    public int getEvent_id() {
        return this.event_id;
    }
    public int getParticipant_id() {
        return this.participant_id;
    }
    public String getDate() {
        return this.date;
    }
    public void setRegisteration_id(int registeration_id) {
        this.registeration_id = registeration_id;
    }
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }
    public void setParticipant_id(int participant_id) {
        this.participant_id = participant_id;
    }
    public void setDate(String date) {
        this.date = date;
    }



}
