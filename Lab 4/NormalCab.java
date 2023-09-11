import java.lang.Math;

class NormalCab extends Driver {

    NormalCab(String licensePlate, int waitingTime) {
        super(licensePlate, waitingTime);
    }
    
    @Override
    double lowestPrice(Request request) {
        return Math.min(request.computeFare(new JustRide()) / 100.0, 
            request.computeFare(new TakeACab()) / 100.0);
    }

    @Override
    double higherPrice(Request request) {
        return Math.max(request.computeFare(new JustRide()) / 100.0, 
            request.computeFare(new TakeACab()) / 100.0);
    }

    @Override
    String getCheaperDriver(Request request) {
        if (request.computeFare(new JustRide()) < request.computeFare(new TakeACab())) {
            return new JustRide().toString();
        }

        return new TakeACab().toString();
    }

    @Override 
    String getExpDriver(Request request) {
        if (request.computeFare(new JustRide()) < request.computeFare(new TakeACab())) {
            return new TakeACab().toString();
        }
         
        return new JustRide().toString();
    }

    @Override 
    public String toString() {
        return super.toString() + "NormalCab";
    }
}
