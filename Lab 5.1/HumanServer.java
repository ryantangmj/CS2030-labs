import java.util.function.Supplier;

class HumanServer extends Server {
    public HumanServer(int qmax, Supplier<Double> serviceTime, Supplier<Double> restTimes) {
        super(qmax, serviceTime, restTimes);
    }

    public HumanServer(Customer customer, double doneTime, boolean available, int qmax,
            Supplier<Double> restTimes, ImList<Customer> waitList) {
        super(customer, doneTime, available, qmax, restTimes, waitList);
    }
    
    @Override
    Server serve(Customer customer) {
        return new HumanServer(customer, customer.getDoneTime(), false, this.qmax, this.restTimes,
                this.waitList);
    }
    
    @Override
    Server updateDoneTime() {
        return new HumanServer(this.customer, this.doneTime + customer.getServiceTime(), false,
                this.qmax, this.restTimes, this.waitList);
    }
    
    @Override
    Server updateDoneWithRest() {
        return new HumanServer(this.customer, this.doneTime, false,
                this.qmax, this.restTimes, this.waitList);
    }

    @Override
    Server joinWaitList(Customer customer) {
        return new HumanServer(this.customer, this.doneTime, false,
                this.qmax, this.restTimes, this.waitList.add(customer));
    }

    @Override
    Server removeWaitList() {
        return new HumanServer(this.customer, this.doneTime, false, this.qmax,
                this.restTimes, this.waitList.remove(0));
    }
}
