class Serve extends Event {
    private final int serverID;

    public Serve(Customer customer, double eventDoneTime, String eventType, int serverID) {
        super(customer, eventDoneTime, "Serve");
        this.serverID = serverID;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d serves by %d", super.eventDoneTime,
                customer.getCustomerID(), this.serverID);
    }
}
