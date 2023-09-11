class Done extends Event {
    private final int serverID;

    Done(Customer customer, double eventStartTime, boolean human, int serverID) {
        super(customer, eventStartTime, human);
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
        if (human) {
            return String.format("%.3f %d done serving by %d", eventStartTime,
                customer.getCustomerID(), serverID);
        }

        return String.format("%.3f %d done serving by self-check %d", eventStartTime,
            customer.getCustomerID(), serverID);
    }
}

