import java.util.function.Supplier;

class Customer {
    private final int customerID;
    private final double arrivalTime;
    private final double startTime;
    private final Supplier<Double> serviceTime;
    
    public Customer() {
        this.customerID = 0;
        this.arrivalTime = 0;
        this.startTime = 0;
        this.serviceTime = new DefaultServiceTime();
    }

    public Customer(int customerID, double arrivalTime, double startTime, 
        Supplier<Double> serviceTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
        this.startTime = startTime;
        this.serviceTime = serviceTime;
    }

    double getServiceTime() {
        return this.serviceTime.get();
    }

    double getArrivalTime() {
        return this.arrivalTime;
    }

    double getStartTime() {
        return this.startTime;
    } 

    double getDoneTime() {
        return this.arrivalTime + this.serviceTime.get();
    }

    int getCustomerID() {
        return this.customerID;
    }

    Customer updateStartTime(double newTime) {
        return new Customer(customerID, arrivalTime, newTime, serviceTime);
    }
}
