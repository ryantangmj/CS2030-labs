class Done extends Event {
    private final Server server;

    Done(Customer customer, Server server) {
        super(customer, customer.getArrivalTime() + customer.getServiceTime(), "Done");
        this.server = server;
    }

    @Override
    public Event run(Shop shop) {
        return new Done(customer, server);
    }

    @Override
    public Server getServer() {
        return this.server;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " "
            + customer.getCustID() + " done serving by " + server.getServerID();
    }
}
