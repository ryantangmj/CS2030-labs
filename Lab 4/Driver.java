abstract class Driver {
    private final String licensePlate;
    private final int waitingTime;

    Driver(String licensePlate, int waitingTime) {
        this.licensePlate = licensePlate;
        this.waitingTime = waitingTime;
    }

    int getWaitingTime() {
        return waitingTime;
    }

    abstract double lowestPrice(Request request);
    
    abstract double higherPrice(Request request);

    abstract String getCheaperDriver(Request request);

    abstract String getExpDriver(Request request);

    @Override 
    public String toString() {
        return String.format("%s (%d mins away) ", licensePlate, waitingTime);
    }
}
