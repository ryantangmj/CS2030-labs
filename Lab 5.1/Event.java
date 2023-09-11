import java.util.List;

public abstract class Event {
    protected final Customer customer;
    protected final double eventStartTime;

    public Event(Customer customer, double eventStartTime) {
        this.customer = customer;
        this.eventStartTime = eventStartTime;
    }

    Customer getCustomer() {
        return this.customer;
    }

    double getEventStartTime() {
        return this.eventStartTime;
    }

    Stats updateStats(Stats stats) {
        return stats;
    }

    abstract Boolean hasNextEvent(); 

    abstract Pair<ImList<Server>, Event> addNextEvent(ImList<Server> servers);
}

