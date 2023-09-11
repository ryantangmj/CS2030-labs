class Wait extends Event {
    private final int serverID;

    Wait(Customer customer, double eventStartTime, boolean human, int serverID) {
        super(customer, eventStartTime, human);
        this.serverID = serverID;
    }

    @Override
    Boolean hasNextEvent() {
        return true;
    }
    
    @Override
    Pair<ImList<Server>, Event> addNextEvent(ImList<Server> servers) {
        int fastestServer = serverID;
           
        if (!human) {
            fastestServer = servers.get(serverID - 1).fastestServerID(servers, serverID);
        }
         
        return new Pair<ImList<Server>, Event>(servers,
            new Pending(customer, 
                servers.get(fastestServer - 1).getDoneTime(), human, fastestServer));
    }

    @Override
    public String toString() {
        if (human) {
            return String.format("%.3f %d waits at %d", eventStartTime,
                 customer.getCustomerID(), serverID);
        }

        return String.format("%.3f %d waits at self-check %d", eventStartTime, 
            customer.getCustomerID(), serverID);
    }
}
