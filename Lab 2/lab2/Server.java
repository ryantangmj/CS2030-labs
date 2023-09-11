import java.lang.String;

class Server {
    private final Customer toServe;
    private final double endTime;
    private final int serverID; 

    Server(Customer toServe, int serverID) {
        this.toServe = toServe;
        this.endTime = toServe.getArrivalTime() + toServe.getServiceTime();
        this.serverID = serverID;
    }

    int getServerID() {
        return this.serverID;
    }

    Customer getServingCustomer() {
        return this.toServe;
    }

    double getEndTime() {
        return this.endTime;
    }

    boolean isNotServing() {
        return toServe.getCustID() == 0;
    }

    Server serve(Customer newCustomer) {
        return new Server(newCustomer, this.serverID);
    }

    @Override
    public String toString() {
        return String.format("%.3f", this.toServe.getArrivalTime()) +
            " customer " + this.toServe.getCustID() + " served by server " + serverID;
    }

}
