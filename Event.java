public class Event {
    private String eventName;
    private String eventDate;

    public Event(String eventName, String eventDate) {
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

    // Getters
    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    @Override
    public String toString() {
        return "Event: " + eventName + ", Date: " + eventDate;
    }
}