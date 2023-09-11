import java.util.List;

public abstract class Event {
    protected final Customer customer;
    protected final double eventStartTime;
    protected final boolean human;
    
    Event(Customer customer, double eventStartTime, boolean human) {
        this.customer = customer;
        this.eventStartTime = eventStartTime;
        this.human = human;
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

