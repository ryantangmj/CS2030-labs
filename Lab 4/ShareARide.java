import java.lang.Math;

class ShareARide extends Ride {
    private static final int fare = 50;
    private static final int surcharge = 500;
    private static final int surchargeLower = 600;
    private static final int surchargeUpper = 900;

    @Override
    int computeFare(int distance, int noOfPassengers, int timeOfService) {
        double totalFare = 0;

        if (timeOfService <= surchargeUpper && timeOfService >= surchargeLower) {
            totalFare += surcharge;
        }

        totalFare += fare * distance;

        return (int) Math.floor(totalFare / noOfPassengers);
    }
    
    @Override 
    public String toString() {
        return "ShareARide";
    }
}
