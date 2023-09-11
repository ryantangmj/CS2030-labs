class Arrive extends Event {

    public Arrive(Customer customer, double eventDoneTime, String eventType) {
        super(customer, eventDoneTime, "Arrive");
    }

    @Override 
    public String toString() {
        return String.format("%.3f %d arrives", super.getEventDoneTime(), 
            super.getCustomer().getCustomerID());
    }
}

