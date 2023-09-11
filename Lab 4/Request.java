class Request {
    private final int distance;
    private final int noOfPassengers;
    private final int time;

    Request(int distance, int noOfPassengers, int time) {
        this.distance = distance;
        this.noOfPassengers = noOfPassengers;
        this.time = time;
    }

    int computeFare(Ride ride) {
        return ride.computeFare(distance, noOfPassengers, time);
    }

    @Override
    public String toString() {
        return String.format("%dkm for %dpax @ %dhrs", distance, noOfPassengers, time);
    }
}

