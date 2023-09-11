class Server {
    private final Customer customer;
    private final double doneTime;
    private final boolean available;

    public Server() {
        this.customer = new Customer();
        this.doneTime = 0.0;
        this.available = true;
    }

    public Server(Customer customer, double doneTime) {
        this.customer = customer;
        this.doneTime = doneTime;
        this.available = false;
    }

    boolean canServe(double startTime) {
        if (this.available || this.doneTime <= startTime) {
            return true;
        }

        return false;
    }

    Server serve(Customer customer) {
        return new Server(customer, customer.getDoneTime());
    }
} 
