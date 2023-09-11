class Arrive extends Event {
    Arrive(Customer customer) {
        super(customer, customer.getArrivalTime(), "Arrive");
    }

    @Override
    public Event run(Shop shop) {
        if (shop.canServe()) {
            Server server = shop.assignServer(customer);
            return new Serve(customer, server);
        } else {
            return new Leave(customer);
        }
    }

    @Override
    public Server getServer() {
        return new Server(customer, 0);
    }

    @Override
    public String toString() {
        return String.format("%.3f", customer.getArrivalTime())
            + " " +  customer.getCustID() +  " arrives";
    }

}
