import java.util.function.Supplier;

class Server {
    protected final Customer customer;
    protected final double doneTime;
    protected final boolean available;
    protected final int qmax;
    protected final Supplier<Double> restTimes;
    protected final ImList<Customer> waitList;

    public Server(int qmax, Supplier<Double> serviceTime, Supplier<Double> restTimes) {
        this.customer = new Customer(serviceTime);
        this.doneTime = 0.0;
        this.available = true;
        this.qmax = qmax;
        this.restTimes = restTimes;
        this.waitList = new ImList<Customer>();
    }

    public Server(Customer customer, double doneTime, boolean available, int qmax, 
        Supplier<Double> restTimes, ImList<Customer> waitList) {
        this.customer = customer;
        this.doneTime = doneTime;
        this.available = false;
        this.qmax = qmax;
        this.restTimes = restTimes;
        this.waitList = waitList;
    }

    boolean canServe(double startTime) {
        if (this.available || this.doneTime <= startTime) {
            return true;
        }

        return false;
    }

    boolean canWait() {
        if (this.waitList.size() < this.qmax) {
            return true;
        }

        return false;
    }

    boolean haveWait() {
        return this.waitList.size() == 0;
    }

    Customer getCustomer() {
        return this.customer;
    }

    double getDoneTime() {
        return this.doneTime;
    }

    Server serve(Customer customer) {
        return new Server(customer, customer.getDoneTime(), false, this.qmax, restTimes, 
            this.waitList);
    }

    Customer serveWaitList() {
        return this.waitList.get(0);
    }

    Server updateDoneTime() {
        return new Server(this.customer, this.doneTime + customer.getServiceTime(), false, 
            this.qmax, this.restTimes, this.waitList);
    }
    
    Server updateDoneWithRest() {
        return new Server(this.customer, this.doneTime + this.restTimes.get(), false,
            this.qmax, this.restTimes, this.waitList);
    }

    Server joinWaitList(Customer customer) {
        return new Server(this.customer, this.doneTime, false, 
            this.qmax, this.restTimes, this.waitList.add(customer));
    }

    Server removeWaitList() {
        return new Server(this.customer, this.doneTime, false, this.qmax,
            this.restTimes, this.waitList.remove(0));
    }
}

