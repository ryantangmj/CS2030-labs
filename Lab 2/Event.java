import java.util.List;

abstract class Event {
    protected final Customer customer;
    protected final double eventDoneTime;
    protected final String eventType;

    public Event(Customer customer, double eventDoneTime, String eventType) {
        this.customer = customer;
        this.eventDoneTime = eventDoneTime;
        this.eventType = eventType;
    }

    Customer getCustomer() {
        return this.customer;
    }

    double getEventDoneTime() {
        return this.eventDoneTime;
    }

    String getEventType() {
        return this.eventType;
    }
}

