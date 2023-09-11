class Done extends Event {
    //private static final int PRIORITYNUMBER = 1;
    private final int serverID;

    public Done(Customer customer, double eventStartTime, int serverID) {
        super(customer, eventStartTime);
        this.serverID = serverID;
    }
    
    @Override
    Boolean hasNextEvent() {
        return true;
    }

    @Override
    Pair<ImList<Server>, Event> addNextEvent(ImList<Server> servers) {
        return new Pair<ImList<Server>, Event>(servers.set(serverID - 1, 
            servers.get(serverID - 1).updateDoneWithRest()), this);
    }

    @Override
    Stats updateStats(Stats stats) {
        return stats.addSuccess();
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", eventStartTime,
            customer.getCustomerID(), serverID);
    }
}

