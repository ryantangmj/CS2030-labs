class Serve extends Event {
    //private static final int PRIORITYNUMBER = 2;
    private final int serverID;

    public Serve(Customer customer, double eventStartTime, int serverID) {
        super(customer, eventStartTime);
        this.serverID = serverID;
    }

    @Override 
    Boolean hasNextEvent() {
        return true;
    }

    @Override 
    Pair<ImList<Server>, Event> addNextEvent(ImList<Server> servers) {
        if (eventStartTime != customer.getArrivalTime()) {
            ImList<Server> updatedServers = servers.set(serverID - 1, 
                servers.get(serverID - 1).removeWaitList().updateDoneTime());

            return new Pair<ImList<Server>, Event>(updatedServers, 
                new Done(customer, updatedServers.get(serverID - 1).getDoneTime(), serverID));
        }

        return new Pair<ImList<Server>, Event>(servers,
            new Done(customer, servers.get(serverID - 1).getDoneTime(),
                serverID));
    }
    
    @Override
    Stats updateStats(Stats stats) {
        return stats.updateWaitTime(eventStartTime
              - customer.getArrivalTime());
    }

    @Override
    public String toString() {
        return String.format("%.3f %d serves by %d", eventStartTime,
                customer.getCustomerID(), serverID);
    }
}
