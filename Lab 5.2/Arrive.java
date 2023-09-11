class Arrive extends Event {

    Arrive(Customer customer, double eventStartTime, boolean human) {
        super(customer, eventStartTime, human);
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
                    new Serve(customer, customer.getArrivalTime(), servers.get(i).isHuman(), 
                        i + 1));
            }
        }

        for (int j = 0; j < servers.size(); j++) { 
            if (servers.get(j).canWait()) {
                ImList<Server> updatedServers = servers.set(j, 
                    servers.get(j).joinWaitList(customer));
                
                if (!updatedServers.get(j).isHuman()) {
                    for (int k = j + 1; k < updatedServers.size(); k++) {
                        updatedServers = updatedServers.set(k, 
                            updatedServers.get(k).joinWaitList(customer));
                    }
                } 
               
                
                return new Pair<ImList<Server>, Event>(updatedServers, 
                    new Wait(customer, customer.getArrivalTime(), 
                        servers.get(j).isHuman(), j + 1));
            }
        }
        
        return new Pair<ImList<Server>, Event>(servers, 
            new Leave(customer, customer.getArrivalTime(), human));
        
    }

    @Override 
    public String toString() {
        return String.format("%.3f %d arrives", eventStartTime, 
            customer.getCustomerID());
    }
}
