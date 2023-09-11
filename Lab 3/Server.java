class Server {
    private final Customer customer;
    private final double doneTime;
    private final boolean available;
    private final int qmax;
    private final ImList<Customer> waitList;

    public Server(int qmax) {
        this.customer = new Customer();
        this.doneTime = 0.0;
        this.available = true;
        this.qmax = qmax;
        this.waitList = new ImList<Customer>();
    }

    public Server(Customer customer, double doneTime, boolean available, int qmax, 
        ImList<Customer> waitList) {
        this.customer = customer;
        this.doneTime = doneTime;
        this.available = false;
        this.qmax = qmax;
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
        return new Server(customer, customer.getDoneTime(), false, this.qmax, this.waitList);
    }

    Customer serveWaitList() {
        return this.waitList.get(0);
    }

    Server updateDoneTime() {
        return new Server(this.customer, this.doneTime + customer.getServiceTime(), false, 
            this.qmax, this.waitList);
    }

    Server joinWaitList(Customer customer) {
        return new Server(this.customer, this.doneTime, false, 
            this.qmax, this.waitList.add(customer));
    }

    Server removeWaitList() {
        return new Server(this.customer, this.doneTime, false, this.qmax,
            this.waitList.remove(0));
    }
}

