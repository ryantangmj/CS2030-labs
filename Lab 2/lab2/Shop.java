class Shop {
    private final ImList<Server> servers;

    Shop(int numOfServers) {
        ImList<Server> serverList = new ImList<Server>();

        for (int i = 0; i < numOfServers; i++) {
            serverList = serverList.add(new Server(new Customer(0, 0, 0), i + 1));
        }

        servers = serverList;
    }

    Shop(ImList<Server> servers) {
        this.servers = servers;
    }

    Shop(Shop shop) {
        this.servers = shop.getServersList();
    }

    ImList<Server> getServersList() {
        return this.servers;
    }


    boolean canServe() {
        for (int i = 0; i < servers.size(); i++) {
            Server server = servers.get(i);
            if (server.isNotServing()) {
                return true;
            }
        }
        return false;
    }

    Server assignServer(Customer customer) {
        for (int i = 0; i < servers.size(); i++) {
            if (servers.get(i).isNotServing()) {
                return servers.get(i).serve(customer);
            }
        }
        return servers.get(0);
    }

    Shop updateServer(Server server, Customer customer) {
        ImList<Server> tempServers = this.servers;
        tempServers = servers.set(server.getServerID() - 1, 
                new Server(customer, server.getServerID()));
        return new Shop(tempServers);
    }
    
    Server findAssignedServer(Customer c2) {
        for (int i = 0; i < servers.size(); i++) {
            Customer c1 = servers.get(i).getServingCustomer();
            if (c1.getCustID() == c2.getCustID()) {
                return servers.get(i);
            }
        }
        return servers.get(0);
    }

    /*
    String shopDo() {
        ImList<Server> servers = new ImList<Server>();
        String output = "";
        int numCustomersServed = 0;
        int numCustomersLeft = 0;

        Customer dummy = new Customer(0, 0, 0);
        for (int i = 0; i < numOfServers; i++) {
            servers = servers.add(new Server(dummy, i + 1));
        }

        for (int n = 0; n < arrivalTimes.size(); n++) {
            Customer customer = new Customer(arrivalTimes.get(n), serviceTimes.get(n), n + 1);
            output += customer.toString() + "\n";

            for (int i = 0; i < servers.size(); i++) {
                if (servers.get(i).canServe(customer)) {
                    servers = servers.set(i, servers.get(i).serve(customer));
                    numCustomersServed++;
                    output += servers.get(i) + "\n";
                    break; 
                } else if (i == servers.size() - 1) {
                    numCustomersLeft++;
                    double cAT = customer.getArrivalTime();
                    int custID = customer.getCustID();
                    output += String.format("%.3f",cAT) + " customer " + custID + " leaves\n";
                } else {
                    continue;
                }
            }
        }
        return output + "[" + numCustomersServed + " " + numCustomersLeft + "]";

    }
    */

    public String toString() {
        return "";
    }

}
