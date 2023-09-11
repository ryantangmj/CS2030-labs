class Done extends Event {
    private final int serverID;

    public Done(Customer customer, double eventDoneTime, String eventType, int serverID) {
        super(customer, eventDoneTime, "Done");
        this.serverID = serverID;
    }

    @Override
    public String toString() {
        return String.format("%.3f %d done serving by %d", super.eventDoneTime,
            customer.getCustomerID(), this.serverID);
    }
}

