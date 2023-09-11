class TakeACab extends Ride {
    private static final int fare = 33;
    private static final int bookingFee = 200;

    TakeACab() {
    }

    @Override 
    int computeFare(int distance, int noOfPasseners, int timeOfService) {
        int totalFare = bookingFee;

        return totalFare + fare * distance;
    }

    @Override
    public String toString() {
        return "TakeACab";
    }
}
