class Serve extends Event {
    private final Server server;

    Serve(Customer customer, Server server) {
        super(customer, customer.getArrivalTime(), "Serve");
        this.server = server;
    }

    @Override
    public Server getServer() {
        return this.server;
    }
 
    @Override
    public Event run(Shop shop) {
        return new Done(customer, server);
    }

    @Override
    public String toString() {
        return String.format("%.3f", customer.getArrivalTime())
            + " " + customer.getCustID() + " serves by " + server.getServerID();
    }
}
