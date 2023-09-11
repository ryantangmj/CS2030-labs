class Leave extends Event {
    private static final int PRIORITYNUMBER = 6;

    public Leave(Customer customer, double eventStartTime) {
        super(customer, eventStartTime);
    }

    @Override
    Boolean hasNextEvent() {
        return false;
    }

    @Override 
    Pair<ImList<Server>, Event> addNextEvent(ImList<Server> servers) {
        return new Pair<ImList<Server>, Event>(servers, this);
    }

    @Override
    Stats updateStats(Stats stats) {
        return stats.addFailure();
    }

    @Override
    int getPriorityNumber() {
        return PRIORITYNUMBER;
    }

    @Override 
    public String toString() {
        return String.format("%.3f %d leaves", eventStartTime,
            customer.getCustomerID());
    }
}

