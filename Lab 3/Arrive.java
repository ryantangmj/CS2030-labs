class Arrive extends Event {
    private static final int PRIORITYNUMBER = 5;

    public Arrive(Customer customer, double eventStartTime) {
        super(customer, eventStartTime);
    }
    
    @Override 
    Boolean hasNextEvent() {
        return true;
    }

    @Override
    Pair<ImList<Server>, Event> addNextEvent(ImList<Server> servers) {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).canServe(customer.getArrivalTime())) {
                ImList<Server> updatedServers = servers.set(i, 
                    servers.get(i).serve(customer));                
                return new Pair<ImList<Server>, Event>(updatedServers, 
                    new Serve(customer, customer.getArrivalTime(), i + 1));
            }
        }

        for (int j = 0; j < servers.size(); j++) { 
            if (servers.get(j).canWait()) {
                ImList<Server> updatedServers = servers.set(j, 
                    servers.get(j).joinWaitList(customer));
                return new Pair<ImList<Server>, Event>(updatedServers, 
                    new Wait(customer, customer.getArrivalTime(), j + 1));
            }
        }
        
        return new Pair<ImList<Server>, Event>(servers, 
            new Leave(customer, customer.getArrivalTime()));
        
    }

    @Override
    int getPriorityNumber() {
        return this.PRIORITYNUMBER;
    }

    @Override 
    public String toString() {
        return String.format("%.3f %d arrives", eventStartTime, 
            customer.getCustomerID());
    }
}
