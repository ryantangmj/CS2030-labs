class Pending extends Event {
    //private static final int PRIORITYNUMBER = 4;
    private final int serverID;

    public Pending(Customer customer, double eventStartTime, int serverID) {
        super(customer, eventStartTime);
        this.serverID = serverID;
    }

    @Override
    Boolean hasNextEvent() {
        return true;
    }

    @Override
    Pair<ImList<Server>, Event> addNextEvent(ImList<Server> servers) {
        if (servers.get(serverID - 1).serveWaitList().getCustomerID() == customer.getCustomerID() 
                && servers.get(serverID - 1).getDoneTime() <= this.eventStartTime) {
            return new Pair<ImList<Server>, Event>(servers,
                new Serve(customer, servers.get(serverID - 1).getDoneTime(), serverID));
        }
        
        return new Pair<ImList<Server>, Event>(servers,
            new Pending(customer,
                servers.get(serverID - 1).getDoneTime(), serverID));
    }

    @Override
    public String toString() {
        return "";
    }

}    
