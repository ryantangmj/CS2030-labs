class Customer {
    private final int customerID;
    private final double arrivalTime;
    private final double serviceTime;

    public Customer() {
        this.customerID = 0;
        this.arrivalTime = 0;
        this.serviceTime = 0;
    }

    public Customer(int customerID, double arrivalTime, double serviceTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    double getArrivalTime() {
        return this.arrivalTime;
    } 

    double getDoneTime() {
        return this.arrivalTime + this.serviceTime;
    }
}
