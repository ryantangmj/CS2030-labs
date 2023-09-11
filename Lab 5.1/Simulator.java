import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int numOfSelfChecks;
    private final int qmax;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restTimes; 

    Simulator(int numOfServers, int numOfSelfChecks, int qmax, ImList<Double> arrivalTimes, 
        Supplier<Double> serviceTime, Supplier<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfSelfChecks = numOfSelfChecks;
        this.qmax = qmax;
        this.arrivalTimes = arrivalTimes;
        this.serviceTime = serviceTime;
        this.restTimes = restTimes;
    }

    ImList<Server> createServers() {
        ImList<Server> servers = new ImList<Server>();
        
        for (int i = 0; i < this.numOfServers; i++) {
            servers = servers.add(new Server(this.qmax, this.serviceTime, 
                this.restTimes));
        }
        
        for (int i = 0; i < this.numOfSelfChecks; i++) {
            servers = servers.add(new HumanServer(this.qmax, this.serviceTime, 
                this.restTimes));
        }

        return servers;
    }

    ImList<Customer> createCustomers() {
        ImList<Customer> customers = new ImList<Customer>();

        for (int i = 0; i < arrivalTimes.size(); i++) {
            customers = customers.add(new Customer(i + 1, arrivalTimes.get(i), 0,
                        serviceTime));
        }

        return customers;
    }

    String simulate() {
        String output = "";
        ImList<Customer> customers = createCustomers();
        ImList<Server> servers = createServers();
        PQ<Event> pq = new PQ<Event>(new EventComp());
        Stats stats = new Stats();

        for (int i = 0; i < customers.size(); i++) {
            pq = pq.add(new Arrive(customers.get(i),customers.get(i).getArrivalTime()));
        }

        while (!pq.isEmpty()) {
            Pair<Event, PQ<Event>> pr = pq.poll();
            pq = pr.second();

            if (pr.first().hasNextEvent()) {
                Pair<ImList<Server>, Event> served = pr.first().addNextEvent(servers);
                
                if (pr.first() != served.second()) {
                    pq = pq.add(served.second());
                }

                servers = served.first();
            }
            
            stats = pr.first().updateStats(stats);
            output = output.trim() + "\n" + pr.first().toString();
            
        }    
        
        if (stats.getSuccess() == 0) {
            output += String.format("\n[0.000 %d %d]", stats.getSuccess(), stats.getFailure());
        } else {
            output += String.format("\n[%.3f %d %d]", (stats.getWaitTime() / stats.getSuccess()),
                stats.getSuccess(), stats.getFailure());
        }

        return output;
    }
}
