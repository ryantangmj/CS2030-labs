class Leave extends Event {
    Leave(Customer customer) {
        super(customer, customer.getArrivalTime(), "Leave");
    }

    @Override
    public Event run(Shop shop) {
        return new Leave(customer);
    }

    @Override
    public Server getServer() {
        return new Server(customer, 0);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.getTime()) + " " + customer.getCustID() + " leaves";
    }
}
