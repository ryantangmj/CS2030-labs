class Serve extends Event {
    private final int serverID;

    Serve(Customer customer, double eventStartTime, boolean human, int serverID) {
        super(customer, eventStartTime, human);
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
            
            if (!human) {
                for (int i = 0; i < updatedServers.size(); i++) {
                    if (!updatedServers.get(i).isHuman() && i != (serverID - 1)) {
                        updatedServers = updatedServers.set(i, 
                            updatedServers.get(i).removeWaitList());
                    }
                }
            }

            return new Pair<ImList<Server>, Event>(updatedServers, 
                new Done(customer, updatedServers.get(serverID - 1).getDoneTime(),
                    human, serverID));
        }

        return new Pair<ImList<Server>, Event>(servers,
            new Done(customer, servers.get(serverID - 1).getDoneTime(),
                human, serverID));
    }
    
    @Override
    Stats updateStats(Stats stats) {
        return stats.updateWaitTime(eventStartTime
              - customer.getArrivalTime());
    }

    @Override
    public String toString() {
        if (human) {
            return String.format("%.3f %d serves by %d", eventStartTime,
                customer.getCustomerID(), serverID);
        }

        return String.format("%.3f %d serves by self-check %d", eventStartTime,
                customer.getCustomerID(), serverID);
    }
}
