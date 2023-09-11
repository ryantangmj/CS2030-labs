class Leave extends Event {
    Leave(Customer customer, double eventStartTime, boolean human) {
        super(customer, eventStartTime, human);
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
    public String toString() {
        return String.format("%.3f %d leaves", eventStartTime,
            customer.getCustomerID());
    }
}

