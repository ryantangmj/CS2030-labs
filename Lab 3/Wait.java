class Wait extends Event {
    private static final int PRIORITYNUMBER = 3;
    private final int serverID;

    public Wait(Customer customer, double eventStartTime, int serverID) {
        super(customer, eventStartTime);
        this.serverID = serverID;
    }

    @Override
    Boolean hasNextEvent() {
        return true;
    }
    
    @Override
    Pair<ImList<Server>, Event> addNextEvent(ImList<Server> servers) {
        return new Pair<ImList<Server>, Event>(servers,
            new Pending(customer, 
                servers.get(serverID - 1).getDoneTime(), serverID));
    }

    @Override
    int getPriorityNumber() {
        return this.PRIORITYNUMBER;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d waits at %d", eventStartTime, 
            customer.getCustomerID(), serverID);
    }
}
