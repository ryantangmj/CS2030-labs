import java.lang.String;

class Shop {
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes;

    public Shop(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
        this.numOfServers = numOfServers;
        this.arrivalTimes = arrivalTimes;
        this.serviceTimes = serviceTimes;
    }

    ImList<Server> createServers() {
        ImList<Server> servers = new ImList<Server>();

        for (int i = 0; i < this.numOfServers; i++) {
            servers = servers.add(new Server());
        }

        return servers;
    }

    ImList<Customer> createCustomers() {
        ImList<Customer> customers = new ImList<Customer>();

        for (int i = 0; i < arrivalTimes.size(); i++) {
            customers = customers.add(new Customer(i + 1, arrivalTimes.get(i), 
                        serviceTimes.get(i)));
        }

        return customers;
    }

    String returnAssignedServer() {
        ImList<Server> servers = createServers();
        ImList<Customer> customers = createCustomers();
        String output = "";
        int success = 0;
        int failure = 0;

        for (int i = 0; i < customers.size(); i++) { //for loop the customers in the customer array
            for (int j = 0; j < this.numOfServers; j++) { //for loop the servers in the server array
                if (servers.get(j).canServe(arrivalTimes.get(i))) {
                    servers = servers.set(j, 
                            servers.get(j).serve(customers.get(i)));
                    output += String.format(
                            "%.3f customer %d arrives\n%.3f customer %d served by server %d\n",
                            arrivalTimes.get(i), i + 1, arrivalTimes.get(i), i + 1, j + 1); 
                    success++;
                    break;
                } else if (j == this.numOfServers - 1) {
                    output += String.format("%.3f customer %d arrives\n%.3f customer %d leaves\n", 
                            arrivalTimes.get(i), i + 1, arrivalTimes.get(i), i + 1); 
                    failure++;
                    break;
                } else {
                    continue;
                }
            }
        }

        output += String.format("[%d %d]", success, failure);
        return output;
    }

    @Override 
    public String toString() {
        String output = returnAssignedServer();
        return output.trim();
    }
}
