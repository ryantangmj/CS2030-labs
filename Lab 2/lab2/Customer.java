import java.lang.String;

class Customer {
    private final double arrivalTime;
    private final double serviceTime;
    private final int custID;

    Customer(double arrivalTime, double serviceTime, int custID) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        this.custID = custID;
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    double getServiceTime() {
        return this.serviceTime;
    } 

    int getCustID() {
        return this.custID;
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.arrivalTime) + " customer " + custID + " arrives";
    }
}
