import java.util.function.Supplier;

class SelfServer extends Servers {
    public SelfServer(int qmax, Supplier<Double> serviceTime, Supplier<Double> restTimes) {
        super(qmax, serviceTime, restTimes);
    }

    public SelfServer(Customer customer, double doneTime, boolean available, int qmax,
            Supplier<Double> restTimes, ImList<Customer> waitList) {
        super(customer, doneTime, available, qmax, restTimes, waitList);
    }
    
    @Override
    Servers serve(Customer customer) {
        return new SelfServer(customer, customer.getDoneTime(), false, this.qmax, this.restTimes,
                this.waitList);
    }
    
    @Override
    Servers updateDoneTime() {
        return new SelfServer(this.customer, this.doneTime + customer.getServiceTime(), false,
                this.qmax, this.restTimes, this.waitList);
    }
    
    @Override
    Servers updateDoneWithRest() {
        return new SelfServer(this.customer, this.doneTime, false,
                this.qmax, this.restTimes, this.waitList);
    }

    @Override
    Servers joinWaitList(Customer customer) {
        return new SelfServer(this.customer, this.doneTime, false,
                this.qmax, this.restTimes, this.waitList.add(customer));
    }

    @Override
    Servers removeWaitList() {
        return new SelfServer(this.customer, this.doneTime, false, this.qmax,
                this.restTimes, this.waitList.remove(0));
    }
}
