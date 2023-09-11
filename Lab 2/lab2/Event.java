abstract class Event {
    protected final Customer customer;
    protected final String state;
    protected final double time;

    Event(Customer customer, double time, String state) {
        this.customer = customer;
        this.time = time;
        this.state = state; 
    }

    Customer getCustomer() {
        return this.customer;
    }

    double getTime() {
        return this.time;
    }

    String getState() {
        return this.state;
    }

    public abstract Event run(Shop shop);

    public abstract Server getServer();

    @Override
    public String toString() {
        return String.format("%.04d", this.time);
    }
}
