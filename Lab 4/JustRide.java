class JustRide extends Ride {
    private static final int fare = 22;
    private static final int surcharge = 500;
    private static final int surchargeLower = 600;
    private static final int surchargeUpper = 900;

    JustRide() {
    }

    @Override 
    int computeFare(int distance, int noOfPassengers, int timeOfService) {
        int totalFare = 0;
        
        if (timeOfService >= surchargeLower && timeOfService <= surchargeUpper) {
            totalFare += surcharge;
        }

        totalFare += fare * distance;

        return totalFare;
    }

    @Override
    public String toString() {
        return "JustRide";
    }
}
