class Pending extends Event {
    private final int serverID;

    Pending(Customer customer, double eventStartTime, boolean human, int serverID) {
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
        
        if (servers.get(serverID - 1).serveWaitList().getCustomerID() == customer.getCustomerID()
                && servers.get(fastestServer - 1).getDoneTime() <= this.eventStartTime) {    
            return new Pair<ImList<Server>, Event>(servers,
                new Serve(customer, servers.get(fastestServer - 1).getDoneTime(), 
                    human, fastestServer));
        }
        
        if (!human) {
            fastestServer = servers.get(fastestServer - 1).fastestServerID(servers, serverID);
        }

        return new Pair<ImList<Server>, Event>(servers,
            new Pending(customer,
                servers.get(fastestServer - 1).getDoneTime(), human, fastestServer));
    }

    @Override
    public String toString() {
        return "";
    }

}    
