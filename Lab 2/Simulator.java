import java.lang.String;

class Simulator {
    private final int numOfServers;
    private final ImList<Double> arrivalTimes;
    private final ImList<Double> serviceTimes;

    public Simulator(int numOfServers, ImList<Double> arrivalTimes, ImList<Double> serviceTimes) {
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

    String simulate() {
        String output = "";
        ImList<Customer> customers = createCustomers();
        ImList<Server> servers = createServers();
        PQ<Event> pq = new PQ<Event>(new EventComp());
        int success = 0;
        int failure = 0;

        for (int i = 0; i < customers.size(); i++) {
            pq = pq.add(new Arrive(customers.get(i),customers.get(i).getArrivalTime(), "Arrive"));
        }

        while (!pq.isEmpty()) {
            Pair<Event, PQ<Event>> pr = pq.poll();
            pq = pr.second();

            if (pr.first().getEventType() == "Arrive") {
                Boolean assignedServer = false;

                for (int j = 0; j < this.numOfServers && !assignedServer; j++) { 
                    if (servers.get(j).canServe(pr.first().getCustomer().getArrivalTime())) {
                        pq = pq.add(new Serve(pr.first().getCustomer(),
                                pr.first().getCustomer().getArrivalTime(), "Serve", j + 1));
                        pq = pq.add(new Done(pr.first().getCustomer(),
                                pr.first().getCustomer().getDoneTime(), "Done", j + 1));
                        success++;
                        assignedServer = true;
                        servers = servers.set(j,
                            servers.get(j).serve(pr.first().getCustomer()));
                    }
                }

                if (!assignedServer) {
                    pq = pq.add(new Leave(pr.first().getCustomer(),
                            pr.first().getCustomer().getArrivalTime(), "Leave"));
                    failure++;
                }
            }

            output += pr.first().toString() + "\n";
        }

        output += String.format("[%d %d]", success, failure);
        return output;
    }
}
