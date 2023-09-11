class Leave extends Event {

    public Leave(Customer customer, double eventDoneTime, String eventType) {
        super(customer, eventDoneTime, "Leave");
    }

    @Override 
    public String toString() {
        return String.format("%.3f %d leaves", super.eventDoneTime,
            super.customer.getCustomerID());
    }
}

